package com.tom.school.db.test.dao.sys;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import com.tom.school.db.dao.QueryResult;
import com.tom.school.db.dao.sys.AuthorityDao;
import com.tom.school.db.model.sys.Authority;
import com.tom.school.db.param.BaseParameter;
import com.tom.school.db.param.sys.AuthorityParameter;
import com.tom.school.db.test.TestContext;

public class AuthorityDaoTest {

	private static int authorityIndex = 0;

	@BeforeClass
	public static void setupBeforeClass() {
		AuthorityDao authorityDao = TestContext.getAuthorityDao();

		// Test persist
		Authority authority = generateAuthority();
		authorityDao.persist(authority);
		assertEquals(authority, authorityDao.get(authority.getId()));

		// Test delete
		authorityDao.delete(authority);
		assertNull(authorityDao.get(authority.getId()));
	}

	private static Authority generateAuthority() {
		Authority authority = new Authority();
		authority.setButtons("Add,Edit,Delete,View");
		authority.setChecked(true);
		authority.setExpanded(false);
		authority.setIconCls("IC_" + getRandomNumber());
		authority.setLeaf(true);
		authority.setMenuCode("MenuCode_" + getRandomNumber());
		authority.setMenuConfig("MenuConfig_" + getRandomNumber());
		authority.setMenuName("MenuName_" + getRandomNumber());
		authority.setParentId(Long.valueOf(-1));
		authority.setSortOrder(Integer.valueOf(-1));
		authority.setUrl("http://schoo.tom.com/" + getRandomNumber());
		return authority;
	}

	private static String getRandomNumber() {
		long randomNumber = System.currentTimeMillis() + authorityIndex++;
		return randomNumber + "";
	}

	private AuthorityDao authorityDao;
	private List<Authority> authorityList = new ArrayList<Authority>();

	public AuthorityDaoTest() {
		this.authorityDao = TestContext.getAuthorityDao();
	}

	@After
	public void tearDown() {
		for (Authority authority : this.authorityList) {
			if (this.authorityDao.get(authority.getId()) != null) {
				this.authorityDao.delete(authority);
			}
		}
	}

	@Test
	public void testDeleteByPK() {
		Authority authority1 = addAuthority();
		Authority authority2 = addAuthority();
		Authority authority3 = addAuthority();

		this.authorityDao.deleteByPK(authority1.getId(), authority2.getId(), authority3.getId());

		assertNull(this.authorityDao.get(authority1.getId()));
		assertNull(this.authorityDao.get(authority2.getId()));
		assertNull(this.authorityDao.get(authority3.getId()));
	}

	@Test
	public void testDeleteByProperties() {
		Authority authority1, authority2;

		/*
		 * where : id = :id
		 */

		authority1 = addAuthority();
		String[] propName = new String[1];
		Object[] propValue = new Object[1];
		propName[0] = "id";
		propValue[0] = authority1.getId();
		this.authorityDao.deleteByProperties(propName, propValue);
		assertNull(this.authorityDao.get(authority1.getId()));

		/*
		 * where : id = :id, menu_name = :menu_name
		 */

		authority1 = addAuthority();
		propName = new String[2];
		propValue = new Object[2];
		propName[0] = "id";
		propValue[0] = authority1.getId();
		propName[1] = "menuName";
		propValue[1] = authority1.getMenuName();
		this.authorityDao.deleteByProperties(propName, propValue);
		assertNull(this.authorityDao.get(authority1.getId()));

		/*
		 * where : id in array(:id1, :id2)
		 */

		authority1 = addAuthority();
		authority2 = addAuthority();
		this.authorityDao.deleteByProperties("id", new Object[] { authority1.getId(), authority2.getId() });
		assertNull(this.authorityDao.get(authority1.getId()));
		assertNull(this.authorityDao.get(authority2.getId()));

		/*
		 * where : mneuName in list(:menuName1, :menuName2)
		 */
		authority1 = addAuthority();
		authority2 = addAuthority();
		List<String> menuNameList = new ArrayList<String>();
		menuNameList.add(authority1.getMenuName());
		menuNameList.add(authority2.getMenuName());
		this.authorityDao.deleteByProperties("menuName", menuNameList);
		assertNull(this.authorityDao.get(authority1.getId()));
		assertNull(this.authorityDao.get(authority2.getId()));
	}

	@Test
	public void testUpdate() {
		Authority authority = addAuthority();
		modifyAutority(authority);
		this.authorityDao.update(authority);
		assertEquals(authority, this.authorityDao.get(authority.getId()));
	}

	@Test
	public void testUpdateByProperties() {
		Authority authority1, authority2;

		/*
		 * array condition : id = :id
		 * array property
		 */

		authority1 = addAuthority();

		// Condition
		String[] conditionName = new String[1];
		Object[] conditionValue = new Object[1];
		conditionName[0] = "id";
		conditionValue[0] = authority1.getId();

		// Property
		modifyAutority(authority1);
		String[] propName = new String[3];
		Object[] propValue = new Object[3];
		propName[0] = "leaf";
		propValue[0] = authority1.getLeaf();
		propName[1] = "menuName";
		propValue[1] = authority1.getMenuName();
		propName[2] = "sortOrder";
		propValue[2] = authority1.getSortOrder();

		this.authorityDao.updateByProperties(conditionName, conditionValue,
				propName, propValue);

		Authority updatedAuthority = this.authorityDao.get(authority1.getId());
		assertEquals(updatedAuthority.getLeaf(), authority1.getLeaf());
		assertEquals(updatedAuthority.getMenuName(), authority1.getMenuName());
		assertEquals(updatedAuthority.getSortOrder(), authority1.getSortOrder());

		/*
		 * array condition : id = :id, menuCode = :menuCode
		 * array property
		 */

		authority1 = addAuthority();

		// Condition
		conditionName = new String[2];
		conditionValue = new Object[2];
		conditionName[0] = "id";
		conditionValue[0] = authority1.getId();
		conditionName[1] = "menuCode";
		conditionValue[1] = authority1.getMenuCode();

		// Property
		modifyAutority(authority1);
		propName = new String[3];
		propValue = new Object[3];
		propName[0] = "leaf";
		propValue[0] = authority1.getLeaf();
		propName[1] = "menuName";
		propValue[1] = authority1.getMenuName();
		propName[2] = "sortOrder";
		propValue[2] = authority1.getSortOrder();

		this.authorityDao.updateByProperties(conditionName, conditionValue,
				propName, propValue);

		updatedAuthority = this.authorityDao.get(authority1.getId());
		assertEquals(updatedAuthority.getLeaf(), authority1.getLeaf());
		assertEquals(updatedAuthority.getMenuName(), authority1.getMenuName());
		assertEquals(updatedAuthority.getSortOrder(), authority1.getSortOrder());

		/*
		 * array condition : id in array(:id1, :id2)
		 * array property
		 */

		authority1 = addAuthority();
		authority2 = addAuthority();

		// Condition
		conditionName = new String[1];
		conditionValue = new Object[1];
		conditionName[0] = "id";
		conditionValue[0] = new Object[] { authority1.getId(), authority2.getId() };

		// Property
		modifyAutority(authority1);
		propName = new String[3];
		propValue = new Object[3];
		propName[0] = "leaf";
		propValue[0] = authority1.getLeaf();
		propName[1] = "menuName";
		propValue[1] = authority1.getMenuName();
		propName[2] = "sortOrder";
		propValue[2] = authority1.getSortOrder();

		this.authorityDao.updateByProperties(conditionName, conditionValue,
				propName, propValue);

		updatedAuthority = this.authorityDao.get(authority1.getId());
		assertEquals(updatedAuthority.getLeaf(), propValue[0]);
		assertEquals(updatedAuthority.getMenuName(), propValue[1]);
		assertEquals(updatedAuthority.getSortOrder(), propValue[2]);

		updatedAuthority = this.authorityDao.get(authority2.getId());
		assertEquals(updatedAuthority.getLeaf(), propValue[0]);
		assertEquals(updatedAuthority.getMenuName(), propValue[1]);
		assertEquals(updatedAuthority.getSortOrder(), propValue[2]);

		/*
		 * list condition : menuCode in list(:menuCode1, :menuCode2)
		 * array property
		 */

		authority1 = addAuthority();
		authority2 = addAuthority();

		// Condition
		conditionName = new String[1];
		conditionValue = new Object[1];
		conditionName[0] = "menuCode";
		List<String> menuCodeList = new ArrayList<String>();
		menuCodeList.add(authority1.getMenuCode());
		menuCodeList.add(authority2.getMenuCode());
		conditionValue[0] = menuCodeList;

		// Property
		modifyAutority(authority1);
		propName = new String[3];
		propValue = new Object[3];
		propName[0] = "leaf";
		propValue[0] = authority1.getLeaf();
		propName[1] = "menuName";
		propValue[1] = authority1.getMenuName();
		propName[2] = "sortOrder";
		propValue[2] = authority1.getSortOrder();

		this.authorityDao.updateByProperties(conditionName, conditionValue,
				propName, propValue);

		updatedAuthority = this.authorityDao.get(authority1.getId());
		assertEquals(updatedAuthority.getLeaf(), propValue[0]);
		assertEquals(updatedAuthority.getMenuName(), propValue[1]);
		assertEquals(updatedAuthority.getSortOrder(), propValue[2]);

		updatedAuthority = this.authorityDao.get(authority2.getId());
		assertEquals(updatedAuthority.getLeaf(), propValue[0]);
		assertEquals(updatedAuthority.getMenuName(), propValue[1]);
		assertEquals(updatedAuthority.getSortOrder(), propValue[2]);

		/**
		 * single condition, array property
		 */

		authority1 = addAuthority();

		// Condition
		String singleConditionName = "id";
		Object singleConditionValue = authority1.getId();

		// Property
		modifyAutority(authority1);
		propName = new String[3];
		propValue = new Object[3];
		propName[0] = "leaf";
		propValue[0] = authority1.getLeaf();
		propName[1] = "menuName";
		propValue[1] = authority1.getMenuName();
		propName[2] = "sortOrder";
		propValue[2] = authority1.getSortOrder();

		this.authorityDao.updateByProperties(singleConditionName, singleConditionValue,
				propName, propValue);

		updatedAuthority = this.authorityDao.get(authority1.getId());
		assertEquals(updatedAuthority.getLeaf(), authority1.getLeaf());
		assertEquals(updatedAuthority.getMenuName(), authority1.getMenuName());
		assertEquals(updatedAuthority.getSortOrder(), authority1.getSortOrder());

		/**
		 * array condition, single property
		 */

		authority1 = addAuthority();

		// Condition
		conditionName = new String[1];
		conditionValue = new Object[1];
		conditionName[0] = "id";
		conditionValue[0] = authority1.getId();

		// Property
		modifyAutority(authority1);
		String singlePropName = "menuName";
		Object singlePropVale = authority1.getMenuName();

		this.authorityDao.updateByProperties(conditionName, conditionValue,
				singlePropName, singlePropVale);

		updatedAuthority = this.authorityDao.get(authority1.getId());
		assertEquals(updatedAuthority.getMenuName(), authority1.getMenuName());

		/**
		 * single condition, single property
		 */

		authority1 = addAuthority();

		// Condition
		singleConditionName = "id";
		singleConditionValue = authority1.getId();

		// Property
		modifyAutority(authority1);
		singlePropName = "menuName";
		singlePropVale = authority1.getMenuName();

		this.authorityDao.updateByProperties(singleConditionName, singleConditionValue,
				singlePropName, singlePropVale);

		updatedAuthority = this.authorityDao.get(authority1.getId());
		assertEquals(updatedAuthority.getMenuName(), authority1.getMenuName());
	}

	private void modifyAutority(Authority authority) {
		authority.setButtons(authority.getButtons() + "*");
		authority.setChecked(authority.getChecked() ? false : true);
		authority.setExpanded(authority.getExpanded() ? false : true);
		authority.setIconCls(authority.getIconCls() + "*");
		authority.setLeaf(authority.getLeaf() ? false : true);
		authority.setMenuCode(authority.getMenuCode() + "*");
		authority.setMenuConfig(authority.getMenuConfig() + "*");
		authority.setMenuName(authority.getMenuName() + "*");
		authority.setParentId(authority.getParentId() - 1);
		authority.setSortOrder(authority.getSortOrder() - 1);
		authority.setUrl(authority.getUrl() + "*");
	}

	@Test
	public void testMerge() {
		Authority authority1 = addAuthority();
		Authority authority2 = this.authorityDao.merge(authority1);

		assertFalse(authority1 == authority2);
		assertEquals(authority1, authority2);

		Authority authority3 = this.authorityDao.get(authority2.getId());
		assertFalse(authority2 == authority3);
	}

	@Test
	public void testLoad() {
		Authority authority = addAuthority();
		try {
			Authority gotAuthority = this.authorityDao.load(authority.getId());
			System.out.println(gotAuthority.getMenuName()); // 抛出异常，因为此时的session已经关闭，延迟加载会失败
			fail();
		} catch (Exception e) {
		}
	}

	@Test
	public void testGetByProperties() {
		String menuName = "m_" + getRandomNumber();
		Authority authority1 = addAuthorityWithMenuName(menuName);
		Authority authority2 = addAuthorityWithMenuName(menuName);

		// Condition
		String[] propName = new String[1];
		Object[] propValue = new Object[1];
		propName[0] = "menuName";
		propValue[0] = menuName;

		// Sort
		Map<String, String> sortedCondition = new HashMap<String, String>();
		sortedCondition.put("id", "desc");

		/*
		 * with sorted condition
		 */
		
		Authority gotAuthority = this.authorityDao.getByProperties(propName, propValue, sortedCondition);
		assertEquals(gotAuthority, authority2);

		sortedCondition.replace("id", "asc");
		gotAuthority = this.authorityDao.getByProperties(propName, propValue, sortedCondition);
		assertEquals(gotAuthority, authority1);

		/*
		 * no sorted condition
		 */
		
		gotAuthority = this.authorityDao.getByProperties(propName, propValue);
		assertEquals(gotAuthority, authority1);
	}

	@Test
	public void testQueryByProperties() {
		String menuName = "m_" + getRandomNumber();
		int top = 5;
		List<Authority> authorityList = new ArrayList<Authority>();
		for (int i = 0; i < 10; i++) {
			authorityList.add(addAuthorityWithMenuName(menuName));
		}

		// Condition
		String[] propName = new String[1];
		Object[] propValue = new Object[1];
		propName[0] = "menuName";
		propValue[0] = menuName;

		// Sort
		Map<String, String> sortedCondition = new HashMap<String, String>();
		sortedCondition.put("id", "desc");

		/*
		 * where(array), sort, top
		 */

		List<Authority> queriedAuthorityList = this.authorityDao.queryByProperties(propName, propValue, sortedCondition, top);
		assertEquals(queriedAuthorityList.size(), top);

		Long lastAuthorityId = Long.MAX_VALUE;
		for (Authority authority : queriedAuthorityList) {
			assertTrue(authorityList.contains(authority));
			assertTrue(authority.getId() < lastAuthorityId); // 判断查询结构为升序排列
			lastAuthorityId = authority.getId();
		}

		/*
		 * where(array), sort
		 */
		queriedAuthorityList = this.authorityDao.queryByProperties(propName, propValue, sortedCondition);
		assertEquals(queriedAuthorityList.size(), authorityList.size());

		lastAuthorityId = Long.MAX_VALUE;
		for (Authority authority : queriedAuthorityList) {
			assertTrue(authorityList.contains(authority));
			assertTrue(authority.getId() < lastAuthorityId);
			lastAuthorityId = authority.getId();
		}

		/*
		 * where(array), top
		 */
		queriedAuthorityList = this.authorityDao.queryByProperties(propName, propValue, top);
		assertEquals(queriedAuthorityList.size(), top);

		lastAuthorityId = Long.MIN_VALUE;
		for (Authority authority : queriedAuthorityList) {
			assertTrue(authorityList.contains(authority));
			assertTrue(authority.getId() > lastAuthorityId);
			lastAuthorityId = authority.getId();
		}

		/*
		 * where(array)
		 */
		queriedAuthorityList = this.authorityDao.queryByProperties(propName, propValue);
		assertEquals(queriedAuthorityList.size(), authorityList.size());

		lastAuthorityId = Long.MIN_VALUE;
		for (Authority authority : queriedAuthorityList) {
			assertTrue(authorityList.contains(authority));
			assertTrue(authority.getId() > lastAuthorityId);
			lastAuthorityId = authority.getId();
		}

		/*
		 * where(single), sort, top
		 */
		queriedAuthorityList = this.authorityDao.queryByProperties(propName, propValue, sortedCondition, top);
		assertEquals(queriedAuthorityList.size(), top);

		lastAuthorityId = Long.MAX_VALUE;
		for (Authority authority : queriedAuthorityList) {
			assertTrue(authorityList.contains(authority));
			assertTrue(authority.getId() < lastAuthorityId);
			lastAuthorityId = authority.getId();
		}

		/*
		 * where(single), sort
		 */

		queriedAuthorityList = this.authorityDao.queryByProperties(propName, propValue, sortedCondition);
		assertEquals(queriedAuthorityList.size(), authorityList.size());

		lastAuthorityId = Long.MAX_VALUE;
		for (Authority authority : queriedAuthorityList) {
			assertTrue(authorityList.contains(authority));
			assertTrue(authority.getId() < lastAuthorityId);
			lastAuthorityId = authority.getId();
		}

		/*
		 * where(single), top
		 */

		queriedAuthorityList = this.authorityDao.queryByProperties(propName, propValue, top);
		assertEquals(queriedAuthorityList.size(), top);

		lastAuthorityId = Long.MIN_VALUE;
		for (Authority authority : queriedAuthorityList) {
			assertTrue(authorityList.contains(authority));
			assertTrue(authority.getId() > lastAuthorityId);
			lastAuthorityId = authority.getId();
		}

		/*
		 * where(single)
		 */

		queriedAuthorityList = this.authorityDao.queryByProperties(propName, propValue);
		assertEquals(queriedAuthorityList.size(), authorityList.size());

		lastAuthorityId = Long.MIN_VALUE;
		for (Authority authority : queriedAuthorityList) {
			assertTrue(authorityList.contains(authority));
			assertTrue(authority.getId() > lastAuthorityId);
			lastAuthorityId = authority.getId();
		}
	}

	@Test
	public void testCountAll() {
		for (int i = 0; i < 10; i++) {
			addAuthority();
		}
		List<Authority> allAuthorities = this.authorityDao.doQueryAll();
		Long allAuthorityCount = this.authorityDao.countAll();
		assertEquals(allAuthorities.size(), allAuthorityCount.intValue());
	}

	public void testDoQueryAll() {
		int top = 10;
		for (int i = 0; i < top; i++) {
			addAuthority();
		}
		Map<String, String> sortedCondition = new HashMap<String, String>();
		sortedCondition.put("id", BaseParameter.SORTED_DESC);
		sortedCondition.put("codeName", BaseParameter.SORTED_ASC);

		/*
		 * sort, top
		 */
		
		List<Authority> allAuthories = this.authorityDao.doQueryAll(sortedCondition, top);
		assertEquals(allAuthories.size(), top);

		Long lastId = Long.MAX_VALUE;
		for (Authority authority : allAuthories) {
			assertTrue(authority.getId() < lastId);
			lastId = authority.getId();
		}

		/*
		 * top
		 */
		
		allAuthories = this.authorityDao.doQueryAll(top);
		assertEquals(allAuthories.size(), top);

		lastId = Long.MIN_VALUE;
		for (Authority authority : allAuthories) {
			assertTrue(authority.getId() > lastId);
			lastId = authority.getId();
		}

		/*
		 * no sort, no top
		 */

		allAuthories = this.authorityDao.doQueryAll();
		Long allAuthoiryCount = this.authorityDao.countAll();
		assertEquals(allAuthories.size(), allAuthoiryCount.intValue());
	}

	@Test
	public void testDoCount() {
		/*
		 * like
		 */

		String menuNamePatter = "menuName_" + getRandomNumber() + "_";
		int authorityNumber = 10;
		for (int i = 0; i < authorityNumber; i++) {
			addAuthorityWithMenuName(menuNamePatter + i);
		}
		AuthorityParameter param = new AuthorityParameter();
		param.getQueryDynamicConditions().put("$like_menuName", menuNamePatter);
		Long count = this.authorityDao.doCount(param);
		assertEquals(authorityNumber, count.intValue());

		/*
		 * equal
		 */

		Integer sortOrder = Integer.valueOf(1000);
		for (int i = 0; i < authorityNumber; i++) {
			addAuthorityWithSortOrder(sortOrder);
		}
		param = new AuthorityParameter();
		param.getQueryDynamicConditions().put("$eq_sortOrder", sortOrder);
		count = this.authorityDao.doCount(param);
		assertEquals(authorityNumber, count.intValue());

		/*
		 * in
		 */
		
		Long[] ids = new Long[authorityNumber];
		List<Long> idList = new ArrayList<Long>();
		for (int i = 0; i < 10; i++) {
			Authority authority = addAuthority();
			ids[i] = authority.getId();
			idList.add(authority.getId());
		}
		param = new AuthorityParameter();
		param.getQueryDynamicConditions().put("$in_id", ids);
		count = this.authorityDao.doCount(param);
		assertEquals(authorityNumber, count.intValue());

		param = new AuthorityParameter();
		param.getQueryDynamicConditions().put("$in_id", idList);
		count = this.authorityDao.doCount(param);
		assertEquals(authorityNumber, count.intValue());
	}

	@Test
	public void testDoQuery() {
		/*
		 * like
		 */

		String menuNamePatter = "menuName_" + getRandomNumber() + "_";
		int authorityNumber = 10;
		List<Authority> addAuthorityList = new ArrayList<Authority>();
		for (int i = 0; i < authorityNumber; i++) {
			addAuthorityList.add(addAuthorityWithMenuName(menuNamePatter + i));
		}
		AuthorityParameter param = new AuthorityParameter();
		param.getQueryDynamicConditions().put("$like_menuName", menuNamePatter);
		List<Authority> queryAuthorityList = this.authorityDao.doQuery(param);
		assertEquals(authorityNumber, queryAuthorityList.size());
		for (Authority authority : addAuthorityList) {
			assertTrue(addAuthorityList.contains(authority));
		}

		/*
		 * equal
		 */

		Integer sortOrder = Integer.valueOf(1000);
		for (int i = 0; i < authorityNumber; i++) {
			addAuthorityList.add(addAuthorityWithSortOrder(sortOrder));
		}
		param = new AuthorityParameter();
		param.getQueryDynamicConditions().put("$eq_sortOrder", sortOrder);
		queryAuthorityList = this.authorityDao.doQuery(param);
		assertEquals(authorityNumber, queryAuthorityList.size());
		for (Authority authority : addAuthorityList) {
			assertTrue(addAuthorityList.contains(authority));
		}

		/*
		 * in
		 */

		Long[] ids = new Long[authorityNumber];
		List<Long> idList = new ArrayList<Long>();
		for (int i = 0; i < 10; i++) {
			Authority authority = addAuthority();
			addAuthorityList.add(authority);
			ids[i] = authority.getId();
			idList.add(authority.getId());
		}
		param = new AuthorityParameter();
		param.getQueryDynamicConditions().put("$in_id", ids);
		queryAuthorityList = this.authorityDao.doQuery(param);
		assertEquals(authorityNumber, queryAuthorityList.size());
		for (Authority authority : addAuthorityList) {
			assertTrue(addAuthorityList.contains(authority));
		}
	}
	
	@Test
	public void testDoPaginationQuery(){
		String menuNamePatter = "menuName_" + getRandomNumber() + "_";
		int authorityNumber = 10;
		List<Authority> addAuthorityList = new ArrayList<Authority>();
		for (int i = 0; i < authorityNumber; i++) {
			addAuthorityList.add(addAuthorityWithMenuName(menuNamePatter + i));
		}
		AuthorityParameter param = new AuthorityParameter();
		param.getQueryDynamicConditions().put("$like_menuName", menuNamePatter);
		param.getSortedConditions().put("id", BaseParameter.SORTED_ASC);
		param.setFirstResult(3);
		param.setMaxResults(3);
		QueryResult<Authority> queryResult = this.authorityDao.doPaginationQuery(param);
		assertEquals(authorityNumber, queryResult.getTotalCount().intValue());
		assertEquals(3, queryResult.getResultList().size());
		for(int i = 0; i < 3; i++){
			Authority authority1 = addAuthorityList.get(i + 3);
			Authority authority2 = queryResult.getResultList().get(i);
			assertEquals(authority1, authority2);
		}
	}

	private Authority addAuthorityWithMenuName(String menuName) {
		Authority authority = generateAuthority();
		authority.setMenuName(menuName);
		addAuthority(authority);
		return authority;
	}

	private Authority addAuthorityWithSortOrder(Integer sortOrder) {
		Authority authority = generateAuthority();
		authority.setSortOrder(sortOrder);
		addAuthority(authority);
		return authority;
	}

	private Authority addAuthority() {
		Authority authority = generateAuthority();
		addAuthority(authority);
		return authority;
	}

	private void addAuthority(Authority authority) {
		this.authorityDao.persist(authority);
		this.authorityList.add(authority);
	}

}

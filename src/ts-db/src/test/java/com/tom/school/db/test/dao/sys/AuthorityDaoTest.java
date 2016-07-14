package com.tom.school.db.test.dao.sys;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import com.tom.school.db.dao.sys.AuthorityDao;
import com.tom.school.db.model.sys.Authority;
import com.tom.school.db.model.sys.SystemUser;
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
		 * where : id = :id
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
		 * where : id = :id, menuCode = :menuCode
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
		 * where : id in array(:id1, :id2)
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
		 * where : menuCode in list(:menuCode1, :menuCode2)
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
		 * polymorphic function1
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
		 * polymorphic function2
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
		 * polymorphic function3
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
			System.out.println(gotAuthority.getMenuName());  //抛出异常，因为此时的session已经关闭，延迟加载会失败
			fail();
		} catch (Exception e) {
		}
	}

	private Authority addAuthority() {
		Authority authority = generateAuthority();
		this.authorityDao.persist(authority);
		this.authorityList.add(authority);
		return authority;
	}

}

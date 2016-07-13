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
	public void testDeleteByProperties(){
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
		this.authorityDao.deleteByProperties("id", new Object[]{authority1.getId(), authority2.getId()});
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
	public void testUpdate(){
		Authority authority = addAuthority();
		modifyAutority(authority);
		this.authorityDao.update(authority);
		assertEquals(authority, this.authorityDao.get(authority.getId()));
	}
	
	private void modifyAutority(Authority authority){
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
	public void testDoQueryAll() {

		List<Authority> allAuthorities = this.authorityDao.doQueryAll();
		for (Authority authority : allAuthorities) {
			System.out.println(authority);
		}

	}
	
	private Authority addAuthority(){
		Authority authority = generateAuthority();
		this.authorityDao.persist(authority);
		this.authorityList.add(authority);
		return authority;
	}

}

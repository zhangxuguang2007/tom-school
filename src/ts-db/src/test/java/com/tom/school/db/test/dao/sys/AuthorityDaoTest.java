package com.tom.school.db.test.dao.sys;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.tom.school.db.dao.sys.AuthorityDao;
import com.tom.school.db.model.sys.Authority;
import com.tom.school.db.test.TestContext;

public class AuthorityDaoTest {
	
	private static int authorityIndex = 0;
	
	@BeforeClass
	public static void setupBeforeClass(){
		AuthorityDao authorityDao = TestContext.getAuthorityDao();
		
		//Test persist
		Authority authority = generateAuthority();
		authorityDao.persist(authority);
		assertEquals(authority, authorityDao.get(authority.getId()));
		
		//Test delete
		authorityDao.delete(authority);
		assertNull(authorityDao.get(authority.getId()));
	}
	
	private static Authority generateAuthority(){
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
	
	public AuthorityDaoTest() {
		this.authorityDao = TestContext.getAuthorityDao();
	}

	@Test
	public void testDoQueryAll(){
		
		List<Authority> allAuthorities = this.authorityDao.doQueryAll();
		for(Authority authority : allAuthorities){
			System.out.println(authority);
		}
		
	}
	
}

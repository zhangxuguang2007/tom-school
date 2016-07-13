package com.tom.school.db.test.dao.sys;

import java.util.List;

import org.junit.Test;

import com.tom.school.db.dao.sys.AuthorityDao;
import com.tom.school.db.model.sys.Authority;
import com.tom.school.db.test.TestContext;

public class AuthorityDaoTest {
	
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

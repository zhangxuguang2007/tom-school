package com.tom.school.db.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tom.school.db.dao.sys.AuthorityDao;
import com.tom.school.db.dao.sys.SystemUserDao;

public class TestContext {

	private static ApplicationContext applicationContext;

	private static ApplicationContext getApplicationContext() {
		if (applicationContext == null) {
			applicationContext = new ClassPathXmlApplicationContext(
					"applicationContext.xml");
		}
		return applicationContext;
	}

	public static SystemUserDao getSystemUserDao() {
		return getApplicationContext().getBean(SystemUserDao.class);
	}
	
	public static AuthorityDao getAuthorityDao() {
		return getApplicationContext().getBean(AuthorityDao.class);
	}

}

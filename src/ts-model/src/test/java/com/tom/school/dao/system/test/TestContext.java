package com.tom.school.dao.system.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tom.school.dao.sys.SystemUserDao;

public class TestContext {

	private static ApplicationContext applicationContext;
	private static SystemUserDao systemUserDao;
	
	private static ApplicationContext getApplicationContext(){
		if(applicationContext == null){
			applicationContext = new ClassPathXmlApplicationContext(
					"applicationContext.xml", "testApplicationContext.xml");
		}
		return applicationContext;
	}
	
	public static SystemUserDao getSystemUserDao(){
		if(systemUserDao == null){
			systemUserDao = getApplicationContext().getBean(SystemUserDao.class);
		}
		return systemUserDao;
	}
	
}

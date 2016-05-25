package com.tom.school.dao.system.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tom.schoo.dao.sys.SystemUserDao;
import com.tom.school.model.sys.SystemUser;

public class SystemUserDaoTest {

	private SystemUserDao systemUserDao;

	@SuppressWarnings("resource")
	@Before
	public void set() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"applicationContext.xml", "testApplicationContext.xml");
		this.systemUserDao = applicationContext.getBean(SystemUserDao.class);
	}

	@Test
	public void testGet() {
		SystemUser user = this.systemUserDao.get(new Integer(2));
		if(user != null){
			System.out.println(user.getName());
		}
	}
	
	@Test
	public void testSave(){
		SystemUser user = new SystemUser();
		user.setName("Tom");
		user.setPassword("Cogent01");
		this.systemUserDao.persist(user);
	}

}

package com.tom.school.dao.system.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

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
	public void testPersist() {
		SystemUser newUser = generateUser();
		this.systemUserDao.persist(newUser);

		SystemUser gotUser = this.systemUserDao.get(newUser.getId());
		assertEquals(newUser, gotUser);

		this.systemUserDao.delete(newUser);
		gotUser = this.systemUserDao.get(newUser.getId());
		assertNull(gotUser);
	}

	private SystemUser generateUser() {
		SystemUser user = new SystemUser();
		user.setName("Tom");
		user.setPassword("Cogent01");
		return user;
	}

}

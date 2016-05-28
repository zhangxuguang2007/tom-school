package com.tom.school.dao.system.test;

import org.junit.Before;
import org.junit.Test;

import com.tom.school.dao.sys.SystemUserDao;
import com.tom.school.model.sys.SystemUser;
import com.tom.school.test.TestContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Random;

public class SystemUserDaoTest {

	private SystemUserDao systemUserDao;

	@Before
	public void set() {
		this.systemUserDao = TestContext.getSystemUserDao();
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
		user.setName("Jack" + (new Random()).nextInt(10000));
		user.setPassword("Cogent01");
		return user;
	}

}

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
		this.systemUserDao.persist(newUser);  //persist

		SystemUser gotUser = this.systemUserDao.get(newUser.getId());
		assertEquals(newUser, gotUser);

		this.systemUserDao.delete(newUser);
		gotUser = this.systemUserDao.get(newUser.getId());
		assertNull(gotUser);
	}

	@Test
	public void testDeleteByPK() {
		SystemUser user1, user2, user3;

		user1 = generateUser();
		user2 = generateUser();
		user3 = generateUser();

		this.systemUserDao.persist(user1);
		this.systemUserDao.persist(user2);
		this.systemUserDao.persist(user3);
		
		assertEquals(user1, this.systemUserDao.get(user1.getId()));
		assertEquals(user2, this.systemUserDao.get(user2.getId()));
		assertEquals(user3, this.systemUserDao.get(user3.getId()));

		this.systemUserDao.deleteByPK(user1.getId(), user2.getId(), user3.getId());  //deletByPK

		assertNull(this.systemUserDao.get(user1.getId()));
		assertNull(this.systemUserDao.get(user2.getId()));
		assertNull(this.systemUserDao.get(user3.getId()));
	}

	private SystemUser generateUser() {
		SystemUser user = new SystemUser();
		user.setName("Jack" + (new Random()).nextInt(10000));
		user.setPassword("Cogent01");
		return user;
	}

}

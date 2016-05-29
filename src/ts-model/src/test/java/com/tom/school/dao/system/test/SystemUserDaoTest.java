package com.tom.school.dao.system.test;

import org.junit.Before;
import org.junit.Test;

import com.tom.school.dao.sys.SystemUserDao;
import com.tom.school.model.sys.SystemUser;
import com.tom.school.test.TestContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;
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
		this.systemUserDao.persist(newUser); // persist

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

		this.systemUserDao.deleteByPK(user1.getId(), user2.getId(), user3.getId()); // deletByPK

		assertNull(this.systemUserDao.get(user1.getId()));
		assertNull(this.systemUserDao.get(user2.getId()));
		assertNull(this.systemUserDao.get(user3.getId()));
	}

	@Test
	public void testdeleteByProperties() {
		/*
		 * id = :id
		 */

		SystemUser user1;
		user1 = generateUser();
		this.systemUserDao.persist(user1);
		assertEquals(user1, this.systemUserDao.get(user1.getId()));

		String[] propName = new String[1];
		Object[] propValue = new Object[1];
		propName[0] = "id";
		propValue[0] = user1.getId();
		this.systemUserDao.deleteByProperties(propName, propValue);
		assertNull(this.systemUserDao.get(user1.getId()));

		/*
		 * id = :id and name = :name
		 */
		
		SystemUser user2;
		user2 = generateUser();
		this.systemUserDao.persist(user2);
		assertEquals(user2, this.systemUserDao.get(user2.getId()));

		propName = new String[2];
		propValue = new Object[2];
		propName[0] = "id";
		propValue[0] = user2.getId();
		propName[1] = "name";
		propValue[1] = user2.getName();
		this.systemUserDao.deleteByProperties(propName, propValue);
		assertNull(this.systemUserDao.get(user2.getId()));

		/*
		 * id in(value1, value2), parameter is array
		 */
		
		SystemUser user3, user4;
		user3 = generateUser();
		user4 = generateUser();
		this.systemUserDao.persist(user3);
		this.systemUserDao.persist(user4);
		assertEquals(user3, this.systemUserDao.get(user3.getId()));
		assertEquals(user4, this.systemUserDao.get(user4.getId()));

		this.systemUserDao.deleteByProperties("id", new Object[] { user3.getId(), user4.getId() });
		assertNull(this.systemUserDao.get(user3.getId()));
		assertNull(this.systemUserDao.get(user4.getId()));

		/*
		 * id in(value1, value2), parameter is list
		 */
		
		SystemUser user5, user6;
		user5 = generateUser();
		user6 = generateUser();
		this.systemUserDao.persist(user5);
		this.systemUserDao.persist(user6);
		assertEquals(user5, this.systemUserDao.get(user5.getId()));
		assertEquals(user6, this.systemUserDao.get(user6.getId()));

		List<String> userNameList = new ArrayList<String>();
		userNameList.add(user5.getName());
		userNameList.add(user6.getName());
		this.systemUserDao.deleteByProperties("name", userNameList);
		assertNull(this.systemUserDao.get(user5.getId()));
		assertNull(this.systemUserDao.get(user6.getId()));
	}
	
	@Test
	public void testUpdate(){
		SystemUser user = generateUser();
		this.systemUserDao.persist(user);
		assertEquals(user, this.systemUserDao.get(user.getId()));
		
		updateUser(user);
		this.systemUserDao.update(user);
		assertEquals(user, this.systemUserDao.get(user.getId()));
		
		this.systemUserDao.delete(user);
		assertNull(this.systemUserDao.get(user.getId()));
	}

	private SystemUser generateUser() {
		SystemUser user = new SystemUser();
		user.setName("Tom" + (new Random()).nextInt(10000));
		user.setPassword("Cogent01");
		return user;
	}
	
	private void updateUser(SystemUser user){
		user.setName("Jack" + (new Random()).nextInt(10000));
		user.setPassword("Cogent01");
	}

}

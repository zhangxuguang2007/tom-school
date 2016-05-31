package com.tom.school.dao.system.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.tom.school.dao.sys.SystemUserDao;
import com.tom.school.model.sys.SystemUser;
import com.tom.school.test.TestContext;

public class SystemUserDaoTest {

	private SystemUserDao systemUserDao;
	private List<SystemUser> userList = new ArrayList<SystemUser>();
	private int userIndex = 0;

	@Before
	public void init() {
		this.systemUserDao = TestContext.getSystemUserDao();

		// Test persist
		SystemUser user = generateUser();
		this.systemUserDao.persist(user);
		assertEquals(user, this.systemUserDao.get(user.getId()));

		// Test delete
		this.systemUserDao.delete(user);
		assertNull(this.systemUserDao.get(user.getId()));
	}

	@After
	public void clear() {
		for (SystemUser user : this.userList) {
			if (this.systemUserDao.get(user.getId()) != null) {
				this.systemUserDao.delete(user);
			}
		}
	}

	@Test
	public void testDeleteByPK() {
		SystemUser user1 = addUserToDB();
		SystemUser user2 = addUserToDB();
		SystemUser user3 = addUserToDB();

		this.systemUserDao.deleteByPK(user1.getId(), user2.getId(),
				user3.getId()); // deletByPK

		assertNull(this.systemUserDao.get(user1.getId()));
		assertNull(this.systemUserDao.get(user2.getId()));
		assertNull(this.systemUserDao.get(user3.getId()));
	}

	@Test
	public void testdeleteByProperties() {
		SystemUser user1, user2;

		/*
		 * id = :id
		 */

		user1 = addUserToDB();
		String[] propName = new String[1];
		Object[] propValue = new Object[1];
		propName[0] = "id";
		propValue[0] = user1.getId();
		this.systemUserDao.deleteByProperties(propName, propValue);
		assertNull(this.systemUserDao.get(user1.getId()));

		/*
		 * id = :id and name = :name
		 */

		user1 = addUserToDB();
		propName = new String[2];
		propValue = new Object[2];
		propName[0] = "id";
		propValue[0] = user1.getId();
		propName[1] = "name";
		propValue[1] = user1.getName();
		this.systemUserDao.deleteByProperties(propName, propValue);
		assertNull(this.systemUserDao.get(user1.getId()));

		/*
		 * id in(value1, value2), parameter is array
		 */

		user1 = addUserToDB();
		user2 = addUserToDB();
		this.systemUserDao.deleteByProperties("id",
				new Object[] { user1.getId(), user2.getId() });
		assertNull(this.systemUserDao.get(user1.getId()));
		assertNull(this.systemUserDao.get(user2.getId()));

		/*
		 * id in(value1, value2), parameter is list
		 */

		user1 = addUserToDB();
		user2 = addUserToDB();
		List<String> userNameList = new ArrayList<String>();
		userNameList.add(user1.getName());
		userNameList.add(user2.getName());
		this.systemUserDao.deleteByProperties("name", userNameList);
		assertNull(this.systemUserDao.get(user1.getId()));
		assertNull(this.systemUserDao.get(user2.getId()));
	}

	@Test
	public void testUpdate() {
		SystemUser user = addUserToDB();
		modifyUser(user);
		this.systemUserDao.update(user);
		assertEquals(user, this.systemUserDao.get(user.getId()));
	}

	@Test
	public void updateByProperties() {
		/*
		 * id = :id
		 */

		SystemUser user1 = addUserToDB();

		String[] propName = new String[2];
		Object[] propValue = new Object[2];
		propName[0] = "name";
		propValue[0] = user1.getName() + (new Random()).nextInt(10000);
		propName[1] = "password";
		propValue[1] = user1.getPassword() + (new Random()).nextInt(10000);

		String[] conditionName = new String[1];
		Object[] conditionValue = new Object[1];
		conditionName[0] = "id";
		conditionValue[0] = user1.getId();

		this.systemUserDao.updateByProperties(conditionName, conditionValue,
				propName, propValue);

		SystemUser updatedUser = this.systemUserDao.get(user1.getId());
		assertEquals(updatedUser.getName(), propValue[0]);
		assertEquals(updatedUser.getPassword(), propValue[1]);
	}

	private SystemUser addUserToDB() {
		SystemUser newUser = generateUser();
		this.userList.add(newUser);
		this.systemUserDao.persist(newUser);
		return newUser;
	}

	private SystemUser generateUser() {
		SystemUser user = new SystemUser();
		user.setName("Tom" + System.currentTimeMillis() + this.userIndex++);
		user.setPassword("Cogent01");
		return user;
	}

	private void modifyUser(SystemUser user) {
		user.setName("Jack" + System.currentTimeMillis() + this.userIndex++);
		user.setPassword("Cogent01");
	}
}

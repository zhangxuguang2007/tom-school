package com.tom.school.dao.system.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.List;

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
		 * where : id = :id
		 */

		user1 = addUserToDB();
		String[] propName = new String[1];
		Object[] propValue = new Object[1];
		propName[0] = "id";
		propValue[0] = user1.getId();
		this.systemUserDao.deleteByProperties(propName, propValue);
		assertNull(this.systemUserDao.get(user1.getId()));

		/*
		 * where : id = :id, name = :name
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
		 * where : id in array(:id1, :id2)
		 */

		user1 = addUserToDB();
		user2 = addUserToDB();
		this.systemUserDao.deleteByProperties("id",
				new Object[] { user1.getId(), user2.getId() });
		assertNull(this.systemUserDao.get(user1.getId()));
		assertNull(this.systemUserDao.get(user2.getId()));

		/*
		 * where : name in list(:name1, :name2)
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
	public void testUpdateByProperties() {
		SystemUser user1, user2;

		/*
		 * where : id = :id
		 */

		user1 = addUserToDB();

		// Condition
		String[] conditionName = new String[1];
		Object[] conditionValue = new Object[1];
		conditionName[0] = "id";
		conditionValue[0] = user1.getId();

		// Property
		modifyUser(user1);
		String[] propName = new String[2];
		Object[] propValue = new Object[2];
		propName[0] = "name";
		propValue[0] = user1.getName();
		propName[1] = "password";
		propValue[1] = user1.getPassword();

		this.systemUserDao.updateByProperties(conditionName, conditionValue,
				propName, propValue);

		SystemUser updatedUser = this.systemUserDao.get(user1.getId());
		assertEquals(updatedUser.getName(), user1.getName());
		assertEquals(updatedUser.getPassword(), user1.getPassword());

		/*
		 * where : id = :id, name = :name
		 */

		user1 = addUserToDB();

		// Condition
		conditionName = new String[2];
		conditionValue = new Object[2];
		conditionName[0] = "id";
		conditionValue[0] = user1.getId();
		conditionName[1] = "name";
		conditionValue[1] = user1.getName();

		// Property
		modifyUser(user1);
		propName = new String[2];
		propValue = new Object[2];
		propName[0] = "name";
		propValue[0] = user1.getName();
		propName[1] = "password";
		propValue[1] = user1.getPassword();

		this.systemUserDao.updateByProperties(conditionName, conditionValue,
				propName, propValue);

		updatedUser = this.systemUserDao.get(user1.getId());
		assertEquals(updatedUser.getName(), user1.getName());
		assertEquals(updatedUser.getPassword(), user1.getPassword());

		/*
		 * where : id in array(:id1, :id2)
		 */

		user1 = addUserToDB();
		user2 = addUserToDB();

		// Condition
		conditionName = new String[1];
		conditionValue = new Object[1];
		conditionName[0] = "id";
		conditionValue[0] = new Object[] { user1.getId(), user2.getId() };

		// Property
		String newPassword = generateUser().getPassword();
		propName = new String[1];
		propValue = new Object[1];
		propName[0] = "password";
		propValue[0] = newPassword;

		this.systemUserDao.updateByProperties(conditionName, conditionValue,
				propName, propValue);

		updatedUser = this.systemUserDao.get(user1.getId());
		assertEquals(updatedUser.getPassword(), newPassword);
		updatedUser = this.systemUserDao.get(user2.getId());
		assertEquals(updatedUser.getPassword(), newPassword);

		/*
		 * where : name in list(:name1, :name2)
		 */

		user1 = addUserToDB();
		user2 = addUserToDB();

		// Condition
		conditionName = new String[1];
		conditionValue = new Object[1];
		List<String> userNameList = new ArrayList<String>();
		userNameList.add(user1.getName());
		userNameList.add(user2.getName());
		conditionName[0] = "name";
		conditionValue[0] = userNameList;

		// Property
		newPassword = generateUser().getPassword();
		propName = new String[1];
		propValue = new Object[1];
		propName[0] = "password";
		propValue[0] = newPassword;

		this.systemUserDao.updateByProperties(conditionName, conditionValue,
				propName, propValue);

		updatedUser = this.systemUserDao.get(user1.getId());
		assertEquals(updatedUser.getPassword(), newPassword);
		updatedUser = this.systemUserDao.get(user2.getId());
		assertEquals(updatedUser.getPassword(), newPassword);

		/**
		 * polymorphic function1
		 */

		user1 = addUserToDB();

		// Condition
		String singleConditionName = "id";
		Object singleConditionValue = user1.getId();

		// Property
		modifyUser(user1);
		propName = new String[2];
		propValue = new Object[2];
		propName[0] = "name";
		propValue[0] = user1.getName();
		propName[1] = "password";
		propValue[1] = user1.getPassword();

		this.systemUserDao.updateByProperties(singleConditionName,
				singleConditionValue, propName, propValue);

		updatedUser = this.systemUserDao.get(user1.getId());
		assertEquals(updatedUser.getName(), user1.getName());
		assertEquals(updatedUser.getPassword(), user1.getPassword());

		/*
		 * polymorphic function2
		 */

		user1 = addUserToDB();

		// Condition
		conditionName = new String[1];
		conditionValue = new Object[1];
		conditionName[0] = "id";
		conditionValue[0] = user1.getId();

		// Property
		modifyUser(user1);
		String singlePropName = "name";
		Object singlePropVale = user1.getName();

		this.systemUserDao.updateByProperties(conditionName, conditionValue,
				singlePropName, singlePropVale);

		updatedUser = this.systemUserDao.get(user1.getId());
		assertEquals(updatedUser.getName(), user1.getName());

		/*
		 * polymorphic function3
		 */

		user1 = addUserToDB();

		// Condition
		singleConditionName = "id";
		singleConditionValue = user1.getId();

		// Property
		modifyUser(user1);
		singlePropName = "name";
		singlePropVale = user1.getName();

		this.systemUserDao.updateByProperties(singleConditionName,
				singleConditionValue, singlePropName, singlePropVale);

		updatedUser = this.systemUserDao.get(user1.getId());
		assertEquals(updatedUser.getName(), user1.getName());
	}

	@Test
	public void testMerge() {
		SystemUser user1 = addUserToDB();
		SystemUser user2 = this.systemUserDao.merge(user1);
		assertFalse(user1 == user2);
		assertEquals(user1, user2);
	}

	private SystemUser addUserToDB() {
		SystemUser newUser = generateUser();
		this.userList.add(newUser);
		this.systemUserDao.persist(newUser);
		return newUser;
	}

	private SystemUser generateUser() {
		SystemUser user = new SystemUser();
		user.setName("name_" + System.currentTimeMillis() + this.userIndex++);
		user.setPassword("password_" + System.currentTimeMillis()
				+ this.userIndex++);
		return user;
	}

	private void modifyUser(SystemUser user) {
		user.setName(user.getName() + "*");
		user.setPassword(user.getPassword() + "*");
	}
}

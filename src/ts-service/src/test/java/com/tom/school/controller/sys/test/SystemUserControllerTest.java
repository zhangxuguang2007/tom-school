package com.tom.school.controller.sys.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.tom.school.model.sys.SystemUser;
import com.tom.school.test.TestContext;
import com.tom.school.utility.HttpUtility;
import com.tom.school.utility.JsonUtility;

public class SystemUserControllerTest {

	@Before
	public void set() {
	}

	@Test
	public void testGetUser() {
		Long id = new Long(13);
		String jsonResponse = HttpUtility.readString(TestContext.ServiceUrl
				+ String.format("/sys/user/getUser/%s", id));
		SystemUser user = JsonUtility.decode(jsonResponse, SystemUser.class);
		if (user != null) {
			assertEquals(user.getId(), id);
			System.out.println(user);
		} else {
			System.out.println("Can't get the system user[" + id + "]");
		}
	}

}

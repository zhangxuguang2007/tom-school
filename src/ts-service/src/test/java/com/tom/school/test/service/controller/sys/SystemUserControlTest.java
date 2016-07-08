package com.tom.school.test.service.controller.sys;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.tom.school.model.sys.SystemUser;
import com.tom.school.support.HttpRequestResult;
import com.tom.school.test.service.TestContext;
import com.tom.school.utility.HttpRequestUtility;
import com.tom.school.utility.JsonUtility;

public class SystemUserControlTest {

	@Test
	public void test() {
		Long userId = Long.valueOf(5332);
		String url = TestContext.ServiceUrl + String.format("/sys/user/%s?token=%s", userId, TestContext.Token);
		HttpRequestResult responseResult = HttpRequestUtility.doGet(url);
		String jsonStr = new String(responseResult.getData());
		SystemUser user = JsonUtility.decode(jsonStr, SystemUser.class);
		assertEquals(responseResult.getStatus(), 200);
		assertEquals(userId, user.getId());
		assertEquals("Tom", user.getName());
		assertEquals("Cogent01", user.getPassword());
	}

}

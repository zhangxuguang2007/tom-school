package com.tom.school.controller.sys.test;

import static junit.framework.Assert.assertEquals;

import org.junit.Test;

import com.tom.school.model.sys.SystemUser;
import com.tom.school.support.HResult;
import com.tom.school.test.TestContext;
import com.tom.school.utility.HttpUtility;
import com.tom.school.utility.JsonUtility;

@SuppressWarnings("deprecation")
public class SystemUserControlTest {
	
	@Test
	public void test(){
		
		Long userId = Long.valueOf(5332);
		String url = TestContext.ServiceUrl + String.format("/sys/user/%s?token=%s", userId, TestContext.Token);
		HResult result = HttpUtility.read(url);
		result.getResult();
		SystemUser user = JsonUtility.decode(result.getResult().toString(), SystemUser.class);
		assertEquals(userId, user.getId());
		assertEquals("Tom", user.getName());
		assertEquals("Cogent01", user.getPassword());
	}
	
}

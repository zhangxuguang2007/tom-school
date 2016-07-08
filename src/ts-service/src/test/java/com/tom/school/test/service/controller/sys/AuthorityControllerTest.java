package com.tom.school.test.service.controller.sys;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.tom.school.support.HttpReponseError;
import com.tom.school.support.HttpRequestResult;
import com.tom.school.test.service.TestContext;
import com.tom.school.utility.HttpRequestUtility;
import com.tom.school.utility.JsonUtility;

public class AuthorityControllerTest {

	@Test
	public void testLogin() {
		/*
		 * Missing arguments
		 */
		String url = TestContext.ServiceUrl + "/authority/login";
		HttpRequestResult responseResult = HttpRequestUtility.doGet(url);
		String errorStr = new String(responseResult.getData());
		HttpReponseError error = JsonUtility.decode(errorStr, HttpReponseError.class);
		assertEquals(error.getError_code(), HttpReponseError.MISSING_ARGUMENTS.getError_code());
		assertTrue(error.getError().equals(HttpReponseError.MISSING_ARGUMENTS.getError()));

		/*
		 * Authority error
		 */
		url = TestContext.ServiceUrl + "/authority/login?name=jack&password=Cogent01";
		responseResult = HttpRequestUtility.doGet(url);
		errorStr = new String(responseResult.getData());
		error = JsonUtility.decode(errorStr, HttpReponseError.class);
		assertEquals(error.getError_code(), HttpReponseError.AUTHORIZATION_ERROR.getError_code());
		assertTrue(error.getError().equals(HttpReponseError.AUTHORIZATION_ERROR.getError()));

		/*
		 * Login success
		 */
		url = TestContext.ServiceUrl + "/authority/login?name=tom&password=Cogent01";
		responseResult = HttpRequestUtility.doGet(url);
		assertEquals(responseResult.getStatus(), 200);
		TestContext.Token = new String(responseResult.getData());
		System.out.println("token:" + TestContext.Token);
	}

}

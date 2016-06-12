package com.tom.school.controller.sys.test;

import static junit.framework.Assert.*;

import org.junit.Test;

import com.tom.school.support.HInnerError;
import com.tom.school.support.HResult;
import com.tom.school.test.TestContext;
import com.tom.school.utility.HttpUtility;
import com.tom.school.utility.JsonUtility;

@SuppressWarnings("deprecation")
public class AuthorityControllerTest {
	
	@Test
	public void testLogin(){
		/*
		 * Missing arguments
		 */
		String url = TestContext.ServiceUrl + "/authority/login";
		HResult result = HttpUtility.read(url);
		HInnerError error = JsonUtility.decode(result.getResult().toString(), HInnerError.class);
		System.out.println(JsonUtility.encode(error));
		assertEquals(HInnerError.MISSING_ARGUMENTS.getHttpStatus(), error.getHttpStatus());
		assertEquals(HInnerError.MISSING_ARGUMENTS.getError_code(), error.getError_code());
		
		/*
		 * Authority error
		 */
		url = TestContext.ServiceUrl + "/authority/login?name=jack&password=Cogent01";
		result = HttpUtility.read(url);
		error = JsonUtility.decode(result.getResult().toString(), HInnerError.class);
		System.out.println(JsonUtility.encode(error));
		assertEquals(HInnerError.AUTHORIZATION_ERROR.getHttpStatus(), error.getHttpStatus());
		assertEquals(HInnerError.AUTHORIZATION_ERROR.getError_code(), error.getError_code());
		
		/*
		 * Login success
		 */
		url = TestContext.ServiceUrl + "/authority/login?name=tom&password=Cogent01";
		result = HttpUtility.read(url);
		assertEquals(result.getStatus(), 200);
		TestContext.Token = result.getResult().toString();
		System.out.println("token:" + TestContext.Token);
	}
	
}

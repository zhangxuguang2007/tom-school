package com.tom.school.test.service.controller.sys;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.tom.school.core.entity.HttpReponseError;
import com.tom.school.core.entity.HttpRequestResult;
import com.tom.school.core.utility.HttpRequestUtility;
import com.tom.school.core.utility.JsonUtility;
import com.tom.school.db.model.sys.Authority;
import com.tom.school.rest.core.ListView;
import com.tom.school.test.service.TestContext;

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
	
	@Test
	public void testList(){
		String url = TestContext.ServiceUrl + String.format("/authority/list?token=%s", TestContext.Token);
		HttpRequestResult responseResult = HttpRequestUtility.doGet(url);
		String jsonStr = new String(responseResult.getData());
		JSONObject jsonObject = new JSONObject(jsonStr);
		int totalRecord = jsonObject.getInt("totalRecord");
		JSONArray jsonArray = jsonObject.getJSONArray("data");
		assertEquals(jsonArray.length(), totalRecord);
		for(int i = 0; i < jsonArray.length(); i++){
			JSONObject jsonAuthorityObject = jsonArray.getJSONObject(i);
			Authority authority = JsonUtility.decode(jsonAuthorityObject.toString(), Authority.class);
			//System.out.println(authority);
		}
	}

}

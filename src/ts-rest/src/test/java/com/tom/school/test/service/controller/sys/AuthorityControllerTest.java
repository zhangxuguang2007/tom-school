package com.tom.school.test.service.controller.sys;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;

import com.tom.school.core.http.HttpReponseError;
import com.tom.school.core.http.HttpRequestResult;
import com.tom.school.core.http.HttpRequestUtility;
import com.tom.school.core.json.JsonUtility;
import com.tom.school.db.model.sys.Authority;
import com.tom.school.test.service.TestContext;

public class AuthorityControllerTest {
	
	@BeforeClass
	public static void setupBeforeClass() {
		testLogin();
	}

	private static void testLogin() {
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
	public void testCheckToken(){
		String url = TestContext.ServiceUrl + String.format("/authority/checkToken?token=%s", TestContext.Token);
		HttpRequestResult responseResult = HttpRequestUtility.doGet(url);
		String resultStr = new String(responseResult.getData());
		assertEquals("true", resultStr);
		
		url = TestContext.ServiceUrl + String.format("/authority/checkToken?token=%s", UUID.randomUUID());
		responseResult = HttpRequestUtility.doGet(url);
		resultStr = new String(responseResult.getData());
		assertEquals("false", resultStr);
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
			System.out.println(authority);
		}
	}
	
	@Test
	public void testGetAuthority(){
		String url = TestContext.ServiceUrl + String.format("/authority/getAuthority?token=%s", TestContext.Token);
		HttpRequestResult responseResult = HttpRequestUtility.doGet(url);
		String jsonStr = new String(responseResult.getData());
		JSONArray parentMenuJsonArray = new JSONArray(jsonStr);
		for(int i = 0; i < parentMenuJsonArray.length(); i++){
			JSONObject parentMenuJsonObject = parentMenuJsonArray.getJSONObject(i);
			assertFalse(parentMenuJsonObject.getBoolean("leaf"));
			
			JSONArray childMenuJsonArray =  parentMenuJsonObject.getJSONArray("children");
			for(int j = 0; j < childMenuJsonArray.length(); j++){
				JSONObject childMenuJsonObject = childMenuJsonArray.getJSONObject(j);
				assertTrue(childMenuJsonObject.getBoolean("leaf"));
			}
		}
		System.out.println(jsonStr);
	}

}

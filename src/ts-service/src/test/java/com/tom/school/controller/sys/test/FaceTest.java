package com.tom.school.controller.sys.test;

import java.net.URLEncoder;

import org.junit.Test;

public class FaceTest {

	@Test
	public void test(){
		String imageUrl = "http://b.hiphotos.baidu.com/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=9db9758da6c27d1eb12b33967abcc60b/21a4462309f79052d1a480170ef3d7ca7bcbd564.jpg";
		imageUrl = URLEncoder.encode(imageUrl);
		String api_key = "0572613ba7a345fdcf53d409bd66b863";
		String api_secret = "mfCxzAHo8QpMxFrmoiasoz1gjX9A6JSF";
		String url = String.format("http://apicn.faceplusplus.com/v2/detection/detect?api_key=%s&api_secret=%S&url=%s&attribute=glass,pose,gender,age,race,smiling", api_key, api_secret, imageUrl);
		System.out.println(url);
	}
	
}

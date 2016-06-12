package com.tom.school.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestContext {

	public static String ServiceUrl;
	public static String Token;
	
	static{
		try {
			Properties properties = new Properties();
			InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("test.properties");
			properties.load(is);
			ServiceUrl = properties.getProperty("server.url");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}

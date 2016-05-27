package com.tom.school.utility;

import java.io.IOException;
import java.io.StringWriter;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtility {

	public static String encode(Object obj) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			StringWriter stringWriter = new StringWriter();
			objectMapper.writeValue(stringWriter, obj);
			return stringWriter.toString();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T decode(String jsonStr, Class<T> clazz) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			return (T) objectMapper.readValue(jsonStr, clazz);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}

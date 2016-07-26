package com.tom.school.core.utility;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

import com.fasterxml.jackson.databind.JavaType;
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

	public static <T> T decode(String jsonStr, Class<T> clazz) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			return (T) objectMapper.readValue(jsonStr, clazz);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static <T> List<T> decodeToList(String jsonStr, Class<T> clazz) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, clazz);
			return objectMapper.readValue(jsonStr, javaType);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}

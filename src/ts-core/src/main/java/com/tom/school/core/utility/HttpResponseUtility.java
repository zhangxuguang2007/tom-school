package com.tom.school.core.utility;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.tom.school.core.entity.ContentType;
import com.tom.school.core.entity.HttpReponseError;

public class HttpResponseUtility {
	
	public static void innerError(HttpServletResponse response){
		write(response, HttpReponseError.INTERNAL_ERROR);
	}

	public static void autorityError(HttpServletResponse response) {
		write(response, HttpReponseError.AUTHORIZATION_ERROR);
	}

	public static void missingArugment(HttpServletResponse response) {
		write(response, HttpReponseError.MISSING_ARGUMENTS);
	}

	public static void invalidArugment(HttpServletResponse response) {
		write(response, HttpReponseError.INVALID_ARGUMENTS);
	}

	private static void write(HttpServletResponse response, HttpReponseError error) {
		response.setStatus(error.getStatus());
		write(response, JsonUtility.encode(error));
	}

	public static void write(HttpServletResponse response, String content, ContentType... contentTypes) {
		if (contentTypes != null && contentTypes.length > 0) {
			ContentType type = contentTypes[0];
			switch (type) {
			case JSON:
				response.setContentType("application/json; charset=utf-8");
				break;
			case XML:
				response.setContentType("application/xml; charset=utf-8");
				break;
			case Text_PLAIN:
				response.setContentType("text/plain; charset=utf-8");
				break;
			case OSTET_STREAM:
				response.setContentType("application/ostet-stream");
				break;
			default:
				response.setContentType("application/json; charset=utf-8");
				break;
			}
		} else {
			response.setContentType("application/json; charset=utf-8");
		}

		PrintWriter out;
		try {
			out = response.getWriter();
			out.write(content);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}

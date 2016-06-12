package com.tom.school.utility;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.tom.school.support.ContentType;
import com.tom.school.support.ErrorResult;
import com.tom.school.support.InnerErrorResult;

public class HttpResponseUtility {

	public static void autorityError(HttpServletResponse response) {
		setResponse(response, InnerErrorResult.AUTHORIZATION_ERROR);
	}

	public static void missingArugment(HttpServletResponse response) {
		setResponse(response, InnerErrorResult.MISSING_ARGUMENTS);
	}

	public static void invalidArugment(HttpServletResponse response) {
		setResponse(response, InnerErrorResult.INVALID_ARGUMENTS);
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
			case Text:
				response.setContentType("text/plain; charset=utf-8");
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

	private static void setResponse(HttpServletResponse response, InnerErrorResult errorResult) {
		response.setContentType("application/json; charset=utf-8");
		response.setStatus(errorResult.getHttpStatus());
		PrintWriter out;
		try {
			out = response.getWriter();
			out.write(JsonUtility.encode((ErrorResult) errorResult));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

package com.tom.school.utility;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.tom.school.support.ContentType;
import com.tom.school.support.HError;
import com.tom.school.support.HInnerError;
import com.tom.school.support.HResult;

public class HttpUtility {

	public static void autorityError(HttpServletResponse response) {
		write(response, HInnerError.AUTHORIZATION_ERROR);
	}

	public static void missingArugment(HttpServletResponse response) {
		write(response, HInnerError.MISSING_ARGUMENTS);
	}

	public static void invalidArugment(HttpServletResponse response) {
		write(response, HInnerError.INVALID_ARGUMENTS);
	}

	private static void write(HttpServletResponse response, HInnerError errorResult) {
		response.setStatus(errorResult.getHttpStatus());
		write(response, JsonUtility.encode((HError) errorResult));
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

	public static HResult read(String url) {
		CloseableHttpClient closeableHttpClient = HttpClientBuilder.create().build();
		HttpGet httpGet = new HttpGet(url);
		try {
			HttpResponse httpResponse = closeableHttpClient.execute(httpGet);
			HttpEntity httpEntity = httpResponse.getEntity();

			HResult result = new HResult();
			result.setStatus(httpResponse.getStatusLine().getStatusCode());
			result.setResult(new String(EntityUtils.toByteArray(httpEntity), "utf8"));
			return result;

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				closeableHttpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}

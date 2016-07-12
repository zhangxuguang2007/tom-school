package com.tom.school.core.utility;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.tom.school.core.entity.HttpRequestResult;

public class HttpRequestUtility {

	public static HttpRequestResult doGet(String url) {
		CloseableHttpClient closeableHttpClient = HttpClientBuilder.create().build();
		HttpGet httpGet = new HttpGet(url);
		try {
			HttpResponse httpResponse = closeableHttpClient.execute(httpGet);
			HttpEntity httpEntity = httpResponse.getEntity();
			
			HttpRequestResult result = new HttpRequestResult();
			result.setStatus(httpResponse.getStatusLine().getStatusCode());
			result.setData(EntityUtils.toByteArray(httpEntity));
			return result;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				closeableHttpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static HttpRequestResult doPost(String url, String body) {
		CloseableHttpClient closeableHttpClient = HttpClientBuilder.create().build();
		try {
			HttpPost httpPost = new HttpPost(url);
			httpPost.setHeader("Content-Type", "text/plain");
			if(body != null && !"".equals(body)){
				httpPost.setEntity(new StringEntity(body));
			}
			
			HttpResponse httpResponse = closeableHttpClient.execute(httpPost);
			HttpEntity httpEntity = httpResponse.getEntity();
			
			HttpRequestResult result = new HttpRequestResult();
			result.setStatus(httpResponse.getStatusLine().getStatusCode());
			result.setData(EntityUtils.toByteArray(httpEntity));
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}

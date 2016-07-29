package com.tom.school.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.tom.school.core.http.HttpRequestResult;
import com.tom.school.core.http.HttpRequestUtility;
import com.tom.school.web.core.ServerContext;

public class AdminAuthorityFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServerletRequest = (HttpServletRequest) request;
		Object token = httpServerletRequest.getSession().getAttribute("token");
		if(token == null){
			login(httpServerletRequest.getSession());
		} else {
			String checkTokenUrl = ServerContext.BaseRESTUrl + "authority/checkToken?token=" + token;
			HttpRequestResult responseResult = HttpRequestUtility.doGet(checkTokenUrl);
			Boolean checkResult = Boolean.valueOf(new String(responseResult.getData()));
			if(!checkResult){
				login(httpServerletRequest.getSession());
			}
		}
		chain.doFilter(request, response);
	}
	
	private void login(HttpSession session){
		String loginUrl = ServerContext.BaseRESTUrl + "authority/login?name=tom&password=Cogent01";
		HttpRequestResult responseResult = HttpRequestUtility.doGet(loginUrl);
		session.setAttribute("token", new String(responseResult.getData()));
	}

	@Override
	public void destroy() {
	}

}

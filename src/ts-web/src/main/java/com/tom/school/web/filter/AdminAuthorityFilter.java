package com.tom.school.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.tom.school.core.entity.HttpRequestResult;
import com.tom.school.core.utility.HttpRequestUtility;
import com.tom.school.web.core.ServerContext;

public class AdminAuthorityFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServerletRequest = (HttpServletRequest) request;
		if(httpServerletRequest.getSession().getAttribute("token") == null){
			String loginUrl = ServerContext.BaseRESTUrl + "authority/login?name=tom&password=Cogent01";
			HttpRequestResult responseResult = HttpRequestUtility.doGet(loginUrl);
			httpServerletRequest.getSession().setAttribute("token", new String(responseResult.getData()));
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}

}

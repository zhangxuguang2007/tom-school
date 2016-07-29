package com.tom.school.rest.filter;

import static com.tom.school.core.http.HttpResponseUtility.autorityError;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tom.school.rest.core.security.SecurityTool;

public class AuthorityFilter implements Filter {

	private List<String> ignoreUrlList;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.ignoreUrlList = new ArrayList<String>();
		this.ignoreUrlList.add("authority/login");
		this.ignoreUrlList.add("authority/checkToken");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;

		if (!checkIgnoreUrl(httpServletRequest.getServletPath())) {
			String token = request.getParameter("token");
			if (!SecurityTool.checkToken(token)) {
				autorityError((HttpServletResponse) response);
			} else {
				chain.doFilter(request, response);
			}
		} else {
			chain.doFilter(request, response);
		}
	}

	private boolean checkIgnoreUrl(String url) {
		for (String ignoreUrl : this.ignoreUrlList) {
			if (url.toLowerCase().endsWith(ignoreUrl.toLowerCase())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void destroy() {
	}

}

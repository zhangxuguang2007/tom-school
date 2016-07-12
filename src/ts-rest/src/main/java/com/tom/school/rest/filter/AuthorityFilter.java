package com.tom.school.rest.filter;

import static com.tom.school.core.utility.HttpResponseUtility.autorityError;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tom.school.rest.core.Cache;

public class AuthorityFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		if (!httpServletRequest.getServletPath().toLowerCase().endsWith("authority/login")) {
			String token = request.getParameter("token");
			if (token == null || !Cache.Tokens.containsKey(token)) {
				autorityError((HttpServletResponse) response);
			} else {
				chain.doFilter(request, response);
			}
		} else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
	}

}

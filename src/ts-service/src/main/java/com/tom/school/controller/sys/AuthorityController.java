package com.tom.school.controller.sys;

import static com.tom.school.utility.HttpResponseUtility.*;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tom.school.service.core.Cache;
import com.tom.school.support.ContentType;

@Controller
@RequestMapping(value = "/authority")
public class AuthorityController {

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void login(HttpServletRequest request, HttpServletResponse response) {
		String userName = request.getParameter("name");
		String password = request.getParameter("password");
		if (userName == null || "".equals(userName) || password == null || "".equals(password)) {
			missingArugment(response);
		} else {
			if (userName.equals("tom") && password.equals("Cogent01")) {
				String token = UUID.randomUUID().toString();
				Cache.Tokens.put(token, userName);
				write(response, token, ContentType.Text_PLAIN);
			} else {
				autorityError(response);
			}
		}
	}

}

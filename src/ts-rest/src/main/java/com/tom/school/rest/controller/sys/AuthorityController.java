package com.tom.school.rest.controller.sys;

import static com.tom.school.core.utility.HttpResponseUtility.autorityError;
import static com.tom.school.core.utility.HttpResponseUtility.missingArugment;
import static com.tom.school.core.utility.HttpResponseUtility.write;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tom.school.core.entity.ContentType;
import com.tom.school.rest.core.Cache;

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
package com.tom.school.web.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tom.school.web.core.ServerContext;

@Controller
@RequestMapping(value="/admin")
public class AdminController {

	@RequestMapping(value="", method=RequestMethod.GET)
	public String index(HttpServletRequest request, Map<String, Object> map){
		map.put("token", request.getSession().getAttribute("token"));
		map.put("baseRESTUrl", ServerContext.BaseRESTUrl);
		return "admin/index";
	}
	
}

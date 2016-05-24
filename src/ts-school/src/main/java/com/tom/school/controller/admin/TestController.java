package com.tom.school.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/test")
public class TestController {
	
	@RequestMapping(value="/admin", method=RequestMethod.GET)
	public String admin(){
		return "admin";
	}
	
}

package com.tom.school.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/")
public class SchoolController {

	@RequestMapping(value="", method=RequestMethod.GET)
	public String index(){
		return "index";
	}
	
}

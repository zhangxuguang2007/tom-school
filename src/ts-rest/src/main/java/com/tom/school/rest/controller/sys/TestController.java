package com.tom.school.rest.controller.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tom.school.db.model.business.Student;

@Controller
@RequestMapping(value="/test")
public class TestController {
	
	private Student student = null;
	
	@Autowired
	public TestController(Student student){
		this.student = student;
	}
	
	@ResponseBody
	@RequestMapping(value="/admin", method=RequestMethod.GET)
	public Student admin(){
		return this.student;
	}
}

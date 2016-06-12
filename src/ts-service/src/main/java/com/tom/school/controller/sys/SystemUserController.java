package com.tom.school.controller.sys;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tom.school.model.sys.SystemUser;
import com.tom.school.service.sys.SystemUserService;

@Controller
@RequestMapping(value="/sys/user")
public class SystemUserController {

	@Resource
	private SystemUserService systemUserService;
	
	@ResponseBody
	@RequestMapping(value="/getUser/{id}", method=RequestMethod.GET)
	public SystemUser getUserById(@PathVariable Long id){
		return this.systemUserService.get(id);
	}
}

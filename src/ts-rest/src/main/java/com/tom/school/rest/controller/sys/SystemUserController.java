package com.tom.school.rest.controller.sys;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tom.school.db.model.sys.SystemUser;
import com.tom.school.rest.service.sys.SystemUserService;

@Controller
@RequestMapping(value="/sys/user")
public class SystemUserController {

	@Resource
	private SystemUserService systemUserService;
	
	@ResponseBody
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public SystemUser getUserById(@PathVariable Long id){
		return this.systemUserService.get(id);
	}
	
}

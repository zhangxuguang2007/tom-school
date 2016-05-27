package com.tom.school.controller.sys;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tom.school.model.sys.SystemUser;
import com.tom.school.service.sys.SystemUserService;

@Controller
@RequestMapping(value="/sys/user")
public class SystemUserController {

	private SystemUserService systemUserService;
	
	@Resource
	public void setSystemUserService(SystemUserService systemUserService){
		this.systemUserService = systemUserService;
	}
	
	@ResponseBody
	@RequestMapping(value="/test", method=RequestMethod.GET)
	public SystemUser test(){
		return this.systemUserService.get(new Long(17));
	}
}

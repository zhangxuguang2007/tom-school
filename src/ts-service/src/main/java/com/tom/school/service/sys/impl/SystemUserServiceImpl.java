package com.tom.school.service.sys.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tom.school.dao.sys.SystemUserDao;
import com.tom.school.model.sys.SystemUser;
import com.tom.school.service.core.BaseService;
import com.tom.school.service.sys.SystemUserService;

@Service
public class SystemUserServiceImpl extends BaseService<SystemUser> implements
		SystemUserService {

	private SystemUserDao systeUserDao;

	@Resource
	public void setSystemUserDao(SystemUserDao systemUserDao) {
		this.systeUserDao = systemUserDao;
		this.dao = systemUserDao;
	}

}

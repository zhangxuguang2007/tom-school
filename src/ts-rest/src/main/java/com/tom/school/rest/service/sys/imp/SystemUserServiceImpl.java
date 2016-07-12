package com.tom.school.rest.service.sys.imp;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tom.school.db.dao.sys.SystemUserDao;
import com.tom.school.db.model.sys.SystemUser;
import com.tom.school.rest.service.BaseService;
import com.tom.school.rest.service.sys.SystemUserService;

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
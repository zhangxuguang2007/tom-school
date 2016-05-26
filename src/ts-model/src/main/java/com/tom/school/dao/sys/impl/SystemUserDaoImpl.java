package com.tom.school.dao.sys.impl;

import com.tom.school.dao.core.BaseDao;
import com.tom.school.dao.sys.SystemUserDao;
import com.tom.school.model.sys.SystemUser;

public class SystemUserDaoImpl extends BaseDao<SystemUser> implements SystemUserDao {
	
	public SystemUserDaoImpl(){
		super(SystemUser.class);
	}

}

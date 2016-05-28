package com.tom.school.dao.sys.impl;

import org.springframework.stereotype.Repository;

import com.tom.school.core.dao.BaseDao;
import com.tom.school.dao.sys.SystemUserDao;
import com.tom.school.model.sys.SystemUser;

@Repository
public class SystemUserDaoImpl extends BaseDao<SystemUser> implements SystemUserDao {
	
	public SystemUserDaoImpl(){
		super(SystemUser.class);
	}

}

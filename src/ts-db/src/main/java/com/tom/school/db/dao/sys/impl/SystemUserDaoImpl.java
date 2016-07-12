package com.tom.school.db.dao.sys.impl;

import org.springframework.stereotype.Repository;

import com.tom.school.db.dao.BaseDao;
import com.tom.school.db.dao.sys.SystemUserDao;
import com.tom.school.db.model.sys.SystemUser;

@Repository
public class SystemUserDaoImpl extends BaseDao<SystemUser> implements SystemUserDao {
	
	public SystemUserDaoImpl(){
		super(SystemUser.class);
	}

}

package com.tom.school.dao.sys.impl;

import com.tom.school.dao.core.BaseDao;
import com.tom.school.dao.sys.SystemUserDao;
import com.tom.school.model.sys.SystemUser;

public class SystemUserDaoImpl extends BaseDao<SystemUser> implements SystemUserDao {
	
	public SystemUserDaoImpl(){
		super(SystemUser.class);
	}

//	private SessionFactory sessionFactory;
//
//	public SessionFactory getSessionFactory() {
//		return sessionFactory;
//	}
//
//	public void setSessionFactory(SessionFactory sessionFactory) {
//		this.sessionFactory = sessionFactory;
//	}
//
//	public Session getSession() {
//		return sessionFactory.getCurrentSession();
//	}
//
//	@Resource(name = "sessionFactory")
//	public void setSF(SessionFactory sessionFactory) {
//		setSessionFactory(sessionFactory);
//	}
//
//	@Override
//	public SystemUser get(int id) {
//		SystemUser user = new SystemUser();
//		user.setId(1);
//		user.setName("Tom");
//		user.setPassword("Cogent01");
//		return user;
//	}

}

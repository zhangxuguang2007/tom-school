package com.tom.school.rest.service.sys.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tom.school.db.dao.sys.AuthorityDao;
import com.tom.school.db.model.sys.Authority;
import com.tom.school.rest.service.BaseService;
import com.tom.school.rest.service.sys.AuthorityService;

@Service
public class AuthorityServiceImp extends BaseService<Authority> implements AuthorityService {

	private AuthorityDao authorityDao;
	
	@Resource
	public void setAuthorityDao(AuthorityDao authorityDao){
		this.authorityDao = authorityDao;
		this.dao = authorityDao;
	}

	@Override
	public List<Authority> queryByParentIdAndRole(Short role) {
		return this.authorityDao.queryByParentidAndRole(role);
	}

	@Override
	public List<Authority> queryChildrenByParentIdAndRole(Long parentId, Short role) {
		return this.authorityDao.queryChildrenByParentIdAndRole(parentId, role);
	}

}

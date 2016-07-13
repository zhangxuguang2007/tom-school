package com.tom.school.db.dao.sys;

import java.util.List;

import com.tom.school.db.dao.Dao;
import com.tom.school.db.model.sys.Authority;

public interface AuthorityDao extends Dao<Authority> {

	List<Authority> queryByParentidAndRole(Short role);
	List<Authority> queryChildrenByParentIdAndRole(Long parentId, Short role);
	
}

package com.tom.school.rest.service.sys;

import java.util.List;

import com.tom.school.db.model.sys.Authority;
import com.tom.school.rest.service.Service;

public interface AuthorityService extends Service<Authority> {

	List<Authority> queryByParentIdAndRole(Short role);
	
	List<Authority> queryChildrenByParentIdAndRole(Long parentId, Short role);
	
}

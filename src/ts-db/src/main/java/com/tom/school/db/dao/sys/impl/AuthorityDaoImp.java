package com.tom.school.db.dao.sys.impl;

import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.tom.school.db.dao.BaseDao;
import com.tom.school.db.dao.sys.AuthorityDao;
import com.tom.school.db.model.sys.Authority;

@Repository
public class AuthorityDaoImp extends BaseDao<Authority> implements AuthorityDao {

	public AuthorityDaoImp() {
		super(Authority.class);
	}

	@Override
	public List<Authority> queryParentByRole(Short role) {
		SQLQuery query = null;
		if (role != null) {
			query = getSession().createSQLQuery("select distinct a.* from authority a,role_authority ra where a.id = ra.authority_id and a.parent_id is null and ra.role = ?");
			query.setParameter(0, role);
		} else {
			query = getSession().createSQLQuery("select * from authority where authority.parent_id is null");
		}
		query.addEntity(Authority.class);
		return query.list();
	}

	@Override
	public List<Authority> queryChildrenByParentIdAndRole(Long parentId, Short role) {
		SQLQuery query = null;
		if (role != null)
		{
			query = getSession().createSQLQuery("select distinct a.* from authority a,role_authority ra where a.id = ra.authority_id and a.parent_id = ? and ra.role = ?");
			query.setParameter(0, parentId);
			query.setParameter(1, role);
		} else {
			query = getSession().createSQLQuery("select * from authority where authority.parent_id = ?");
			query.setParameter(0, parentId);
		}
		query.addEntity(Authority.class);
		return query.list();
	}
}

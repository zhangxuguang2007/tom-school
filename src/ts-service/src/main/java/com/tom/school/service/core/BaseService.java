package com.tom.school.service.core;

import java.io.Serializable;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tom.school.dao.core.Dao;

@Transactional
public class BaseService<E> implements Service<E> {

	protected Dao<E> dao;

	@Override
	public void persist(E entity) {
		this.dao.persist(entity);
	}

	@Override
	public void delete(E entity) {
		this.dao.delete(entity);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	@Override
	public E get(Serializable id) {
		return this.dao.get(id);
	}

}
package com.tom.school.rest.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tom.school.db.dao.Dao;
import com.tom.school.db.dao.QueryResult;
import com.tom.school.db.param.BaseParameter;

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

	@Override
	public void deleteByPK(Serializable... id) {
		this.dao.deleteByPK(id);
	}

	@Override
	public void deleteByProperties(String propName, Object propValue) {
		this.dao.deleteByProperties(propName, propValue);
	}

	@Override
	public void deleteByProperites(String[] propName, Object[] propValue) {
		this.dao.deleteByProperties(propName, propValue);
	}

	@Override
	public void update(E entity) {
		this.dao.update(entity);
	}

	@Override
	public void update(E entity, Serializable oldId) {
		this.dao.update(entity, oldId);
	}

	@Override
	public void updateByProperties(String[] conditionName, Object[] conditionValue, String[] propertyName, Object[] propertyValue) {
		this.dao.updateByProperties(conditionName, conditionValue, propertyName, propertyValue);
	}

	@Override
	public void updateByProperties(String[] conditionName, Object[] conditionValue, String propertyName, Object propertyValue) {
		this.dao.updateByProperties(conditionName, conditionValue, propertyName, propertyValue);
	}

	@Override
	public void updateByProperties(String conditionName, Object conditionValue, String[] propertyName, Object[] propertyValue) {
		this.dao.updateByProperties(conditionName, conditionValue, propertyName, propertyValue);
	}

	@Override
	public void updateByProperties(String conditionName, Object conditionValue, String propertyName, Object propertyValue) {
		this.dao.updateByProperties(conditionName, conditionValue, propertyName, propertyValue);
	}
	
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	@Override
	public E get(Serializable id) {
		return this.dao.get(id);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	@Override
	public E getByProperties(String[] propName, Object[] propValue) {
		return this.dao.getByProperties(propName, propValue);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	@Override
	public E getByProperties(String[] propName, Object[] propValue, Map<String, String> sortedCondition) {
		return this.dao.getByProperties(propName, propValue, sortedCondition);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	@Override
	public E getByProperties(String propName, Object propValue) {
		return this.dao.getByProperties(propName, propValue);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	@Override
	public E getByProperties(String propName, Object propValue, Map<String, String> sortedCondition) {
		return this.dao.getByProperties(propName, propValue, sortedCondition);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	@Override
	public List<E> queryByProperties(String[] propName, Object[] propValue, Map<String, String> sortedCondition, Integer top) {
		return this.dao.queryByProperties(propName, propValue, sortedCondition, top);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	@Override
	public List<E> queryByProperties(String[] propName, Object[] propValue, Map<String, String> sortedCondition) {
		return this.dao.queryByProperties(propName, propValue, sortedCondition);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	@Override
	public List<E> queryByProperties(String[] propName, Object[] propValue, Integer top) {
		return this.dao.queryByProperties(propName, propValue, top);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	@Override
	public List<E> queryByProperties(String[] propName, Object[] propValue) {
		return this.dao.queryByProperties(propName, propValue);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	@Override
	public List<E> queryByProperties(String propName, Object propValue, Map<String, String> sortedCondition, Integer top) {
		return this.dao.queryByProperties(propName, propValue, sortedCondition, top);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	@Override
	public List<E> queryByProperties(String propName, Object propValue, Map<String, String> sortedCondition) {
		return this.dao.queryByProperties(propName, propValue, sortedCondition);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	@Override
	public List<E> queryByProperties(String propName, Object propValue, Integer top) {
		return this.dao.queryByProperties(propName, propValue, top);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	@Override
	public List<E> queryByProperties(String propName, Object propValue) {
		return this.dao.queryByProperties(propName, propValue);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	@Override
	public List<E> doQueryAll() {
		return this.dao.doQueryAll();
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	@Override
	public List<E> doQueryAll(Map<String, String> sortedCondition, Integer top) {
		return this.dao.doQueryAll(sortedCondition, top);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	@Override
	public List<E> doQueryAll(Integer top) {
		return this.dao.doQueryAll(top);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	@Override
	public List<E> doQuery(BaseParameter parameter) {
		return this.dao.doQuery(parameter);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	@Override
	public QueryResult<E> doPaginationQuery(BaseParameter parameter) {
		return this.dao.doPaginationQuery(parameter);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	@Override
	public Long countAll() {
		return this.dao.countAll();
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	@Override
	public Long doCount(BaseParameter parameter) {
		return this.dao.doCount(parameter);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	@Override
	public E load(Serializable id) {
		return this.dao.load(id);
	}

	@Override
	public void clear() {
		this.dao.clear();
	}

	@Override
	public void evict() {
		this.evict();
	}

	@Override
	public E merge(E entity) {
		return this.dao.merge(entity);
	}

}

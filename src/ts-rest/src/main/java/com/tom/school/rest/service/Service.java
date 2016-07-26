package com.tom.school.rest.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.tom.school.db.dao.QueryResult;
import com.tom.school.db.param.BaseParameter;

public interface Service<E> {
	
	public void persist(E entity);
	
	public void delete(E entity);
	public void deleteByPK(Serializable...id);
	public void deleteByProperties(String propName, Object propValue);
	public void deleteByProperites(String[] propName, Object[] propValue);
	
	public void update(E entity);
	public void update(E entity, Serializable oldId);
	public void updateByProperties(String[] conditionName, Object[] conditionValue, String[] propertyName, Object[] propertyValue);
	public void updateByProperties(String[] conditionName, Object[] conditionValue, String propertyName, Object propertyValue);
	public void updateByProperties(String conditionName, Object conditionValue, String[] propertyName, Object[] propertyValue);
	public void updateByProperties(String conditionName, Object conditionValue, String propertyName, Object propertyValue);
	
	public E get(Serializable id);
	public E getByProperties(String[] propName, Object[] propValue);
	public E getByProperties(String[] propName, Object[] propValue, Map<String, String> sortedCondition);
	public E getByProperties(String propName, Object propValue);
	public E getByProperties(String propName, Object propValue, Map<String, String> sortedCondition);
	
	public List<E> queryByProperties(String[] propName, Object[] propValue, Map<String, String> sortedCondition, Integer top);
	public List<E> queryByProperties(String[] propName, Object[] propValue, Map<String, String> sortedCondition);
	public List<E> queryByProperties(String[] propName, Object[] propValue, Integer top);
	public List<E> queryByProperties(String[] propName, Object[] propValue);
	public List<E> queryByProperties(String propName, Object propValue, Map<String, String> sortedCondition, Integer top);
	public List<E> queryByProperties(String propName, Object propValue, Map<String, String> sortedCondition);
	public List<E> queryByProperties(String propName, Object propValue, Integer top);
	public List<E> queryByProperties(String propName, Object propValue);
	
	public List<E> doQueryAll();
	public List<E> doQueryAll(Map<String, String> sortedCondition, Integer top);
	public List<E> doQueryAll(Integer top);
	
	public List<E> doQuery(BaseParameter parameter);
	
	public QueryResult<E> doPaginationQuery(BaseParameter parameter);
	
	public Long countAll();
	
	public Long doCount(BaseParameter parameter);
	
	public E load(Serializable id);
	
	public void clear();
	
	public void evict();
	
	public E merge(E entity);
	
}

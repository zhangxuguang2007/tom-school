package com.tom.school.db.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.tom.school.db.dao.BaseParameter;
import com.tom.school.db.dao.QueryResult;

public interface Dao<E> {
	/**
	 * Save persistent object
	 * 
	 * @param entity
	 */
	public void persist(E entity);

	/**
	 * Delete persistent object by key
	 * 
	 * @param id
	 * @return indicate if delete successfully
	 */
	public boolean deleteByPK(Serializable... id);

	/**
	 * Delete persistent object
	 * 
	 * @param entity
	 */
	public void delete(E entity);

	/**
	 * Delete entity by property by HQL
	 * 
	 * @param propName
	 * @param propValue
	 */
	public void deleteByProperties(String propName, Object propValue);
	
	/**
	 * Delete entity by property by HQL
	 * 
	 * @param propName
	 * @param propValue
	 */
	public void deleteByProperties(String[] propName, Object[] propValue);

	/**
	 * Update the persistent instance with the identifier of the given detached
	 * instance.
	 * 
	 * @param entity
	 */
	public void update(E entity);

	/**
	 * update single property batch by condition batch
	 * 
	 * @param conditionName
	 * @param conditonValue
	 * @param propertyName
	 * @param propetyValue
	 */
	public void updateByProperties(String[] conditionName, Object[] conditonValue, String[] propertyName,
			Object[] propetyValue);

	/**
	 * update single property by condition batch
	 * 
	 * @param conditionName
	 * @param conditionValue
	 * @param propertyName
	 * @param propertyValue
	 */
	public void updateByProperties(String[] conditionName, Object[] conditionValue, String propertyName,
			Object propertyValue);

	/**
	 * update property batch by single condition
	 * 
	 * @param conditionName
	 * @param conditionValue
	 * @param propertyName
	 * @param propetyValue
	 */
	public void updateByProperties(String conditionName, Object conditionValue, String[] propertyName,
			Object[] propetyValue);

	/**
	 * update single property batch by single condition
	 * 
	 * @param conditionName
	 * @param conditionValue
	 * @param propertyName
	 * @param propertyValue
	 */
	public void updateByProperties(String conditionName, Object conditionValue, String propertyName,
			Object propertyValue);

	/**
	 * cautiously use this method, through delete then insert to update an
	 * entity when need to update primary key value (unsupported) use this
	 * method
	 * 
	 * @param entity
	 * @param oldId
	 */
	public void update(E entity, Serializable oldId);
	
	/**
	 * Merge the state of the given entity into the current persistence context.
	 * 
	 * @param entity
	 * @return
	 */
	public E merge(E entity);
	
	/**
	 * Get persistent object
	 * 
	 * @param id
	 * @return
	 */
	public E get(Serializable id);
	
	/**
	 * load persistent object
	 * 
	 * @param id
	 * @return
	 */
	public E load(Serializable id);
	
	/**
	 * c
	 * 
	 * @param propName
	 * @param propValue
	 * @return
	 */
	public E getByProperties(String[] propName, Object[] propValue);
	
	/**
	 * get an entity by properties and sorted condition
	 * 
	 * @param propName
	 * @param propValue
	 * @param soredCondition
	 * @return
	 */
	public E getByProperties(String[] propName, Object[] propValue, Map<String, String> sortedCondition);
	
	/**
	 * query by property
	 * 
	 * @param propName
	 * @param propValue
	 * @param sortedConidtion
	 * @param top
	 * @return
	 */
	public List<E> queryByProperties(String[] propName, Object[] propValue, Map<String, String> sortedConidtion, Integer top);
	
	/**
	 * query by property
	 * 
	 * @param propName
	 * @param propValue
	 * @param sortedCondition
	 * @return
	 */
	public List<E> queryByProperties(String[] propName, Object[] propValue, Map<String, String> sortedCondition);
	
	/**
	 * query by property
	 * 
	 * @param propName
	 * @param propValue
	 * @param top
	 * @return
	 */
	public List<E> queryByProperties(String[] propName, Object[] propValue, Integer top);
	
	/**
	 * query by property
	 * 
	 * @param propName
	 * @param propValue
	 * @return
	 */
	public List<E> queryByProperties(String[] propName, Object[] propValue);
	
	/**
	 * query by property
	 * 
	 * @param propName
	 * @param propValue
	 * @param sortedCondition
	 * @param top
	 * @return
	 */
	public List<E> queryByProperties(String propName, Object propValue, Map<String, String> sortedCondition, Integer top);
	
	/**
	 * query by property
	 * 
	 * @param propName
	 * @param propValue
	 * @param sortedCondition
	 * @return
	 */
	public List<E> queryByProperties(String propName, Object propValue, Map<String, String> sortedCondition);
	
	/**
	 * query by property
	 * 
	 * @param propName
	 * @param propValue
	 * @param top
	 * @return
	 */
	public List<E> queryByProperties(String propName, Object propValue, Integer top);
	
	/**
	 * query by property
	 * 
	 * @param propName
	 * @param propValue
	 * @return
	 */
	public List<E> queryByProperties(String propName, Object propValue);
	
	/**
	 * Completely clear the session.
	 */
	public void clear();
	
	/**
	 * Remove this instance from the session cache.
	 * 
	 * @param entity
	 */
	public void evict(E entity);
	
	/**
	 * count all
	 * @return
	 */
	public Long countAll();
	
	/**
	 * Query all
	 * 
	 * @return
	 */
	public List<E> doQueryAll();
	
	/**
	 * Query all
	 * 
	 * @param soredCondition
	 * @param top
	 * @return
	 */
	public List<E> doQueryAll(Map<String, String> soredCondition, Integer top);
	
	/**
	 * Query all
	 * 
	 * @param top
	 * @return
	 */
	public List<E> doQueryAll(Integer top);
	
	/**
	 * count by parameter
	 * 
	 * @param parameter
	 * @return
	 */
	public Long doCount(BaseParameter param);
	
	/**
	 * query by parameter
	 * 
	 * @param parameter
	 * @return
	 */
	public List<E> doQuery(BaseParameter param);
	
	/**
	 * pagination query
	 * 
	 * @param parameter
	 * @return
	 */
	public QueryResult<E> doPaginationQuery(BaseParameter param);
	
}

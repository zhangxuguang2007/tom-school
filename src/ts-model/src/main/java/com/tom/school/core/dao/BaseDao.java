package com.tom.school.core.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tom.school.core.support.BaseParameter;
import com.tom.school.core.support.QueryResult;

public class BaseDao<E> implements Dao<E> {

	private SessionFactory sessionFactory;

	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	protected Class<E> entityClass;

	public BaseDao(Class<E> entityClass) {
		this.entityClass = entityClass;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}

	@Resource(name = "sessionFactory")
	public void setSF(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	@Override
	public void persist(E entity) {
		Session session = sessionFactory.openSession();
	    Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(entity);
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		} finally {
			session.close();
		}
	}
	
	@Override
	public void delete(E entity) {
		Session session = sessionFactory.openSession();
	    Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.delete(entity);
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		} finally {
			session.close();
		}
	}

	@Override
	public E get(Serializable id) {
		Session session = sessionFactory.openSession();
		try{
			return session.get(this.entityClass, id);
		}finally{
			session.close();
		}
	}

	@Override
	public boolean deleteByPK(Serializable... id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void deleteByProperties(String propName, Object propValue) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(E entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateByProperties(String[] conditionName, Object[] conditonValue, String[] propertyName,
			String[] propetyValue) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateByProperties(String[] conditionName, Object[] conditionValue, String propertyName,
			String propertyValue) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateByProperties(String conditionName, String conditionValue, String[] propertyName,
			String[] propetyValue) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateByPropertyies(String conditionName, String conditionValue, String propertyName,
			String propertyValue) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(E entity, Serializable oldId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public E merge(E entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E load(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E getByProperties(String[] propName, Object[] propValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E getByProperties(String[] propName, Object[] propValue, Map<String, String> sortedCondition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<E> queryByProperties(String[] propName, Object[] propValue, Map<String, String> sortedConidtion,
			Integer top) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<E> queryByProperties(String[] propName, Object[] propValue, Map<String, String> sortedCondition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<E> queryByProperties(String[] propName, Object[] propValue, Integer top) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<E> queryByProperties(String[] propName, Object[] propValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<E> queryByProperties(String propName, Object propValue, Map<String, String> sortedCondition,
			Integer top) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<E> queryByProperties(String propName, Object propValue, Map<String, String> sortedCondition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<E> queryByProperties(String propName, Object propValue, Integer top) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<E> queryByProperties(String propName, Object propValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void evict(E entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Long countAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<E> doQueryAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<E> doQueryAll(Map<String, String> soredCondition, Integer top) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<E> doQueryAll(Integer top) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long doCount(BaseParameter parameter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<E> doQuery(BaseParameter parameter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QueryResult<E> doPaginationQuery(BaseParameter parameter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QueryResult<E> doPaginationQuery(BaseParameter parameter, boolean bool) {
		// TODO Auto-generated method stub
		return null;
	}

}

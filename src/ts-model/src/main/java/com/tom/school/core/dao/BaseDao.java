package com.tom.school.core.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tom.school.core.support.BaseParameter;
import com.tom.school.core.support.QueryResult;

@Transactional
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

	public Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	@Resource(name = "sessionFactory")
	public void setSF(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	@Override
	public void persist(E entity) {
		getSession().save(entity);
	}

	@Override
	public void delete(E entity) {
		getSession().delete(entity);
	}

	@Override
	public boolean deleteByPK(Serializable... id) {
		boolean result = false;
		if (id != null && id.length > 0) {
			for (int i = 0; i < id.length; i++) {
				E entity = get(id[i]);
				if (entity != null) {
					getSession().delete(entity);
					result = true;
				}
			}
		}
		return result;
	}

	@Override
	public void deleteByProperties(String propName, Object propValue) {
		deleteByProperties(new String[] { propName },
				new Object[] { propValue });
	}

	@Override
	public void deleteByProperties(String[] propName, Object[] propValue) {
		if (propName != null && propName.length > 0 && propValue != null
				&& propValue.length > 0 && propName.length == propValue.length) {
			StringBuffer sb = new StringBuffer("delete from "
					+ entityClass.getName() + " o where 1=1 ");
			appendQL(sb, propName, propValue);
			Query query = getSession().createQuery(sb.toString());
			setParameter(query, propName, propValue);
			query.executeUpdate();
		} else {
			throw new IllegalArgumentException(
					"Method deleteByProperties in BaseDao argument is illegal!");
		}
	}

	@Override
	public void update(E entity) {
		getSession().update(entity);
	}

	@Override
	public void update(E entity, Serializable oldId) {
		deleteByPK(oldId);
		persist(entity);
	}

	@Override
	public void updateByProperties(String[] conditionName,
			Object[] conditionValue, String propertyName, Object propertyValue) {
		updateByProperties(conditionName, conditionValue,
				new String[] { propertyName }, new Object[] { propertyValue });
	}

	@Override
	public void updateByProperties(String conditionName, Object conditionValue,
			String[] propertyName, Object[] propetyValue) {
		updateByProperties(new String[] { conditionName },
				new Object[] { conditionValue }, propertyName, propetyValue);
	}

	@Override
	public void updateByProperties(String conditionName, Object conditionValue,
			String propertyName, Object propertyValue) {
		updateByProperties(new String[] { conditionName },
				new Object[] { conditionValue }, new String[] { propertyName },
				new Object[] { propertyValue });
	}

	@Override
	public void updateByProperties(String[] conditionName,
			Object[] conditonValue, String[] propertyName, Object[] propetyValue) {
		if (propertyName != null && propertyName.length > 0
				&& propetyValue != null && propetyValue.length > 0
				&& propertyName.length == propetyValue.length
				&& conditionName != null && conditionName.length > 0
				&& conditonValue != null && conditonValue.length > 0
				&& conditionName.length == conditonValue.length) {
			StringBuffer sb = new StringBuffer();
			sb.append("update " + this.entityClass.getName() + " o set ");
			for (int i = 0; i < propertyName.length; i++) {
				sb.append(propertyName[i] + " = :p_" + propertyName[i] + ",");
			}
			sb.deleteCharAt(sb.length() - 1); // delete the last char ','
			sb.append(" where 1=1 ");
			appendQL(sb, conditionName, conditonValue);
			Query query = getSession().createQuery(sb.toString());
			for (int i = 0; i < propertyName.length; i++) {
				query.setParameter("p_" + propertyName[i], propetyValue[i]);
			}
			setParameter(query, conditionName, conditonValue);
			query.executeUpdate();
		} else {
			throw new IllegalArgumentException(
					"Method updateByProperties in BaseDao argument is illegal!");
		}
	}

	@Override
	public E merge(E entity) {
		return (E) getSession().merge(entity);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	@Override
	public E get(Serializable id) {
		return (E) getSession().get(this.entityClass, id);
	}

	@Override
	public E load(Serializable id) {
		return (E) getSession().load(this.entityClass, id);
	}

	@Override
	public E getByProperties(String[] propName, Object[] propValue) {
		return getByProperties(propName, propValue, null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public E getByProperties(String[] propName, Object[] propValue,
			Map<String, String> sortedCondition) {
		if (propName != null && propName.length > 0 && propValue != null
				&& propValue.length > 0 && propName.length == propValue.length) {
			StringBuffer sb = new StringBuffer("select o from "
					+ this.entityClass.getName() + " o  where 1=1 ");
			appendQL(sb, propName, propValue);
			if(sortedCondition != null && sortedCondition.size() > 0){
				sb.append(" order by ");
				for(Entry<String, String> e : sortedCondition.entrySet()){
					sb.append(e.getKey() + " " + e.getValue() + ",");
				}
				sb.deleteCharAt(sb.length() - 1);
			}
			Query query = getSession().createQuery(sb.toString());
			setParameter(query, propName, propValue);
			List<E> list = query.list();
			if(list != null && list.size() != 0){
				return list.get(0);
			}
			return null;
		}

		return null;
	}

	@Override
	public List<E> queryByProperties(String[] propName, Object[] propValue,
			Map<String, String> sortedConidtion, Integer top) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<E> queryByProperties(String[] propName, Object[] propValue,
			Map<String, String> sortedCondition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<E> queryByProperties(String[] propName, Object[] propValue,
			Integer top) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<E> queryByProperties(String[] propName, Object[] propValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<E> queryByProperties(String propName, Object propValue,
			Map<String, String> sortedCondition, Integer top) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<E> queryByProperties(String propName, Object propValue,
			Map<String, String> sortedCondition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<E> queryByProperties(String propName, Object propValue,
			Integer top) {
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
		this.getSession().clear();
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
	public QueryResult<E> doPaginationQuery(BaseParameter parameter,
			boolean bool) {
		// TODO Auto-generated method stub
		return null;
	}

	private void appendQL(StringBuffer sb, String[] propName, Object[] propValue) {
		for (int i = 0; i < propName.length; i++) {
			String name = propName[i];
			Object value = propValue[i];
			if (value instanceof Object[]) {
				Object[] arraySerializable = (Object[]) value;
				if (arraySerializable != null && arraySerializable.length > 0) {
					sb.append(" and o." + name + " in (:"
							+ name.replace(".", "") + ")");
				}
			} else if (value instanceof Collection<?>) {
				Collection<?> arraySerializable = (Collection<?>) value;
				if (arraySerializable != null && arraySerializable.size() > 0) {
					sb.append(" and o." + name + " in (:"
							+ name.replace(".", "") + ")");
				}
			} else {
				if (value == null) {
					sb.append(" and o." + name + " is null ");
				} else {
					sb.append(" and o." + name + "=:" + name.replace(".", ""));
				}
			}
		}
	}

	private void setParameter(Query query, String[] propName, Object[] propValue) {
		for (int i = 0; i < propName.length; i++) {
			String name = propName[i];
			Object value = propValue[i];
			if (value != null) {
				if (value instanceof Object[]) {
					query.setParameterList(name.replace(".", ""),
							(Object[]) value);
				} else if (value instanceof Collection<?>) {
					query.setParameterList(name.replace(".", ""),
							(Collection<?>) value);
				} else {
					query.setParameter(name.replace(".", ""), value);
				}
			}
		}
	}

}

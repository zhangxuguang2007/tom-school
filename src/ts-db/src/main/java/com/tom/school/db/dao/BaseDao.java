package com.tom.school.db.dao;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tom.school.core.utility.BeanUtility;
import com.tom.school.db.param.BaseParameter;

@Transactional
public class BaseDao<E> implements Dao<E> {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	private static Map<String, Method> MAP_METHOD = new HashMap<String, Method>();

	private SessionFactory sessionFactory;

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
		deleteByProperties(new String[] { propName }, new Object[] { propValue });
	}

	@Override
	public void deleteByProperties(String[] propName, Object[] propValue) {
		if (propName != null && propName.length > 0
				&& propValue != null && propValue.length > 0
				&& propName.length == propValue.length) {
			StringBuffer sb = new StringBuffer("delete from " + entityClass.getName() + " o where 1=1 ");
			appendQL(sb, propName, propValue);
			Query query = getSession().createQuery(sb.toString());
			setParameter(query, propName, propValue);
			query.executeUpdate();
		} else {
			throw new IllegalArgumentException("Method deleteByProperties in BaseDao argument is illegal!");
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
	public void updateByProperties(String[] conditionName, Object[] conditionValue, String propertyName, Object propertyValue) {
		updateByProperties(conditionName, conditionValue, new String[] { propertyName }, new Object[] { propertyValue });
	}

	@Override
	public void updateByProperties(String conditionName, Object conditionValue, String[] propertyName, Object[] propetyValue) {
		updateByProperties(new String[] { conditionName }, new Object[] { conditionValue }, propertyName, propetyValue);
	}

	@Override
	public void updateByProperties(String conditionName, Object conditionValue, String propertyName, Object propertyValue) {
		updateByProperties(new String[] { conditionName }, new Object[] { conditionValue }, new String[] { propertyName }, new Object[] { propertyValue });
	}

	@Override
	public void updateByProperties(String[] conditionName, Object[] conditonValue, String[] propertyName, Object[] propetyValue) {
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
			throw new IllegalArgumentException("Method updateByProperties " + "" + "in BaseDao argument is illegal!");
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
	public E getByProperties(String[] propName, Object[] propValue, Map<String, String> sortedCondition) {
		if (propName != null && propName.length > 0 && propValue != null && propValue.length > 0 && propName.length == propValue.length) {
			StringBuffer sb = new StringBuffer("select o from " + this.entityClass.getName() + " o  where 1=1 ");
			appendQL(sb, propName, propValue);
			if (sortedCondition != null && sortedCondition.size() > 0) {
				sb.append(" order by ");
				for (Entry<String, String> e : sortedCondition.entrySet()) {
					sb.append(e.getKey() + " " + e.getValue() + ",");
				}
				sb.deleteCharAt(sb.length() - 1);
			}
			Query query = getSession().createQuery(sb.toString());
			setParameter(query, propName, propValue);
			List<E> list = query.list();
			if (list != null && list.size() != 0) {
				return list.get(0);
			}
			return null;
		}

		return null;
	}

	@Override
	public List<E> queryByProperties(String[] propName, Object[] propValue, Map<String, String> sortedCondition) {
		return queryByProperties(propName, propValue, sortedCondition, null);
	}

	@Override
	public List<E> queryByProperties(String[] propName, Object[] propValue, Integer top) {
		return queryByProperties(propName, propValue, null, top);
	}

	@Override
	public List<E> queryByProperties(String[] propName, Object[] propValue) {
		return queryByProperties(propName, propValue, null, null);
	}

	@Override
	public List<E> queryByProperties(String propName, Object propValue, Map<String, String> sortedCondition, Integer top) {
		return queryByProperties(new String[] { propName }, new Object[] { propValue }, sortedCondition, top);
	}

	@Override
	public List<E> queryByProperties(String propName, Object propValue, Map<String, String> sortedCondition) {
		return queryByProperties(new String[] { propName }, new Object[] { propValue }, sortedCondition, null);
	}

	@Override
	public List<E> queryByProperties(String propName, Object propValue, Integer top) {
		return queryByProperties(new String[] { propName }, new Object[] { propValue }, null, top);
	}

	@Override
	public List<E> queryByProperties(String propName, Object propValue) {
		return queryByProperties(new String[] { propName }, new Object[] { propValue }, null, null);
	}

	@Override
	public List<E> queryByProperties(String[] propName, Object[] propValue, Map<String, String> sortedConidtion, Integer top) {

		if (propName != null && propName.length > 0 && propValue != null && propValue.length > 0 && propName.length == propValue.length) {
			StringBuffer sb = new StringBuffer("select o from " + this.entityClass.getName() + " o where 1=1 ");
			appendQL(sb, propName, propValue);
			if (sortedConidtion != null && sortedConidtion.size() > 0) {
				sb.append(" order by ");
				for (Entry<String, String> e : sortedConidtion.entrySet()) {
					sb.append(e.getKey() + " " + e.getValue() + ",");
				}
				sb.deleteCharAt(sb.length() - 1);
			}
			Query query = getSession().createQuery(sb.toString());
			setParameter(query, propName, propValue);
			if (top != null) {
				query.setFirstResult(0);
				query.setMaxResults(top);
			}
			return query.list();
		}
		return null;
	}

	@Override
	public Long countAll() {
		return (Long) getSession().createQuery("select count(*) from " + this.entityClass.getName()).uniqueResult();
	}

	@Override
	public void clear() {
		this.getSession().clear();
	}

	@Override
	public void evict(E entity) {
		getSession().evict(entity);
	}

	@Override
	public List<E> doQueryAll() {
		return doQueryAll(null, null);
	}

	@Override
	public List<E> doQueryAll(Integer top) {
		return doQueryAll(null, top);
	}

	@Override
	public List<E> doQueryAll(Map<String, String> sortedCondition, Integer top) {
		Criteria criteria = getSession().createCriteria(entityClass);
		if (sortedCondition != null && sortedCondition.size() > 0) {
			Iterator<String> it = sortedCondition.keySet().iterator();
			while (it.hasNext()) {
				String pm = it.next();
				if (BaseParameter.SORTED_ASC.equalsIgnoreCase(sortedCondition.get(pm))) {
					criteria.addOrder(Order.desc(pm));
				} else if (BaseParameter.SORTED_DESC.equalsIgnoreCase(sortedCondition.get(pm))) {
					criteria.addOrder(Order.desc(pm));
				}
			}
		}
		if (top != null) {
			criteria.setMaxResults(top);
			criteria.setFirstResult(0);
		}
		return criteria.list();
	}

	@Override
	public Long doCount(BaseParameter param) {
		if (param == null) {
			return null;
		}
		Criteria criteria = getSession().createCriteria(this.entityClass);
		processQuery(criteria, param);
		criteria.setProjection(Projections.rowCount());
		return ((Number) criteria.uniqueResult()).longValue();
	}

	@Override
	public List<E> doQuery(BaseParameter param) {
		if (param == null) {
			return null;
		}
		Criteria criteria = getSession().createCriteria(this.entityClass);
		processQuery(criteria, param);
		if (param.getSortedConditions() != null && param.getSortedConditions().size() > 0) {
			Map<String, String> sortedCondition = param.getSortedConditions();
			if (sortedCondition != null && sortedCondition.size() > 0) {
				Iterator<String> it = sortedCondition.keySet().iterator();
				while (it.hasNext()) {
					String pm = it.next();
					if (BaseParameter.SORTED_ASC.equalsIgnoreCase(sortedCondition.get(pm))) {
						criteria.addOrder(Order.desc(pm));
					} else if (BaseParameter.SORTED_DESC.equalsIgnoreCase(sortedCondition.get(pm))) {
						criteria.addOrder(Order.desc(pm));
					}
				}
			}
		}
		return criteria.list();
	}

	@Override
	public QueryResult<E> doPaginationQuery(BaseParameter param) {
		if (param == null) {
			return null;
		}
		Criteria criteria = getSession().createCriteria(this.entityClass);
		processQuery(criteria, param);
		QueryResult<E> qr = new QueryResult<E>();
		criteria.setProjection(Projections.rowCount());
		qr.setTotalCount(((Number) criteria.uniqueResult()).longValue());
		if (qr.getTotalCount() > 0) {
			if (param.getSortedConditions() != null && param.getSortedConditions().size() > 0) {
				Map<String, String> sortedConditions = param.getSortedConditions();
				for (Entry<String, String> e : sortedConditions.entrySet()) {
					if (BaseParameter.SORTED_DESC.equalsIgnoreCase(e.getValue())) {
						criteria.addOrder(Order.desc(e.getKey()));
					} else {
						criteria.addOrder(Order.asc(e.getKey()));
					}
				}
			}
			criteria.setProjection(null);
			criteria.setFirstResult(param.getFirstResult());
			criteria.setMaxResults(param.getMaxResults());
			qr.setResultList(criteria.list());
		}
		return qr;
	}

	// ---------------------------------------------------------------------
	// For HQL
	// ---------------------------------------------------------------------

	private void appendQL(StringBuffer sb, String[] propName, Object[] propValue) {
		for (int i = 0; i < propName.length; i++) {
			String name = propName[i];
			Object value = propValue[i];
			if (value instanceof Object[]) {
				Object[] arraySerializable = (Object[]) value;
				if (arraySerializable != null && arraySerializable.length > 0) {
					sb.append(" and o." + name + " in (:" + name.replace(".", "") + ")");
				}
			} else if (value instanceof Collection<?>) {
				Collection<?> arraySerializable = (Collection<?>) value;
				if (arraySerializable != null && arraySerializable.size() > 0) {
					sb.append(" and o." + name + " in (:" + name.replace(".", "") + ")");
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
					query.setParameterList(name.replace(".", ""), (Object[]) value);
				} else if (value instanceof Collection<?>) {
					query.setParameterList(name.replace(".", ""), (Collection<?>) value);
				} else {
					query.setParameter(name.replace(".", ""), value);
				}
			}
		}
	}

	// ---------------------------------------------------------------------
	// For QBC
	// ---------------------------------------------------------------------

	private void processQuery(Criteria criteria, BaseParameter param) {
		try {
			Map<String, Object> staticConditionMap = BeanUtility.describleAvaliableParameter(param);
			Map<String, Object> dynamicConditionMap = param.getQueryDynamicConditions();
			Map<String, Object> conditonMap = new HashMap<String, Object>();
			conditonMap.putAll(staticConditionMap);
			conditonMap.putAll(dynamicConditionMap);

			if (conditonMap != null && conditonMap.size() > 0) {
				for (Entry<String, Object> e : conditonMap.entrySet()) {
					String pn = e.getKey();
					String propName = BeanUtility.getParamPropName(pn);
					String methodName = BeanUtility.getParamOpt(pn);
					Method method = getMethod(methodName);
					Object value = e.getValue();
					if (value != null && !(value instanceof String && "".equals((String) value))) {
						if ("like".equalsIgnoreCase(methodName)) {
							criteria.add(Restrictions.like(propName, value.toString(), MatchMode.ANYWHERE));
						} else if ("isNull".equalsIgnoreCase(methodName) && value instanceof Boolean) {
							if ((Boolean) value) {
								criteria.add(Restrictions.isNull(propName));
							} else {
								criteria.add(Restrictions.isNull(propName));
							}
						} else if ("IN".equalsIgnoreCase(methodName) && (value instanceof Object[] || value instanceof Collection)) {
							if (value instanceof Object[]) {
								criteria.add(Restrictions.in(propName, (Object[]) value));
							} else {
								criteria.add(Restrictions.in(propName, (Collection) value));
							}

						} else {
							criteria.add((Criterion) method.invoke(Restrictions.class, new Object[] { propName, value }));
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Method getMethod(String name) {
		if (!MAP_METHOD.containsKey(name)) {
			Class<Restrictions> clazz = Restrictions.class;
			Class[] paramType = new Class[] { String.class, Object.class };
			Class[] likeParamType = new Class[] { String.class, String.class, MatchMode.class };
			Class[] isNullType = new Class[] { String.class };
			try {
				Method method = null;
				if ("like".equalsIgnoreCase(name)) {
					method = clazz.getMethod(name, likeParamType);
				} else if ("isNull".equalsIgnoreCase(name)) {
					method = clazz.getMethod(name, isNullType);
				} else {
					method = clazz.getMethod(name, paramType);
				}
				MAP_METHOD.put(name, method);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return MAP_METHOD.get(name);
	}
}

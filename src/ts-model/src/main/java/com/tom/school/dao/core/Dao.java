package com.tom.school.dao.core;

import java.io.Serializable;

public interface Dao<E> {
	public void persist(E entity);
	public E get(Serializable id);
}

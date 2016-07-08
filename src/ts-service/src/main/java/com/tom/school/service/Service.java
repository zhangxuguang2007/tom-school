package com.tom.school.service;

import java.io.Serializable;

public interface Service<E> {
	public void persist(E entity);
	public void delete(E entity);
	public E get(Serializable id);
}

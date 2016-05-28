package com.tom.school.core.support;

import java.util.List;

public class QueryResult<E> {

	private List<E> resultList;

	public List<E> getResultList() {
		return resultList;
	}

	public void setResultList(List<E> resultList) {
		this.resultList = resultList;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	private Long totalCount;

}

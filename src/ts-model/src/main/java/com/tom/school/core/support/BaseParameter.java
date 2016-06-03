package com.tom.school.core.support;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class BaseParameter implements Serializable {

	private static final long serialVersionUID = 305991101837532498L;

	public static final String SORTED_ASC = "ASC";
	public static final String SORTED_DESC = "DESC";

	private Integer maxResults = 20;
	private Integer firstResult = 0;
	private String cmd;
	private Map<String, Object> queryDynamicConditions = new HashMap<String, Object>();
	private Map<String, String> sortedConditions = new LinkedHashMap<String, String>();

	public Integer getMaxResults() {
		return maxResults;
	}

	public void setMaxResults(Integer maxResult) {
		this.maxResults = maxResult;
	}

	public Integer getFirstResult() {
		return firstResult;
	}

	public void setFirstResult(Integer firstRersult) {
		this.firstResult = firstRersult;
	}

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	public Map<String, Object> getQueryDynamicConditions() {
		return queryDynamicConditions;
	}

	public void setQueryDynamicConditions(
			Map<String, Object> queryDynamicConditions) {
		this.queryDynamicConditions = queryDynamicConditions;
	}

	public Map<String, String> getSortedConditions() {
		return sortedConditions;
	}

	public void setSortedConditions(Map<String, String> sortedConditions) {
		this.sortedConditions = sortedConditions;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static String getSortedAsc() {
		return SORTED_ASC;
	}

	public static String getSortedDesc() {
		return SORTED_DESC;
	}

}

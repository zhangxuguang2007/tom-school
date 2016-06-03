package com.tom.school.core.support;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class BaseParameter implements Serializable {

	private static final long serialVersionUID = 305991101837532498L;

	public static final String SORTED_ASC = "ASC";
	public static final String SORTED_DESC = "DESC";

	private Integer maxResults = 20;
	private Integer firstResult = 0;
	private Integer topCount = 0;
	private String[] sortColumns;
	private String cmd;
	private Map<String, Object> queryDynamicConditions = new HashMap<String, Object>();
	private Map<String, String> sortedConditions = new LinkedHashMap<String, String>();
	private Map<String, Object> dynamicProperties = new HashMap<String, Object>();

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

	public Integer getTopCount() {
		return topCount;
	}

	public void setTopCount(Integer topCount) {
		this.topCount = topCount;
	}

	public String[] getSortColumns() {
		return sortColumns;
	}

	public void setSortColumns(String[] sortColumns) {
		this.sortColumns = sortColumns;
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

	public Map<String, Object> getDynamicProperties() {
		return dynamicProperties;
	}

	public void setDynamicProperties(Map<String, Object> dynamicProperties) {
		this.dynamicProperties = dynamicProperties;
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

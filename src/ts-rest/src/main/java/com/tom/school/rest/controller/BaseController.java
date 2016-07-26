package com.tom.school.rest.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tom.school.core.entity.ContentType;
import com.tom.school.core.utility.HttpResponseUtility;
import com.tom.school.core.utility.JsonUtility;
import com.tom.school.db.dao.QueryResult;
import com.tom.school.db.param.BaseParameter;
import com.tom.school.rest.core.ListView;
import com.tom.school.rest.service.Service;

public abstract class BaseController<E> {

	protected Service<E> service;

	public BaseController() {
	}

	@RequestMapping(value = { "/list" }, method = { RequestMethod.GET, RequestMethod.POST })
	public void doList(@ModelAttribute BaseParameter parameter, HttpServletRequest request, HttpServletResponse response) {
		QueryResult<E> queryResult = this.service.doPaginationQuery(parameter);
		ListView<E> listView = new ListView<E>();
		listView.setData(queryResult.getResultList());
		listView.setTotalRecord(queryResult.getTotalCount());
		HttpResponseUtility.write(response, JsonUtility.encode(listView), ContentType.JSON);
	}

	@RequestMapping(value = "/save")
	public void doSave() {
	}

	@RequestMapping(value = "/content/{id}")
	public void doContent() {
	}

	@RequestMapping(value = "/delete")
	public void doDelete() {
	}

	protected void beforeList(E entity) {
	}

	protected void beforeSaveNew(E entity) {
	}

	protected void beforeSaveUpdate(E entity) {
	}

}

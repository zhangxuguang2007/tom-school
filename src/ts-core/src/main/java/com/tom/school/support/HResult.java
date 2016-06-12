package com.tom.school.support;

/*
 * HTTP result
 */
public class HResult {

	private int status;
	private Object result;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

}

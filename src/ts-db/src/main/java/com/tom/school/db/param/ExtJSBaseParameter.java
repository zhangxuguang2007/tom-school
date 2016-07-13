package com.tom.school.db.param;

public class ExtJSBaseParameter extends BaseParameter {

	private static final long serialVersionUID = 85166356810410853L;
	private Boolean success;
	private String message;

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}

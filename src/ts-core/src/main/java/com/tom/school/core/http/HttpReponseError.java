package com.tom.school.core.http;

public class HttpReponseError{

	private int status;
	private String error;
	private int error_code;

	/** interanal error */
	public static final HttpReponseError INTERNAL_ERROR;

	/** authorization error */
	public static final HttpReponseError AUTHORIZATION_ERROR;

	/** missing arguments error */
	public static final HttpReponseError MISSING_ARGUMENTS;

	/** invalid arguments error */
	public static final HttpReponseError INVALID_ARGUMENTS;

	static {

		INTERNAL_ERROR = new HttpReponseError();
		INTERNAL_ERROR.setError_code(1001);
		INTERNAL_ERROR.setStatus(500);
		INTERNAL_ERROR.setError("INTERNAL_ERROR");

		AUTHORIZATION_ERROR = new HttpReponseError();
		AUTHORIZATION_ERROR.setError_code(1003);
		AUTHORIZATION_ERROR.setStatus(403);
		AUTHORIZATION_ERROR.setError("AUTHORIZATION_ERROR");

		MISSING_ARGUMENTS = new HttpReponseError();
		MISSING_ARGUMENTS.setError_code(1004);
		MISSING_ARGUMENTS.setStatus(400);
		MISSING_ARGUMENTS.setError("MISSING_ARGUMENTS");

		INVALID_ARGUMENTS = new HttpReponseError();
		INVALID_ARGUMENTS.setError_code(1004);
		INVALID_ARGUMENTS.setStatus(400);
		INVALID_ARGUMENTS.setError("INVALID_ARGUMENTS");

	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public int getError_code() {
		return error_code;
	}

	public void setError_code(int error_code) {
		this.error_code = error_code;
	}

}

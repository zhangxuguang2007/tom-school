package com.tom.school.support;

/*
 * Inner HTTP error
 */
public class HInnerError extends HError {
	
	private int httpStatus;
	
	/** interanal error */
	public static final HInnerError INTERNAL_ERROR;
	
	/** authorization error */
	public static final HInnerError AUTHORIZATION_ERROR;
	
	/** missing arguments error */
	public static final HInnerError MISSING_ARGUMENTS;
	
	/** invalid arguments error */
	public static final HInnerError INVALID_ARGUMENTS;
	
	static{
		
		INTERNAL_ERROR = new HInnerError();
		INTERNAL_ERROR.setError_code(1001);
		INTERNAL_ERROR.setHttpStatus(500);
		INTERNAL_ERROR.setError("INTERNAL_ERROR");
		
		AUTHORIZATION_ERROR = new HInnerError();
		AUTHORIZATION_ERROR.setError_code(1003);
		AUTHORIZATION_ERROR.setHttpStatus(403);
		AUTHORIZATION_ERROR.setError("AUTHORIZATION_ERROR");
		
		MISSING_ARGUMENTS = new HInnerError();
		MISSING_ARGUMENTS.setError_code(1004);
		MISSING_ARGUMENTS.setHttpStatus(400);
		MISSING_ARGUMENTS.setError("MISSING_ARGUMENTS");
		
		INVALID_ARGUMENTS = new HInnerError();
		INVALID_ARGUMENTS.setError_code(1004);
		INVALID_ARGUMENTS.setHttpStatus(400);
		INVALID_ARGUMENTS.setError("INVALID_ARGUMENTS");
		
	}

	public int getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(int httpStatus) {
		this.httpStatus = httpStatus;
	}
	
}

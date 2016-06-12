package com.tom.school.support;

public class InnerErrorResult extends ErrorResult {
	
	private int httpStatus;
	
	/** interanal error */
	public static final InnerErrorResult INTERNAL_ERROR;
	
	/** authorization error */
	public static final InnerErrorResult AUTHORIZATION_ERROR;
	
	/** missing arguments error */
	public static final InnerErrorResult MISSING_ARGUMENTS;
	
	/** invalid arguments error */
	public static final InnerErrorResult INVALID_ARGUMENTS;
	
	static{
		
		INTERNAL_ERROR = new InnerErrorResult();
		INTERNAL_ERROR.setError_code(1001);
		INTERNAL_ERROR.setHttpStatus(500);
		INTERNAL_ERROR.setError("INTERNAL_ERROR");
		
		AUTHORIZATION_ERROR = new InnerErrorResult();
		AUTHORIZATION_ERROR.setError_code(1003);
		AUTHORIZATION_ERROR.setHttpStatus(403);
		AUTHORIZATION_ERROR.setError("AUTHORIZATION_ERROR");
		
		MISSING_ARGUMENTS = new InnerErrorResult();
		MISSING_ARGUMENTS.setError_code(1004);
		MISSING_ARGUMENTS.setHttpStatus(400);
		MISSING_ARGUMENTS.setError("MISSING_ARGUMENTS");
		
		INVALID_ARGUMENTS = new InnerErrorResult();
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

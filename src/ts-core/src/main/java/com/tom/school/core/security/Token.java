package com.tom.school.core.security;

import java.util.Date;

public class Token {

	private String value;
	private String userName;
	private Date visitdTime;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getVisitdTime() {
		return visitdTime;
	}

	public void setVisitdTime(Date visitdTime) {
		this.visitdTime = visitdTime;
	}

}

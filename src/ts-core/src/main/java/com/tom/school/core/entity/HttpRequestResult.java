package com.tom.school.core.entity;


public class HttpRequestResult {

	private int status;
	private byte[] data;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}


}

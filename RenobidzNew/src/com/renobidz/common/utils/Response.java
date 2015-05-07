package com.renobidz.common.utils;

/**
 * @author Ankur
 * 
 * Generic Response Object for message
 */
public class Response {
	private STATUS status;
	private String message;

	/**
	 * @param status
	 * @param message
	 */
	public Response(STATUS status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public STATUS getStatus() {
		return status;
	}

	public void setStatus(STATUS status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}

package com.pitang.desafio.exception;

import java.util.Date;

public class ErrorMessage {

	private Date timesTamp;
	private String message;
	private int errorCode;

	
	
	public ErrorMessage(Date timesTamp, String message, int errorCode) {
		super();
		this.timesTamp = timesTamp;
		this.message = message;
		this.errorCode = errorCode;
	}

	

	public ErrorMessage() {
		
	}



	public Date getTimesTamp() {
		return timesTamp;
	}

	
	public void setTimesTamp(Date timesTamp) {
		this.timesTamp = timesTamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

}

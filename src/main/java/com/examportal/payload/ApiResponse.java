package com.examportal.payload;

import org.springframework.http.HttpStatus;

import lombok.Builder;

@Builder
public class ApiResponse {
	
	private String message;
	private boolean status;
	private HttpStatus httpStatus;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
	public ApiResponse(String message, boolean status, HttpStatus httpStatus) {
		super();
		this.message = message;
		this.status = status;
		this.httpStatus = httpStatus;
	}
	public ApiResponse() {
		super();
	}
	@Override
	public String toString() {
		return "ApiResponse [message=" + message + ", status=" + status + ", httpStatus=" + httpStatus + "]";
	}
	
	

}

package com.zg.encurtador.web.response.exceptions;

import org.springframework.http.HttpStatus;

public class ResponseException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	private HttpStatus status;

	public ResponseException(HttpStatus status, String message) {
		super(message);
		this.status = status;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	
}

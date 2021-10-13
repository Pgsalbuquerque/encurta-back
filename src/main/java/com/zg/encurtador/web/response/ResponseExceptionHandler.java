package com.zg.encurtador.web.response;


import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.zg.encurtador.web.response.exceptions.ResponseException;

@ControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(ResponseException.class)
	public ResponseEntity<Object> handleResponse(ResponseException responseException, WebRequest req) {
		ResponseMessage res = new ResponseMessage(responseException.getMessage());
		
		return handleExceptionInternal(responseException, res, new HttpHeaders(), responseException.getStatus(), req);
	}
	
}

package com.pitang.desafio.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AppExceptionHandler  extends ResponseEntityExceptionHandler{

	
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<Object> handException(Exception ex, WebRequest request){
		
		
		String erroMessageDescriptor = ex.getLocalizedMessage();
		if(erroMessageDescriptor==null) {
			erroMessageDescriptor = ex.toString();
		}
		ErrorMessage errorMessage = new ErrorMessage(new Date(),erroMessageDescriptor,HttpStatus.INTERNAL_SERVER_ERROR.value());

		return  new ResponseEntity<>(errorMessage, new HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}

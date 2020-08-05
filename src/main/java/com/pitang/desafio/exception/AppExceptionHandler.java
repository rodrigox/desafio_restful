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

	
	@ExceptionHandler(value = BadRequestException.class)
	public ResponseEntity<Object> handInternalErrorException(BadRequestException ex, WebRequest request){
		
		
		String erroMessageDescriptor = ex.getLocalizedMessage();
		if(erroMessageDescriptor==null) {
			erroMessageDescriptor = ex.toString();
		}
		ErrorMessage errorMessage = new ErrorMessage(new Date(),erroMessageDescriptor,HttpStatus.BAD_REQUEST.value());

		return  new ResponseEntity<>(errorMessage, new HttpHeaders(),HttpStatus.BAD_REQUEST);
	}
	
	
	
	@ExceptionHandler(value = NotFoundException.class)
	public ResponseEntity<Object> handNotFoundException(NotFoundException ex, WebRequest request){
		
		
		String erroMessageDescriptor = ex.getLocalizedMessage();
		if(erroMessageDescriptor==null) {
			erroMessageDescriptor = ex.toString();
		}
		ErrorMessage errorMessage = new ErrorMessage(new Date(),erroMessageDescriptor,HttpStatus.NOT_FOUND.value());

		return  new ResponseEntity<>(errorMessage, new HttpHeaders(),HttpStatus.NOT_FOUND);
	}
	

	@ExceptionHandler(value = LoginException.class)
	public ResponseEntity<Object> handIncorrectLoginException(LoginException ex, WebRequest request){
		
		
		String erroMessageDescriptor = ex.getLocalizedMessage();
		if(erroMessageDescriptor==null) {
			erroMessageDescriptor = ex.toString();
		}
		ErrorMessage errorMessage = new ErrorMessage(new Date(),erroMessageDescriptor,HttpStatus.NOT_FOUND.value());

		return  new ResponseEntity<>(errorMessage, new HttpHeaders(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = UnauthorizedException.class)
	public ResponseEntity<Object> handUnauthorizedException(UnauthorizedException ex, WebRequest request){
		
		
		String erroMessageDescriptor = ex.getLocalizedMessage();
		if(erroMessageDescriptor==null) {
			erroMessageDescriptor = ex.toString();
		}
		ErrorMessage errorMessage = new ErrorMessage(new Date(),erroMessageDescriptor,HttpStatus.UNAUTHORIZED.value());

		return  new ResponseEntity<>(errorMessage, new HttpHeaders(),HttpStatus.UNAUTHORIZED);
	}
	
	
	
	
}

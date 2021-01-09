package com.compassouol.recrutamento.cadastroservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.compassouol.recrutamento.cadastroservice.rest.model.Error_R;

@ControllerAdvice
public class CustomResponseExceptionHandler extends ResponseEntityExceptionHandler {
	
	//TODO: logar o trace completo
	
	@ExceptionHandler(BadRequestException.class)
	public final ResponseEntity<Error_R> handleBadRequestException(BadRequestException ex, WebRequest request) {		
		Error_R error_R = new Error_R(HttpStatus.BAD_REQUEST.value(), ex.getLocalizedMessage());		
		return new ResponseEntity<>(error_R, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NotFoundException.class)
	public final ResponseEntity<Error_R> handleNotFoundException(NotFoundException ex, WebRequest request) {		
		Error_R error_R = new Error_R(HttpStatus.NOT_FOUND.value(), ex.getLocalizedMessage());		
		return new ResponseEntity<>(error_R, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(InternalServerErrorException.class)
	public final ResponseEntity<Error_R> handleInternalServerErrorException(InternalServerErrorException ex, WebRequest request) {		
		Error_R error_R = new Error_R(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getLocalizedMessage());		
		return new ResponseEntity<>(error_R, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Error_R> handleExceptionCommon(Exception ex, WebRequest request) {		
		Error_R error_R = new Error_R(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getLocalizedMessage());		
		return new ResponseEntity<>(error_R, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
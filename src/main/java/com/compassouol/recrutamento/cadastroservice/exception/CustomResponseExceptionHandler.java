package com.compassouol.recrutamento.cadastroservice.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.compassouol.recrutamento.cadastroservice.rest.model.Error_R;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

@ControllerAdvice
public class CustomResponseExceptionHandler extends ResponseEntityExceptionHandler {
		
	private Logger logger = LoggerFactory.getLogger(CustomResponseExceptionHandler.class);
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public final ResponseEntity<Error_R> handleErroArgumentoInvalido(MethodArgumentTypeMismatchException ex, WebRequest request) {
		logger.error(ex.getLocalizedMessage(), ex);
		String msg = null;
		if(ex.getCause() instanceof ConversionException) {
			msg = "O valor de algum parâmetro não esta correto.";
		}else {
			msg = ex.getLocalizedMessage();
		}
		Error_R error_R = new Error_R(HttpStatus.BAD_REQUEST.value(), msg);		
		return new ResponseEntity<>(error_R, HttpStatus.BAD_REQUEST);
	}	
	
	@ExceptionHandler(BadRequestException.class)
	public final ResponseEntity<Error_R> handleBadRequestException(BadRequestException ex, WebRequest request) {
		logger.error(ex.getLocalizedMessage(), ex);
		Error_R error_R = new Error_R(HttpStatus.BAD_REQUEST.value(), ex.getLocalizedMessage());		
		return new ResponseEntity<>(error_R, HttpStatus.BAD_REQUEST);
	}		
	
	@ExceptionHandler(NotFoundException.class)
	public final ResponseEntity<Error_R> handleNotFoundException(NotFoundException ex, WebRequest request) {
		logger.error(ex.getLocalizedMessage(), ex);
		Error_R error_R = new Error_R(HttpStatus.NOT_FOUND.value(), ex.getLocalizedMessage());		
		return new ResponseEntity<>(error_R, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(InternalServerErrorException.class)
	public final ResponseEntity<Error_R> handleInternalServerErrorException(InternalServerErrorException ex, WebRequest request) {		
		logger.error(ex.getLocalizedMessage(), ex);
		Error_R error_R = new Error_R(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getLocalizedMessage());		
		return new ResponseEntity<>(error_R, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		//return super.handleHttpMessageNotReadable(ex, headers, status, request);		
		logger.error(ex.getLocalizedMessage(), ex);
		String msg = null;
		if(ex.getCause() instanceof InvalidFormatException) {
			msg = "O valor de algum atributo não esta correto.";
		}else {
			msg = ex.getLocalizedMessage();
		}
		Error_R error_R = new Error_R(HttpStatus.BAD_REQUEST.value(), msg);		
		return new ResponseEntity<>(error_R, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Error_R> handleErrosServidorNaoTratados(Exception ex, WebRequest request) {		
		logger.error(ex.getLocalizedMessage(), ex);
		Error_R error_R = new Error_R(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getLocalizedMessage());		
		return new ResponseEntity<>(error_R, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
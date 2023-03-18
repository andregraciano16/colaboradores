package com.empresa.colaboradores.config.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class CustomExceptionHandler {

	@Autowired
    private MessageSource messageSource;
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<Object> handleUCartaoNaoEncontradoException(RuntimeException exception,
			WebRequest request) {
		return new ResponseEntity<>(messageSource.getMessage("internal.server.error", null, null), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}

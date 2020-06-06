package com.simpleJwt.server.SimpleJwtServer.exception;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	Log log = LogFactory.getLog(GlobalExceptionHandler.class);
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Details> getException(Details details){
		
		log.debug(details.getMessage());
		
		return new ResponseEntity<Details>(details, HttpStatus.BAD_REQUEST);
	}
}

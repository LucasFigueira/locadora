package com.alltecnologia.locadora.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class LocadoraBadRequestException extends RuntimeException {
	 private static final long serialVersionUID = 1L;

	  public LocadoraBadRequestException(String error) {
	    super(error);
	  }
}

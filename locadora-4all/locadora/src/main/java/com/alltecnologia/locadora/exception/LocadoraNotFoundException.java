package com.alltecnologia.locadora.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class LocadoraNotFoundException extends RuntimeException {

	  private static final long serialVersionUID = 1L;

	  public LocadoraNotFoundException(String error) {
	    super(error);
	  }
}

package com.alltecnologia.locadora.exception;

public class LocadoraException{
	  private static final long serialVersionUID = 1L;

	  private String error;
	  
	  public LocadoraException(String error) { 
	    this.error = error;
	  }
	  
	  public String getError() {
	    return error;
	  }

	  public void setError(String error) {
	    this.error = error;
	  }
}

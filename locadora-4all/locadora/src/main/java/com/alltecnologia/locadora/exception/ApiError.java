package com.alltecnologia.locadora.exception;
 

import java.util.Date;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiError {
  @JsonFormat(shape = JsonFormat.Shape.STRING , pattern = "dd-MM-yyyy HH:mm:ss")
  private Date          timestamp;
  private Integer       status;
  private String        error;
  private Integer       internalErrorCode;
  private String        exception;
  private String        message;
  private String        path;

  private ApiError() {
     timestamp = new Date();
  }

  public ApiError(HttpStatus status) {
    this();
    this.status = status.value();
    error = status.getReasonPhrase();
  }

  public ApiError(HttpStatus status, Throwable exception) {
    this(status);
    this.exception = exception.getClass().getCanonicalName();
    this.message = exception.getLocalizedMessage();
  }

  public ApiError(HttpStatus status, Throwable exception, String message) {
    this(status);
    this.exception = exception.getClass().getCanonicalName();
    this.message = message;
  }

  // GETTERS
  public Date getTimestamp() {
    return timestamp;
  }

  public Integer getStatus() {
    return status;
  }

  public String getError() {
    return error;
  }
  
  public Integer getInternalErrorCode() {
    return internalErrorCode;
  }
  
  public String getException() {
    return exception;
  }

  public String getMessage() {
    return message;
  }

  public String getPath() {
    return path;
  }

  // SETTERS
  public void setTimestamp(Date timestamp) {
    this.timestamp = timestamp;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public void setError(String error) {
    this.error = error;
  }

  public void setInternalErrorCode(Integer internalErrorCode) {
    this.internalErrorCode = internalErrorCode;
  }
  
  public void setException(String exception) {
    this.exception = exception;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public void setPath(String path) {
    this.path = path;
  } 
}
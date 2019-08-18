package com.alltecnologia.locadora.exception;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.alltecnologia.locadora.config.MsgConfiguration;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;  

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class RestTemplateValidExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	MsgConfiguration messages;

	@Autowired
	MessageSource messageSource;

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<String> erros = new ArrayList<>();

		for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
			String fails = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
			erros.add(fails);
		}

		return buildResponseEntity(new ApiError(status, ex, erros.toString()));
	}

	@ExceptionHandler(LocadoraNotFoundException.class)
	protected ResponseEntity<Object> handleExceptionNotFound(LocadoraNotFoundException ex, WebRequest request) {
		return buildResponseEntity(new ApiError(HttpStatus.NOT_FOUND, ex, ex.getMessage()));
	}
	  
	@ExceptionHandler(Exception.class)
	protected ResponseEntity<Object> handleExceptionGeneric(Exception ex, WebRequest request) {
		String msg = messages.get("entity.validation.internal-error");
		return buildResponseEntity(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex, msg));
	}

	@ExceptionHandler(RestTempValidationException.class)
	protected ResponseEntity<Object> handleExceptionRes(RestTempValidationException ex, WebRequest request) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		ApiError api = mapper.readValue(ex.getLocalizedMessage(), ApiError.class);
		return buildResponseEntity(api);
	}

	private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
		HttpStatus status = HttpStatus.valueOf(apiError.getStatus());

		return new ResponseEntity<>(apiError, status);
	}  

}

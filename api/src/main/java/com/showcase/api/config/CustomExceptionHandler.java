package com.showcase.api.config;

import com.showcase.api.controller.data.response.CommonResponse;
import com.showcase.api.support.exception.BusinessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
																  HttpHeaders headers, HttpStatusCode status,
																  WebRequest request) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach(error -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});

		return new ResponseEntity<>(new CommonResponse<>(errors, null, LocalDateTime.now()),
									HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = {BusinessException.class})
	protected ResponseEntity<Object> handleBusinessException(RuntimeException ex, WebRequest request) {
		return handleExceptionInternal(ex, new CommonResponse<>(null, ex.getMessage(), LocalDateTime.now()),
									   new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleGlobalExceptions(Exception ex, WebRequest request) {
		return handleExceptionInternal(ex, new CommonResponse<>(null, ex.getMessage(), LocalDateTime.now()),
									   new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
	}

}

package com.gov.restapi.GovRestApi.exception;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.ConstraintViolationException;

// @ControllerAdvice 를 설정하면 Swagger에서 충돌이 발생하여 API 목록을 표시하지 못함. 아잭 미해결 상태임...
@ControllerAdvice(basePackages = "com.gov.restapi.GovRestApi.restController")
//@ControllerAdvice
//@RestControllerAdvice(annotations = {RestController.class}, basePackageClasses = {DiaryController.class, MemberController.class, TransmissionController.class})/@RestController
@RestController
public class GlobalExceptionHandler {
	
	// 기본적인 예외 처리
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleGeneralException(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// 특정 예외 처리: NoSuchElementException
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<?> handleNoSuchElementException(NoSuchElementException e) {
		return new ResponseEntity<>("Book not found: " + e.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	// 특정 예외 처리: MethodArgumentNotValidException (유효성 검사 실패)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error ->
			errors.put(error.getField(), error.getDefaultMessage())
		);
		return errors;
	}
	
	// 특정 예외 처리: ConstraintViolationException (유효성 검사 실패)
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {
		return new ResponseEntity<>("Validation error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
}

package com.github.jmsmarcelo.alura.forum.api.base.exceptions;

import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ErrorHandler {
	@ExceptionHandler(ValidatorException.class)
	public ResponseEntity<?> handleErrorValidator(ValidatorException ex) {
		return ResponseEntity.badRequest().body(get(ex));
	}
	@ExceptionHandler({EntityNotFoundException.class, NullPointerException.class,
		InvalidDataAccessApiUsageException.class})
	public ResponseEntity<?> handleError404() {
		return ResponseEntity.notFound().build();
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleError400(MethodArgumentNotValidException ex) {
		var errors = ex.getFieldErrors();
		return ResponseEntity.badRequest().body(
				new PageImpl<>(errors.stream().map(ErrorDataValidation::new).toList()));
	}
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<?> handleError400(HttpMessageNotReadableException ex) {
		return ResponseEntity.badRequest().body(get(ex));
	}
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<?> handleError400(SQLIntegrityConstraintViolationException ex) {
		return ResponseEntity.badRequest().body(get(ex));
	}
	
	private Object[] get(Exception err) {
				return new Object[] {new ErrorDataException(err)};
	}
	private record ErrorDataValidation(String field, String message) {
		public ErrorDataValidation(FieldError err) {
			this(err.getField(), err.getDefaultMessage());
		}
	}
	private record ErrorDataException(String error, String message) {
		public ErrorDataException(Exception ex) {
			this(ex.getCause().toString(), ex.getMessage());
		}
	}
}

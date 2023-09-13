package com.github.jmsmarcelo.alura.forum.api.base.exceptions;

public class ValidatorException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private Errors error;
	public ValidatorException(Errors error, String msg) {
		super(msg, new ValidatorException(error));
		this.error = error;
	}
	public ValidatorException(Errors error) {
		this.error = error;
	}
	@Override
	public String toString() {
		return error.toString();
		
	}
}
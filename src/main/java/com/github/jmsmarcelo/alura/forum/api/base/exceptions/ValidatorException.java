package com.github.jmsmarcelo.alura.forum.api.base.exceptions;

public class ValidatorException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ValidatorException(String msg) {
		super(msg);
	}
}

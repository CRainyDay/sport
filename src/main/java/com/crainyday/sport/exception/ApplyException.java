package com.crainyday.sport.exception;

public class ApplyException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public ApplyException() {
		super();
	}
	public ApplyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	public ApplyException(String message, Throwable cause) {
		super(message, cause);
	}
	public ApplyException(String message) {
		super(message);
	}
	public ApplyException(Throwable cause) {
		super(cause);
	}
}

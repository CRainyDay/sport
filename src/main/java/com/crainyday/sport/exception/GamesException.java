package com.crainyday.sport.exception;

public class GamesException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public GamesException() {
		super();
	}
	public GamesException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	public GamesException(String message, Throwable cause) {
		super(message, cause);
	}
	public GamesException(String message) {
		super(message);
	}
	public GamesException(Throwable cause) {
		super(cause);
	}
}

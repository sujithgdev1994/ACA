package com.infant.exception;

/**
 * Created by sujith on 15-01-2023
 */
public class BadRequestException extends RuntimeException {

	private static final long serialVersionUID = -5341758043670151400L;

	public BadRequestException(String message) {
		super(message);
	}

}

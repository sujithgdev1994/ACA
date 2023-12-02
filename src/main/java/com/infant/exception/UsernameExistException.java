package com.infant.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by sujith on 24-12-2022
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UsernameExistException extends RuntimeException {

	private static final long serialVersionUID = 7037960606814484329L;

	public UsernameExistException(String message) {
		super(message);
	}
}

package com.er.exceptions;

public class InvalidEmailException extends RuntimeException {

	public InvalidEmailException(String msg) {
		super(msg);
	}
}

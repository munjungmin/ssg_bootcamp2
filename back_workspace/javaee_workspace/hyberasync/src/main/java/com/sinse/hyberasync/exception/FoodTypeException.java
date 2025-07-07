package com.sinse.hyberasync.exception;

public class FoodTypeException extends RuntimeException{

	public FoodTypeException(String message, Throwable cause) {
		super(message, cause);
	}

	public FoodTypeException(String message) {
		super(message);
	}

	public FoodTypeException(Throwable cause) {
		super(cause);
	}
}

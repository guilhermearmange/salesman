package com.salesman.exception;

public class SalesmanException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public SalesmanException(String message) {
		super(message);
	}
	
	public SalesmanException(String message, Throwable cause) {
		super(message, cause);
	}

}

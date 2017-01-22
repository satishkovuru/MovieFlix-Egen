package io.egen.api.exception;

public class LoginException extends BaseException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LoginException(String message) {
		super(message);
	}
	
	public LoginException(String message, Throwable cause) {
		super(message,cause);
	}

}

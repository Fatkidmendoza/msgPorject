package edu.msg.flightmanager.backend.exception;

public class ExpiredTokenException extends RuntimeException {

	private static final long serialVersionUID = 263619559075174990L;

	public ExpiredTokenException() {
		super();
	}

	public ExpiredTokenException(String message, Throwable cause) {
		super(message, cause);

	}

	public ExpiredTokenException(String message) {
		super(message);

	}

	public ExpiredTokenException(Throwable cause) {
		super(cause);

	}


}

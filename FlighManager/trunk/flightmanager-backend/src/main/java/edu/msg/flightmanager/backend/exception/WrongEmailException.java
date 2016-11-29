package edu.msg.flightmanager.backend.exception;

public class WrongEmailException extends RuntimeException {

	private static final long serialVersionUID = 263619559075174990L;

	public WrongEmailException() {
		super();
	}

	public WrongEmailException(String message, Throwable cause) {
		super(message, cause);

	}

	public WrongEmailException(String message) {
		super(message);

	}

	public WrongEmailException(Throwable cause) {
		super(cause);

	}


}

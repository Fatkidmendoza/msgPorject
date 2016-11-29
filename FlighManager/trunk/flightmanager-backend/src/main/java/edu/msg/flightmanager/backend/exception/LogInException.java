package edu.msg.flightmanager.backend.exception;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class LogInException extends RuntimeException {

	private static final long serialVersionUID = 2967972610801713589L;

	public LogInException() {
		super();
	}

	public LogInException(String message, Throwable cause) {
		super(message, cause);
	}

	public LogInException(String message) {
		super(message);
	}

	public LogInException(Throwable cause) {
		super(cause);
	}

}

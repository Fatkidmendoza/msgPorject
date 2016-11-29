package edu.msg.flightmanager.backend.exception;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class DifferentVersionException extends RuntimeException {

	private static final long serialVersionUID = 7508580358405935450L;

	public DifferentVersionException() {
		super();
	}

	public DifferentVersionException(String message, Throwable cause) {
		super(message, cause);
	}

	public DifferentVersionException(String message) {
		super(message);
	}

	public DifferentVersionException(Throwable cause) {
		super(cause);
	}

}

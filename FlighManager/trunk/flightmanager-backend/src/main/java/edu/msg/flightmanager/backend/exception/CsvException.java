package edu.msg.flightmanager.backend.exception;

public class CsvException extends RuntimeException {

	private static final long serialVersionUID = 7508580358405935450L;

	public CsvException() {
		super();
	}

	public CsvException(String message, Throwable cause) {
		super(message, cause);
	}

	public CsvException(String message) {
		super(message);
	}

	public CsvException(Throwable cause) {
		super(cause);
	}

}

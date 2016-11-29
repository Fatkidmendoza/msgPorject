package edu.msg.flightmanager.web.filters;

import javax.ejb.ApplicationException;
import javax.faces.application.FacesMessage;
import javax.faces.validator.ValidatorException;

@ApplicationException(rollback = true)
public class PasswordException {

	public PasswordException(String errorMessage) {
		FacesMessage message = new FacesMessage(errorMessage);
		throw new ValidatorException(message);
	}
}

package edu.msg.flightmanager.web.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.apache.commons.lang.math.NumberUtils;

@FacesValidator("phoneValidator")
public class PhoneNumberValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		String phone = value.toString();
		boolean isNumber = NumberUtils.isNumber(phone);

		if (phone.length() < 6) {
			FacesMessage message = new FacesMessage("Phone number too short!");
			throw new ValidatorException(message);
		} else if (!isNumber) {
			FacesMessage message = new FacesMessage("Invalid phone number");
			throw new ValidatorException(message);
		}
	}

}

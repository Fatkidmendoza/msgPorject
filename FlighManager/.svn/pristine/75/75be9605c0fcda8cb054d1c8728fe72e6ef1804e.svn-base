package edu.msg.flightmanager.web.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("passwordValidator")
public class PasswordValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		String password = value.toString();
		UIInput passIn = (UIInput) component.getAttributes().get("passChck");
		String chckPass = passIn.getSubmittedValue().toString();
		if (password.isEmpty() || password == null) {
			FacesMessage message = new FacesMessage("Enter the password");
			throw new ValidatorException(message);
		} else if (!password.equals(chckPass)) {
			FacesMessage message = new FacesMessage("Passwords must match");
			throw new ValidatorException(message);
		}
	}
}

package edu.msg.flightmanager.web.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import edu.msg.flightmanager.backend.service.UserService;

@FacesValidator("emailValidator")
public class EmailValidator implements Validator {

	@EJB
	private UserService userService;

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

		String email = value.toString();

		if (email == null || email.isEmpty()) {
			FacesMessage message = new FacesMessage("Email field is empty");
			throw new ValidatorException(message);
		}
		if (!matchesPattern(email)) {
			FacesMessage message = new FacesMessage("Invalid email format, hint : name@domain.com");
			throw new ValidatorException(message);
		}
	}

	private boolean matchesPattern(String password) {
		Pattern pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);
		Matcher matcherInput = pattern.matcher(password);
		return matcherInput.find();
	}
}

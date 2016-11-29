package edu.msg.flightmanager.web.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import edu.msg.flightmanager.web.util.Constants;

@FacesValidator("userNameValidator")
public class UserNameValidator implements Validator {

	@Override
	public void validate(FacesContext facesContext, UIComponent uiComponent, Object value) throws ValidatorException {
		String userName = value.toString();
		String password = value.toString();
		Pattern pattern = Pattern.compile(Constants.NAME_PATTERN, Pattern.CASE_INSENSITIVE);
		Matcher matcherInput = pattern.matcher(userName);
		boolean matchesNormalCharacter = matcherInput.find();

		if (userName.length() < 4) {
			FacesMessage message = new FacesMessage("Username is  too short!");
			throw new ValidatorException(message);
		} else if (matchesNormalCharacter) {
			FacesMessage message = new FacesMessage("Username contains special characters");
			throw new ValidatorException(message);
		}
	}
}
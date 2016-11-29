package edu.msg.flightmanager.web.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import edu.msg.flightmanager.backend.service.UserService;
import edu.msg.flightmanager.web.util.Constants;

@ManagedBean
@RequestScoped
public class UserValidator implements Validator {

	@EJB
	private UserService userService;

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		UIInput passInput = (UIInput) component.getAttributes().get("pass");
		String password = passInput.getSubmittedValue().toString();
		String userName = value.toString();
		Pattern pattern = Pattern.compile(Constants.NAME_PATTERN, Pattern.CASE_INSENSITIVE);
		Matcher matcherInput = pattern.matcher(userName);
		boolean matchesNormalCharacter = matcherInput.find();

		if (userName.isEmpty() || userName == null || password == null || password.isEmpty()) {
			FacesMessage message = new FacesMessage("Enter your username and password");
			throw new ValidatorException(message);
		} else if (matchesNormalCharacter) {
			FacesMessage message = new FacesMessage("Username or password contains special characters");
			throw new ValidatorException(message);
		}

	}

}

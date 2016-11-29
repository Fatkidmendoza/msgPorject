package edu.msg.flightmanager.backend.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.msg.flightmanager.backend.model.User;

public class UserValidator {

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	private static final String USER_NAME_IS_NOT_VALID = "User name is not valid.";
	private static final String PASSWORD_IS_NOT_VALID = "Password is not valid.";
	private static final String FIRST_NAME_IS_NOT_VALID = "User first name is not valid.";
	private static final String LAST_NAME_IS_NOT_VALID = "User last name is not valid.";
	private static final String EMAIL_IS_NOT_VALID = "Email is not valid.";
	private static final String PHONE_NUMBER_IS_NOT_VALID = "Phone number is not valid.";

	public static void validateUser(User user) throws ValidationException {
		if (user.getUserName() == null || user.getUserName().length() < 3 || user.getUserName().length() > 20) {
			throw new ValidationException(USER_NAME_IS_NOT_VALID);
		}
		if (user.getPassword() == null || user.getPassword().length() < 3 || user.getPassword().length() > 100) {
			throw new ValidationException(PASSWORD_IS_NOT_VALID);
		}
		if (user.getFirstName() == null || user.getFirstName().length() < 3 || user.getFirstName().length() > 20) {
			throw new ValidationException(FIRST_NAME_IS_NOT_VALID);
		}
		if (user.getLastName() == null || user.getLastName().length() < 3 || user.getLastName().length() > 20) {
			throw new ValidationException(LAST_NAME_IS_NOT_VALID);
		}
		if (user.getEmail() == null || user.getEmail().length() < 10 || user.getEmail().length() > 100) {
			throw new ValidationException(EMAIL_IS_NOT_VALID);
		}
		if (!validateEmail(user.getEmail())) {
			throw new ValidationException(EMAIL_IS_NOT_VALID);
		}
		if (user.getPhoneNumber() == null || user.getPhoneNumber().length() < 10
				|| user.getPhoneNumber().length() > 30) {
			throw new ValidationException(PHONE_NUMBER_IS_NOT_VALID);
		}
	}

	private static boolean validateEmail(String email) {
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);;
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

}


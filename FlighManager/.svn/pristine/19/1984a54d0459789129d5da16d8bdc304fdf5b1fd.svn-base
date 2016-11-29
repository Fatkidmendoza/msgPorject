package edu.msg.flightmanager.backend.util;

import edu.msg.flightmanager.backend.model.Company;

public class CompanyValidator {

	private static final String NAME_IS_NOT_VALID = "The name is not valid.";

	public static void validateCompany(Company company) throws ValidationException {
		if (company.getName() == null || company.getName().length() < 2 || company.getName().length() > 50) {
			throw new ValidationException(NAME_IS_NOT_VALID);
		}
	}

}

package edu.msg.flightmanager.backend.util;

import edu.msg.flightmanager.backend.model.Airport;

public class AirportValidator {
	private static final String NAME_NOT_VALID = "The name is not valid.";
	private static final String ADDRESS_NOT_VALID = "The adress is not valid.";
	private static final String COUNTRY_NOT_VALID = "The country is not valid.";
	private static final String CITY_NOT_VALID = "The city is not valid.";

	public static void validate(Airport airport) throws ValidationException {
		validateStringField(airport.getName(), NAME_NOT_VALID);
		validateStringField(airport.getAddress(), ADDRESS_NOT_VALID);
		validateStringField(airport.getCity(), CITY_NOT_VALID);
		validateStringField(airport.getCountry(), COUNTRY_NOT_VALID);

	}

	private static void validateStringField(String field, String message) {
		if (field == null || field.length() < 2 || field.length() > 50) {
			throw new ValidationException(message);
		}
	}

}

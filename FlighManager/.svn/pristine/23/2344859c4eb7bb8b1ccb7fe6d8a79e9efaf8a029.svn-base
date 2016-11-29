package edu.msg.flightmanager.backend.util;

import edu.msg.flightmanager.backend.model.Plane;

public class PlaneValidator {

	private static final String NUMBER_OF_SEATS_IS_NOT_VALID = "The number of seats is not valid.";

	public static void validatePlane(Plane plane) throws ValidationException {
		if(plane.getNumberOfPlaces() == null || plane.getNumberOfPlaces() < 10 || plane.getNumberOfPlaces() > 1000) {
			throw new ValidationException(NUMBER_OF_SEATS_IS_NOT_VALID);
		}
	}
}

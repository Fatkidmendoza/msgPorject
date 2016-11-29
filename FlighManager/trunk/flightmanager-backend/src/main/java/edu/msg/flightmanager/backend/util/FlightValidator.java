package edu.msg.flightmanager.backend.util;

import java.util.Calendar;

import edu.msg.flightmanager.backend.model.Flight;
import edu.msg.flightmanager.backend.model.User;
import edu.msg.flightmanager.backend.model.UserType;

public class FlightValidator {

	private static final String DATE_IS_NOT_VALID = "The date is not valid.";
	private static final String NUMBER_OF_CREW_MEMBERS_NOT_VALID = "Number of crew members is not valid.";

	public static void validateFlight(Flight flight) throws ValidationException {
		FlightTemplateValidator.validateFlightTemplate(flight.getTemplate());
		PlaneValidator.validatePlane(flight.getPlane());

		if (flight.getDate().before(Calendar.getInstance().getTime())) {
			throw new ValidationException(DATE_IS_NOT_VALID);
		}

		int nrPilots = 0, nrStewards = 0;
		for (User user : flight.getAircrewUsers()) {
			if (user.getType().equals(UserType.PILOT)) {
				nrPilots++;
			} else if (user.getType().equals(UserType.STEWARD)) {
				nrStewards++;
			}
		}
		if (nrPilots != 2 || nrStewards != 4) {
			throw new ValidationException(NUMBER_OF_CREW_MEMBERS_NOT_VALID);
		}

	}

}
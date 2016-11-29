package edu.msg.flightmanager.backend.util;

import edu.msg.flightmanager.backend.model.FlightTemplate;

public class FlightTemplateValidator {

	private static final String THE_SAME_AIRPORTS = "Departure can't be the same with arrival.";

	public static void validateFlightTemplate(FlightTemplate flightTemplate) {
		// validate airport and intinerary using their specific validators

		if (flightTemplate.getDeparture().getId() == flightTemplate.getArrival().getId()) {
			throw new ValidationException(THE_SAME_AIRPORTS);
		}
	}

}

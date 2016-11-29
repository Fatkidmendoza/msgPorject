package edu.msg.flightmanager.backend.util;

import java.util.Calendar;

import edu.msg.flightmanager.backend.model.Itinerary;

public class ItineraryValidator {

	private static final String DURATION_TOO_SHORT = "Duration is too short";

	public static void validate(Itinerary itinerary) throws ValidationException {
		Calendar calendar = Calendar.getInstance();
		//		calendar.setTime(itinerary.getDuration());
		//		int numberOfMinutes = calendar.get(Calendar.MINUTE)+calendar.get(calendar.HOUR_OF_DAY)*60;
		//		if (numberOfMinutes < 30) {
		//			throw new ValidationException(DURATION_TOO_SHORT);
		//		}

	}

}

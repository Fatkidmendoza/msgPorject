package edu.msg.flightmanager.backend.assembler;

import edu.msg.flightmanager.backend.dto.ItineraryDto;
import edu.msg.flightmanager.backend.model.Itinerary;
import edu.msg.flightmanager.backend.model.PeriodicityType;

/**
 * The class converts from itinerary model object to dto object, and reverse
 */
public class ItineraryAssembler  {

	public static Itinerary dtoToModel(ItineraryDto itineraryDto){
		Itinerary itinerary = new Itinerary();
		itinerary.setUuid(itineraryDto.getUuid());
		itinerary.setId(itineraryDto.getId());
		itinerary.setPeriodicity(PeriodicityType.valueOf(itineraryDto.getPeriodicityName()));
		itinerary.setDeleted(itineraryDto.isDeleted());
		itinerary.setStartTime(itineraryDto.getStartTime());
		itinerary.setEndTime(itineraryDto.getEndTime());
		itinerary.setNextDay(itineraryDto.isNextDay());
		itinerary.setVersion(itineraryDto.getVersion());
		itinerary.setCode(itineraryDto.getCode());
		return itinerary;
	}

	public static ItineraryDto modelToDto(Itinerary itinerary){
		ItineraryDto itineraryDto = new ItineraryDto();
		itineraryDto.setUuid(itinerary.getUuid());
		itineraryDto.setId(itinerary.getId());
		itineraryDto.setPeriodicityName(itinerary.getPeriodicity().name());
		itineraryDto.setDeleted(itinerary.isDeleted());
		itineraryDto.setStartTime(itinerary.getStartTime());
		itineraryDto.setEndTime(itinerary.getEndTime());
		itineraryDto.setNextDay(itinerary.isNextDay());
		itineraryDto.setCode(itinerary.getCode());
		itineraryDto.setVersion(itinerary.getVersion());
		return itineraryDto;
	}



}

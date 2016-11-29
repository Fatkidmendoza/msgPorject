package edu.msg.flightmanager.backend.assembler;

import edu.msg.flightmanager.backend.dto.AirportDto;
import edu.msg.flightmanager.backend.model.Airport;

/**
 * The class converts from airport model object to dto object, and reverse
 */
public class AirportAssembler {

	public static AirportDto modelToDto(Airport airport) {
		AirportDto airportDto = new AirportDto();
		airportDto.setUuid(airport.getUuid());
		airportDto.setVersion(airport.getVersion());
		airportDto.setId(airport.getId());
		airportDto.setName(airport.getName());
		airportDto.setCountry(airport.getCountry());
		airportDto.setCity(airport.getCity());
		airportDto.setAddress(airport.getAddress());
		airportDto.setDeleted(airport.isDeleted());
		airportDto.setCode(airport.getCode());
		airportDto.setTimezone(airport.getTimezone());

		return airportDto;
	}

	public static Airport dtoToModel(AirportDto airportDto) {
		Airport airport = new Airport();
		airport.setUuid(airportDto.getUuid());
		airport.setId(airportDto.getId());
		airport.setVersion(airportDto.getVersion());
		airport.setName(airportDto.getName());
		airport.setCountry(airportDto.getCountry());
		airport.setCity(airportDto.getCity());
		airport.setAddress(airportDto.getAddress());
		airport.setDeleted(airportDto.isDeleted());
		airport.setCode(airportDto.getCode());
		airport.setTimezone(airportDto.getTimezone());
		return airport;
	}

}

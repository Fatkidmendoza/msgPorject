package edu.msg.flightmanager.backend.assembler;

import java.util.ArrayList;
import java.util.List;

import edu.msg.flightmanager.backend.dto.FlightDto;
import edu.msg.flightmanager.backend.dto.UserDto;
import edu.msg.flightmanager.backend.model.Flight;
import edu.msg.flightmanager.backend.model.User;

/**
 * The class converts from flight model object to dto object, and reverse
 */
public class FlightAssembler {

	public static FlightDto modelToDto(Flight flight) {
		FlightDto flightDto = new FlightDto();
		flightDto.setUuid(flight.getUuid());
		flightDto.setId(flight.getId());
		flightDto.setVersion(flight.getVersion());
		flightDto.setDate(flight.getDate());
		flightDto.setPassed(flight.isPassed());
		List<UserDto> aircrewUsers = new ArrayList<UserDto>();
		for (User user : flight.getAircrewUsers()) {
			aircrewUsers.add(UserAssembler.modelToDto(user));
		}
		flightDto.setAircrewUsers(aircrewUsers);
		flightDto.setTemplate(FlightTemplateAssembler.modelToDto(flight.getTemplate()));
		flightDto.setPlane(PlaneAssembler.modelToDto(flight.getPlane()));
		flightDto.setDeleted(flight.isDeleted());
		flightDto.setCode(flight.getCode());
		return flightDto;
	}

	public static Flight dtoToModel(FlightDto flightDto) {
		Flight flight = new Flight();
		flight.setUuid(flightDto.getUuid());
		flight.setId(flightDto.getId());
		flight.setDate(flightDto.getDate());
		flight.setPassed(flightDto.isPassed());
		flight.setVersion(flightDto.getVersion());
		List<User> aircrewUsers = new ArrayList<User>();
		for (UserDto userDto : flightDto.getAircrewUsers()) {
			aircrewUsers.add(UserAssembler.dtoToModel(userDto));
		}
		flight.setAircrewUsers(aircrewUsers);
		flight.setTemplate(FlightTemplateAssembler.dtoToModel(flightDto.getTemplate()));
		flight.setPlane(PlaneAssembler.dtoToModel(flightDto.getPlane()));
		flight.setDeleted(flightDto.isDeleted());
		flight.setCode(flightDto.getCode());
		return flight;

	}

}

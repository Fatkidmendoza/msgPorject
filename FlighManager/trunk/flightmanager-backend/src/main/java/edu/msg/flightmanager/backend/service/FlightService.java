package edu.msg.flightmanager.backend.service;

import java.io.File;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import edu.msg.flightmanager.backend.dto.FlightDto;
import edu.msg.flightmanager.backend.dto.FlightTemplateDto;
import edu.msg.flightmanager.backend.dto.UserDto;
import edu.msg.flightmanager.backend.model.FlightTemplate;

@Local
public interface FlightService {

	List<FlightDto> getAll() throws ServiceException;

	FlightDto update(FlightDto flightDto) throws ServiceException;

	FlightDto insert(FlightDto flightDto) throws ServiceException;

	void delete(FlightDto flightDto) throws ServiceException;

	void activate(FlightDto flightDto) throws ServiceException;

	List<FlightDto> getHistory() throws ServiceException;

	File getCsvFileWithFlights(Date startDate, Date endDate) throws ServiceException;

	Map<String, Integer> readFromCsvFile(InputStream inputStream);

	List<FlightDto> getAvailableFlight() throws ServiceException;

	Map<UserDto, Long> reportWithAllFlightHoursOfAllCrewMembers(Date startDate, Date endDate, String userType, String companyName) throws ServiceException;

	List<FlightDto> getTheFlightsOfAUser(UserDto userDto) throws ServiceException;

	List<FlightDto> getFlightsOfACompany(String companyName) throws ServiceException;
	
	void insertNextFlights(FlightTemplateDto template, Date date) throws ServiceException;
}

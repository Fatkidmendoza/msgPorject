package edu.msg.flightmanager.backend.service;

import java.util.List;

import javax.ejb.Local;

import edu.msg.flightmanager.backend.dto.AirportDto;

@Local
public interface AirportService {

	List<AirportDto> getAll() throws ServiceException;

	AirportDto insert(AirportDto airportDto) throws ServiceException;

	void delete(AirportDto airportDto) throws ServiceException;

	void activate(AirportDto airportDto) throws ServiceException;

	AirportDto update(AirportDto airportDto);

}

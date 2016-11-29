package edu.msg.flightmanager.backend.service;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import edu.msg.flightmanager.backend.dto.ItineraryDto;

@Local
public interface ItineraryService {

	List<ItineraryDto> getAll() throws ServiceException;

	ItineraryDto insert(ItineraryDto itineraryDto) throws ServiceException;

	void delete(ItineraryDto itineraryDto) throws ServiceException;

	void activate(ItineraryDto itineraryDto) throws ServiceException;

	List<String> getPeriodicityTypes();

	ItineraryDto update(ItineraryDto itineraryDto) throws ServiceException;

	File getCsvFileWithItineraries() throws ServiceException;

	Map<String, Integer> readFromCsvFile(InputStream inputStream);

}

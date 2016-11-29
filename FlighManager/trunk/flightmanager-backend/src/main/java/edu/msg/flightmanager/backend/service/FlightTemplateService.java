package edu.msg.flightmanager.backend.service;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import edu.msg.flightmanager.backend.dto.FlightTemplateDto;

@Local
public interface FlightTemplateService {

	List<FlightTemplateDto> getAll() throws ServiceException;

	FlightTemplateDto update(FlightTemplateDto flightTemplateDto) throws ServiceException;

	FlightTemplateDto insert(FlightTemplateDto flightTemplateDto) throws ServiceException;

	void delete(FlightTemplateDto flightTemplateDto) throws ServiceException;

	void activate(FlightTemplateDto flightTemplateDto) throws ServiceException;

	File getCsvFileWithFlightTemplates() throws ServiceException;

	Map<String, Integer> readFromCsvFile(InputStream inputStream);

}

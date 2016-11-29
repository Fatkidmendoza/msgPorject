package edu.msg.flightmanager.backend.service;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import edu.msg.flightmanager.backend.dto.PlaneDto;

@Local
public interface PlaneService {
	List<PlaneDto> getByCompany(String companyName) throws ServiceException;

	List<PlaneDto> getAll() throws ServiceException;

	PlaneDto update(PlaneDto planeDto) throws ServiceException;

	PlaneDto insert(PlaneDto planeDto) throws ServiceException;

	void delete(PlaneDto planeDto) throws ServiceException;

	void activate(PlaneDto planeDto) throws ServiceException;

	List<String> getAllTypes();

	List<String> getAllMakes();

	File getCsvFileWithPlanes(String companyName) throws ServiceException;

	Map<String, Integer> readFromCsvFile(InputStream inputStream);

}

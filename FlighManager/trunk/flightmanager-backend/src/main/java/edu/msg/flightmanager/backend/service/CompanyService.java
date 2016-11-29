package edu.msg.flightmanager.backend.service;

import java.util.List;

import javax.ejb.Local;

import edu.msg.flightmanager.backend.dto.CompanyDto;

@Local
public interface CompanyService {

	List<CompanyDto> getAll() throws ServiceException;

	CompanyDto getByName(String name) throws ServiceException;

	CompanyDto update(CompanyDto companyDto) throws ServiceException;

	CompanyDto insert(CompanyDto companyDto) throws ServiceException;

	void delete(CompanyDto companyDto) throws ServiceException;

	void activate(CompanyDto companyDto) throws ServiceException;

}
package edu.msg.flightmanager.backend.service.beans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import edu.msg.flightmanager.backend.assembler.CompanyAssembler;
import edu.msg.flightmanager.backend.assembler.PlaneAssembler;
import edu.msg.flightmanager.backend.assembler.UserAssembler;
import edu.msg.flightmanager.backend.dto.CompanyDto;
import edu.msg.flightmanager.backend.model.Company;
import edu.msg.flightmanager.backend.model.Plane;
import edu.msg.flightmanager.backend.model.User;
import edu.msg.flightmanager.backend.repository.CompanyRepository;
import edu.msg.flightmanager.backend.repository.RepositoryException;
import edu.msg.flightmanager.backend.service.CompanyService;
import edu.msg.flightmanager.backend.service.PlaneService;
import edu.msg.flightmanager.backend.service.ServiceException;
import edu.msg.flightmanager.backend.service.UserService;
import edu.msg.flightmanager.backend.util.CompanyValidator;
import edu.msg.flightmanager.backend.util.ValidationException;

@Stateless(name = "CompanyService", mappedName = "ejb/CompanyService")
public class CompanyServiceBean implements CompanyService {

	@EJB
	private CompanyRepository companyRepository;

	@EJB
	private UserService userService;

	@EJB
	private PlaneService planeService;

	@Override
	public List<CompanyDto> getAll() throws ServiceException {
		try {
			List<CompanyDto> companiesDto = new ArrayList<CompanyDto>();
			for (Company company :companyRepository.getAll()) {
				companiesDto.add(CompanyAssembler.modelToDto(company));
			}
			return companiesDto;
		} catch (RepositoryException e) {
			throw new ServiceException("The companies selection failed");
		}
	}

	@Override
	public CompanyDto getByName(String name) throws ServiceException {
		try{
			Company company = companyRepository.getByName(name);
			return CompanyAssembler.modelToDto(company);
		} catch(RepositoryException e) {
			throw new ServiceException("Company selection by name failed.");
		}
	}

	@Override
	public CompanyDto update(CompanyDto companyDto) throws ServiceException {
		Company company = CompanyAssembler.dtoToModel(companyDto);
		try {
			CompanyValidator.validateCompany(company);
			company = companyRepository.update(company);
			CompanyDto updatedCompany = CompanyAssembler.modelToDto(company);
			return updatedCompany;
		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage());
		} catch (RepositoryException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public CompanyDto insert(CompanyDto companyDto) throws ServiceException {
		Company company = CompanyAssembler.dtoToModel(companyDto);
		try{
			CompanyValidator.validateCompany(company);
			company = companyRepository.insert(company);
			CompanyDto insertedCompany = CompanyAssembler.modelToDto(company);
			return insertedCompany;
		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage());
		} catch (RepositoryException e) {
			throw new ServiceException(e.getMessage());
		}

	}

	@Override
	public void delete(CompanyDto companyDto) throws ServiceException {
		Company company = CompanyAssembler.dtoToModel(companyDto);
		try {
			//the airports don't have to be deleted because they don't belong to a single company
			company.setAirports(new ArrayList<>());
			//the users have to be deleted because they belong to a single company
			for(User user : company.getEmployees()) {
				userService.delete(UserAssembler.modelToDto(user));
			}
			//the planes have to be deleted because they belong to a single company
			for(Plane plane : company.getPlanes()) {
				planeService.delete(PlaneAssembler.modelToDto(plane));
			}
			companyRepository.delete(company);
		} catch (RepositoryException e) {
			throw new ServiceException(e.getMessage());
		} catch (ServiceException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void activate(CompanyDto companyDto) throws ServiceException {
		Company company = CompanyAssembler.dtoToModel(companyDto);
		try {
			companyRepository.activate(company);
		} catch(RepositoryException e) {
			throw new ServiceException(e.getMessage());
		}
	}

}
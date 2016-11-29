package edu.msg.flightmanager.backend.service.beans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.validator.ValidatorException;

import edu.msg.flightmanager.backend.assembler.AirportAssembler;
import edu.msg.flightmanager.backend.dto.AirportDto;
import edu.msg.flightmanager.backend.model.Airport;
import edu.msg.flightmanager.backend.model.Flight;
import edu.msg.flightmanager.backend.model.FlightTemplate;
import edu.msg.flightmanager.backend.repository.AirportRepository;
import edu.msg.flightmanager.backend.repository.FlightRepository;
import edu.msg.flightmanager.backend.repository.FlightTemplateRepository;
import edu.msg.flightmanager.backend.repository.RepositoryException;
import edu.msg.flightmanager.backend.service.AirportService;
import edu.msg.flightmanager.backend.service.ServiceException;
import edu.msg.flightmanager.backend.util.AirportValidator;
import edu.msg.flightmanager.backend.util.ValidationException;

@Stateless(name = "AirportService", mappedName = "ejb/AirportService")
public class AirportServiceBean implements AirportService {

	@EJB
	private AirportRepository airportRepository;

	@EJB
	private FlightTemplateRepository flightTemplateRepository;

	@EJB
	private FlightRepository flightRepository;

	@Override
	public List<AirportDto> getAll() throws ServiceException {
		try {
			List<AirportDto> airportsDto = new ArrayList<AirportDto>();
			for (Airport airport : airportRepository.getAll()) {
				airportsDto.add(AirportAssembler.modelToDto(airport));
			}
			return airportsDto;
		} catch (RepositoryException e) {
			throw new ServiceException("The airports selection failed");
		}
	}

	@Override
	public AirportDto insert(AirportDto airportDto) throws ServiceException {
		Airport airport = AirportAssembler.dtoToModel(airportDto);
		if (airport.getCode().equals("")) {
			throw new ServiceException("Code can not be empty");
		}
		try {
			AirportValidator.validate(airport);
			airportRepository.insert(airport);
			return AirportAssembler.modelToDto(airport);
		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage());
		} catch (RepositoryException e) {
			throw new ServiceException("Insert a new airport failed");
		}
	}

	@Override
	public void delete(AirportDto airportDto) throws ServiceException {
		try {
			Airport airport = AirportAssembler.dtoToModel(airportDto);
			List<FlightTemplate> flightTemplates = flightTemplateRepository.getTheFlightTemplatesThatHaveAnAirport(airport);
			for(FlightTemplate flightTemplate : flightTemplates) {
				List<Flight> flights = flightRepository.getFlightsThatHaveAFlightTemplate(flightTemplate);
				for(Flight flight : flights) {
					flightRepository.delete(flight);
				}
				flightTemplateRepository.delete(flightTemplate);
			}
			airportRepository.delete(airport);
		} catch (RepositoryException e) {
			throw new ServiceException("Delete user failed");
		}

	}

	@Override
	public void activate(AirportDto airportDto) throws ServiceException {
		try {
			Airport airport = AirportAssembler.dtoToModel(airportDto);
			airportRepository.activate(airport);
		} catch (RepositoryException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public AirportDto update(AirportDto airportDto) {
		Airport airport = AirportAssembler.dtoToModel(airportDto);
		if (airport.getCode().equals("")) {
			throw new ServiceException("Code can not be empty");
		}
		try {
			AirportValidator.validate(airport);
			airport = airportRepository.update(airport);
			airportDto = AirportAssembler.modelToDto(airport);
			return airportDto;
		} catch (ValidatorException e) {
			throw new ServiceException(e.getMessage());
		} catch (RepositoryException e) {
			throw new ServiceException(e.getMessage());
		}
	}

}

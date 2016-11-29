package edu.msg.flightmanager.backend.service.beans;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import edu.msg.flightmanager.backend.assembler.FlightTemplateAssembler;
import edu.msg.flightmanager.backend.dto.FlightTemplateDto;
import edu.msg.flightmanager.backend.exception.CsvException;
import edu.msg.flightmanager.backend.model.Airport;
import edu.msg.flightmanager.backend.model.Company;
import edu.msg.flightmanager.backend.model.Flight;
import edu.msg.flightmanager.backend.model.FlightTemplate;
import edu.msg.flightmanager.backend.model.Itinerary;
import edu.msg.flightmanager.backend.repository.AirportRepository;
import edu.msg.flightmanager.backend.repository.CompanyRepository;
import edu.msg.flightmanager.backend.repository.FlightRepository;
import edu.msg.flightmanager.backend.repository.FlightTemplateRepository;
import edu.msg.flightmanager.backend.repository.ItineraryRepository;
import edu.msg.flightmanager.backend.repository.RepositoryException;
import edu.msg.flightmanager.backend.service.FlightTemplateService;
import edu.msg.flightmanager.backend.service.ServiceException;
import edu.msg.flightmanager.backend.util.CsvFileReader;
import edu.msg.flightmanager.backend.util.CsvFileWriter;
import edu.msg.flightmanager.backend.util.FlightTemplateValidator;
import edu.msg.flightmanager.backend.util.ValidationException;

@Stateless(name = "FlightTemplateService", mappedName = "ejb/FlightTemplateService")
public class FlightTemplateServiceBean implements FlightTemplateService {

	@EJB
	private FlightTemplateRepository flightTemplateRepository;

	@EJB
	private FlightRepository flightRepository;

	@EJB
	private AirportRepository airportRepository;

	@EJB
	private ItineraryRepository itineraryRepository;

	@EJB
	private CompanyRepository companyRepository;

	@Override
	public List<FlightTemplateDto> getAll() throws ServiceException {
		try {
			List<FlightTemplateDto> templates = new ArrayList<FlightTemplateDto>();
			for (FlightTemplate flightTemplate : flightTemplateRepository.getAll()) {
				templates.add(FlightTemplateAssembler.modelToDto(flightTemplate));
			}
			return templates;
		} catch (RepositoryException e) {
			throw new ServiceException("The flight templates selection failed");
		}
	}

	@Override
	public FlightTemplateDto insert(FlightTemplateDto flightTemplateDto) throws ServiceException {
		FlightTemplate flightTemplate = FlightTemplateAssembler.dtoToModel(flightTemplateDto);

		if (flightTemplate.getCode().equals("")) {
			throw new ServiceException("Code can not be empty");
		}
		try {
			FlightTemplateValidator.validateFlightTemplate(flightTemplate);
			if (isAvailableForInsert(flightTemplate)) {
				flightTemplate = flightTemplateRepository.insert(flightTemplate);
				flightTemplateDto = FlightTemplateAssembler.modelToDto(flightTemplate);
				return flightTemplateDto;
			} else {
				throw new ServiceException("FlightTemplate exists in database, you cann't have the same details");
			}
		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage());
		} catch (RepositoryException e) {
			throw new ServiceException("Flight Template insertion failed.");
		}
	}

	@Override
	public void delete(FlightTemplateDto flightTemplateDto) throws ServiceException {
		FlightTemplate flightTemplate = FlightTemplateAssembler.dtoToModel(flightTemplateDto);
		try {
			List<Flight> flights = flightRepository.getFlightsThatHaveAFlightTemplate(flightTemplate);
			// physically delete all flights that have this flight template
			for (Flight flight : flights) {
				flightRepository.physicalDelete(flight);
			}
			flightTemplateRepository.delete(flightTemplate);
		} catch (RepositoryException e) {
			throw new ServiceException("Flight Template deletion failed.");
		}
	}

	@Override
	public void activate(FlightTemplateDto flightTemplateDto) throws ServiceException {
		FlightTemplate flightTemplate = FlightTemplateAssembler.dtoToModel(flightTemplateDto);
		try {
			flightTemplateRepository.activate(flightTemplate);
		} catch (RepositoryException e) {
			throw new ServiceException("Flight Template activation failed.");
		}
	}

	@Override
	public FlightTemplateDto update(FlightTemplateDto flightTemplateDto) throws ServiceException {
		FlightTemplate flightTemplate = FlightTemplateAssembler.dtoToModel(flightTemplateDto);
		if (flightTemplate.getCode().equals("")) {
			throw new ServiceException("Code can not be empty");
		}
		try {
			if (isAvailableForUpdate(flightTemplate)) {
				flightTemplate = flightTemplateRepository.update(flightTemplate);
				flightTemplateDto = FlightTemplateAssembler.modelToDto(flightTemplate);
				return flightTemplateDto;
			} else {
				throw new ServiceException("FlightTemplate with the same details exists in database, you cann't have the same details");
			}
		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage());
		} catch (RepositoryException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public File getCsvFileWithFlightTemplates() throws ServiceException {
		List<FlightTemplate> flightTemplates;
		try {
			flightTemplates = flightTemplateRepository.getAll();
		} catch (RepositoryException e) {
			throw new ServiceException(e.getMessage());
		}
		List<List<String>> flightTemplatesAsListsOfStrings = new ArrayList<>();
		for (FlightTemplate flightTemplate : flightTemplates) {
			List<String> flightTemplateAsString = new ArrayList<>();
			flightTemplateAsString.add(flightTemplate.getCode());
			flightTemplateAsString.add(String.valueOf(flightTemplate.isDeleted()));
			flightTemplateAsString.add(flightTemplate.getArrival().getCode());
			flightTemplateAsString.add(flightTemplate.getDeparture().getCode());
			flightTemplateAsString.add(flightTemplate.getItinerary().getCode());
			flightTemplateAsString.add(new SimpleDateFormat("yyy-MM-dd").format(flightTemplate.getAvailableFrom()));
			flightTemplateAsString.add(flightTemplate.getCompany().getName());

			flightTemplatesAsListsOfStrings.add(flightTemplateAsString);
		}
		String fileHeader = "Code,Deleted,ArrivalCode,DepartureCode,ItineraryCode,AvailableFrom,CompanyName";

		File csvFile = new File("flightTemplates.csv");
		try {
			FileWriter fileWriter = new FileWriter(csvFile);
			CsvFileWriter.writeCsvFile(fileWriter, fileHeader, flightTemplatesAsListsOfStrings);
			return csvFile;
		} catch (IOException e) {
			throw new ServiceException(e.getMessage());
		}

	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NEVER)
	public Map<String, Integer> readFromCsvFile(InputStream inputStream) {
		List<List<String>> entities;
		try {
			entities = CsvFileReader.readCsvFile(inputStream);
		} catch (CsvException e) {
			Map<String, Integer> numberOfInserts = new HashMap<>();
			numberOfInserts.put("successful", 0);
			numberOfInserts.put("unsuccessful", 0);
			return numberOfInserts;
		}
		// read the header
		List<String> header = entities.get(0);
		// get the position of each field from the header
		Map<String, Integer> positionOfFields = new HashMap<>();
		for (int i = 0; i < header.size(); i++) {
			positionOfFields.put(header.get(i), i);
		}

		// read the entities, which start in the list from position 1
		Integer numberOfSuccessfulInserts = 0;
		Integer numberOfUnsuccessfulInserts = 0;
		for (int i = 1; i < entities.size(); i++) {
			List<String> entity = entities.get(i);
			try {
				String arrivalCode = entity.get(positionOfFields.get("ArrivalCode"));
				Airport arrival = airportRepository.getByCode(arrivalCode);

				String departureCode = entity.get(positionOfFields.get("DepartureCode"));
				Airport departure = airportRepository.getByCode(departureCode);

				String itineraryCode = entity.get(positionOfFields.get("ItineraryCode"));
				Itinerary itinerary = itineraryRepository.getByCode(itineraryCode);

				String companyName = entity.get(positionOfFields.get("CompanyName"));
				Company company = companyRepository.getByName(companyName);

				String[] dateAsListOfStrings = entity.get(positionOfFields.get("AvailableFrom")).split("-");
				LocalDate localDate = LocalDate.of(Integer.valueOf(dateAsListOfStrings[0]),
						Integer.valueOf(dateAsListOfStrings[1]), Integer.valueOf(dateAsListOfStrings[2]));
				Date availableFrom = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

				FlightTemplate flightTemplate = new FlightTemplate();
				flightTemplate.setCode(entity.get(positionOfFields.get("Code")));
				flightTemplate.setDeleted(Boolean.valueOf(entity.get(positionOfFields.get("Deleted"))));
				flightTemplate.setArrival(arrival);
				flightTemplate.setDeparture(departure);
				flightTemplate.setItinerary(itinerary);
				flightTemplate.setAvailableFrom(availableFrom);
				flightTemplate.setCompany(company);
				flightTemplateRepository.insert(flightTemplate);

				numberOfSuccessfulInserts++;
			} catch (RepositoryException | NullPointerException | IndexOutOfBoundsException
					| IllegalArgumentException e) {
				numberOfUnsuccessfulInserts++;
			}
		}

		Map<String, Integer> numberOfInserts = new HashMap<>();
		numberOfInserts.put("successful", numberOfSuccessfulInserts);
		numberOfInserts.put("unsuccessful", numberOfUnsuccessfulInserts);
		return numberOfInserts;
	}

	public boolean isAvailableForInsert(FlightTemplate flightTemplate) {
		for (FlightTemplate flightTemplateFromDB : flightTemplateRepository.getAll()) {
			if (flightTemplate.equals(flightTemplateFromDB)) {
				return false;
			}
		}
		return true;
	}

	public boolean isAvailableForUpdate(FlightTemplate flightTemplate) {
		for (FlightTemplate flightTemplateFromDB : flightTemplateRepository.getAll()) {
			if (flightTemplate.equals(flightTemplateFromDB) && flightTemplate.getId()!=flightTemplateFromDB.getId()) {
				return false;
			}
		}
		return true;
	}
}

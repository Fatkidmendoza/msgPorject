package edu.msg.flightmanager.backend.service.beans;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
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

import edu.msg.flightmanager.backend.assembler.ItineraryAssembler;
import edu.msg.flightmanager.backend.dto.ItineraryDto;
import edu.msg.flightmanager.backend.exception.CsvException;
import edu.msg.flightmanager.backend.model.Flight;
import edu.msg.flightmanager.backend.model.FlightTemplate;
import edu.msg.flightmanager.backend.model.Itinerary;
import edu.msg.flightmanager.backend.model.PeriodicityType;
import edu.msg.flightmanager.backend.repository.FlightRepository;
import edu.msg.flightmanager.backend.repository.FlightTemplateRepository;
import edu.msg.flightmanager.backend.repository.ItineraryRepository;
import edu.msg.flightmanager.backend.repository.RepositoryException;
import edu.msg.flightmanager.backend.service.ItineraryService;
import edu.msg.flightmanager.backend.service.ServiceException;
import edu.msg.flightmanager.backend.util.CsvFileReader;
import edu.msg.flightmanager.backend.util.CsvFileWriter;
import edu.msg.flightmanager.backend.util.ItineraryValidator;
import edu.msg.flightmanager.backend.util.ValidationException;

@Stateless(name = "ItineraryService", mappedName = "ejb/ItineraryService")
public class ItineraryServiceBean implements ItineraryService {

	@EJB
	private ItineraryRepository itineraryRepository;

	@EJB
	private FlightTemplateRepository flightTemplateRepository;

	@EJB
	private FlightRepository flightRepository;

	@Override
	public List<ItineraryDto> getAll() throws ServiceException {
		try {
			List<ItineraryDto> itineraries = new ArrayList<ItineraryDto>();
			for (Itinerary itinerary : itineraryRepository.getAll()) {
				itineraries.add(ItineraryAssembler.modelToDto(itinerary));
			}
			return itineraries;
		} catch (RepositoryException e) {
			throw new ServiceException("The itineraries selection failed");
		}
	}

	@Override
	public ItineraryDto insert(ItineraryDto itineraryDto) throws ServiceException {
		Itinerary itinerary = ItineraryAssembler.dtoToModel(itineraryDto);
		if (itinerary.getCode().equals("")) {
			throw new ServiceException("Code can not be empty");
		}
		try {
			ItineraryValidator.validate(itinerary);
			boolean isAvailable = isAvailableForInsert(itinerary);
			if (isAvailable) {
				itinerary = itineraryRepository.insert(itinerary);
				itineraryDto = ItineraryAssembler.modelToDto(itinerary);
				return itineraryDto;
			} else {
				throw new ServiceException("Itineray exists in database");
			}
		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage());
		} catch (RepositoryException e) {
			throw new ServiceException("Insert a new itinerary failed");
		}
	}

	@Override
	public void delete(ItineraryDto itineraryDto) throws ServiceException {
		try {
			Itinerary itinerary = ItineraryAssembler.dtoToModel(itineraryDto);
			List<FlightTemplate> flightTemplates = flightTemplateRepository
					.getTheFlightTemplatesThatHaveAnItinerary(itinerary);
			boolean futureFlightsForThisItineraryExist = false;
			for (FlightTemplate flightTemplate : flightTemplates) {
				List<Flight> flights = flightRepository.getFlightsThatHaveAFlightTemplate(flightTemplate);
				for (Flight flight : flights) {
					if (flight.getDate().compareTo(new Date()) > 0) {
						futureFlightsForThisItineraryExist = true;
					}
				}
			}
			if (futureFlightsForThisItineraryExist) {
				throw new ServiceException(
						"The itineray can not be deleted because there are upcoming flights that use it.");
			} else {
				itineraryRepository.delete(itinerary);
				for (FlightTemplate flightTemplate : flightTemplates) {
					flightTemplateRepository.delete(flightTemplate);
				}
			}
		} catch (RepositoryException e) {
			throw new ServiceException("Delete user failed");
		}
	}

	@Override
	public void activate(ItineraryDto itineraryDto) throws ServiceException {
		try {
			Itinerary itinerary = ItineraryAssembler.dtoToModel(itineraryDto);
			itineraryRepository.activate(itinerary);
		} catch (RepositoryException e) {
			throw new ServiceException(e.getMessage());
		}

	}

	@Override
	public List<String> getPeriodicityTypes() {
		List<String> types = new ArrayList<String>();
		for (int i = 0; i < PeriodicityType.values().length; i++) {
			types.add(PeriodicityType.values()[i].toString());
		}
		return types;
	}

	@Override
	public ItineraryDto update(ItineraryDto itineraryDto) throws ServiceException {
		Itinerary itinerary = ItineraryAssembler.dtoToModel(itineraryDto);
		if (itinerary.getCode().equals("")) {
			throw new ServiceException("Code can not be empty");
		}
		try {
			ItineraryValidator.validate(itinerary);
			if (isAvailableForUpdate(itinerary)) {
				itinerary = itineraryRepository.update(itinerary);
				itineraryDto = ItineraryAssembler.modelToDto(itinerary);
				return itineraryDto;
			} else {
				throw new ServiceException("Itineray exists in database, you cann't have the same details");
			}
		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage());
		} catch (RepositoryException e) {
			throw new ServiceException("Insert a new itinerary failed");
		}
	}

	public boolean isAvailableForInsert(Itinerary itinerary) {
		for (Itinerary itineraryFromDB : itineraryRepository.getAll()) {
			if (itinerary.equals(itineraryFromDB)) {
				return false;
			}
		}
		return true;
	}

	public boolean isAvailableForUpdate(Itinerary itinerary) {
		for (Itinerary itineraryFromDB : itineraryRepository.getAll()) {
			if (itinerary.equals(itineraryFromDB) && itinerary.getId() != itineraryFromDB.getId()) {
				return false;
			}
		}
		return true;
	}

	@Override
	public File getCsvFileWithItineraries() throws ServiceException {
		List<Itinerary> itineraries;
		try {
			itineraries = itineraryRepository.getAll();
		} catch (RepositoryException e) {
			throw new ServiceException(e.getMessage());
		}
		List<List<String>> itinerariesAsListsOfStrings = new ArrayList<>();
		for (Itinerary itinerary : itineraries) {
			List<String> itineraryAsString = new ArrayList<>();
			itineraryAsString.add(itinerary.getCode());
			itineraryAsString.add(String.valueOf(itinerary.isDeleted()));
			itineraryAsString.add(new SimpleDateFormat("HH:mm").format(itinerary.getStartTime()));
			itineraryAsString.add(new SimpleDateFormat("HH:mm").format(itinerary.getEndTime()));
			itineraryAsString.add(String.valueOf(itinerary.isNextDay()));
			itineraryAsString.add(itinerary.getPeriodicity().toString());

			itinerariesAsListsOfStrings.add(itineraryAsString);
		}
		String fileHeader = "Code,Deleted,StartTime,EndTime,NextDay,Periodicity";

		File csvFile = new File("itineraries.csv");
		try {
			FileWriter fileWriter = new FileWriter(csvFile);
			CsvFileWriter.writeCsvFile(fileWriter, fileHeader, itinerariesAsListsOfStrings);
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
		// get the positions of the fields
		Map<String, Integer> positionOfFields = new HashMap<>();
		for (int i = 0; i < header.size(); i++) {
			positionOfFields.put(header.get(i), i);
		}

		// starting from position 1, read the entities
		Integer numberOfSuccessfulInserts = 0;
		Integer numberOfUnsuccessfulInserts = 0;
		for (int i = 1; i < entities.size(); i++) {
			List<String> entity = entities.get(i);
			try {
				Itinerary itinerary = new Itinerary();
				itinerary.setCode(entity.get(positionOfFields.get("Code")));
				itinerary.setDeleted(Boolean.valueOf(entity.get(positionOfFields.get("Deleted"))));

				String[] startTimeAsStrings = entity.get(positionOfFields.get("StartTime")).split(":");
				LocalTime localStartTime = LocalTime.of(Integer.valueOf(startTimeAsStrings[0]), Integer.valueOf(startTimeAsStrings[1]));
				Instant startInstant = localStartTime.atDate(LocalDate.now()).atZone(ZoneId.systemDefault()).toInstant();
				Date startTime = Date.from(startInstant);
				itinerary.setStartTime(startTime);

				String[] endTimeAsStrings = entity.get(positionOfFields.get("EndTime")).split(":");
				LocalTime localEndTime = LocalTime.of(Integer.valueOf(endTimeAsStrings[0]), Integer.valueOf(endTimeAsStrings[1]));
				Instant endInstant = localEndTime.atDate(LocalDate.now()).atZone(ZoneId.systemDefault()).toInstant();
				Date endTime = Date.from(endInstant);
				itinerary.setEndTime(endTime);

				itinerary.setNextDay(Boolean.valueOf(entity.get(positionOfFields.get("NextDay"))));
				itinerary.setPeriodicity(PeriodicityType.valueOf(entity.get(positionOfFields.get("Periodicity"))));

				this.insert(ItineraryAssembler.modelToDto(itinerary));

				numberOfSuccessfulInserts++;
			} catch (RepositoryException | NullPointerException | IndexOutOfBoundsException
					| IllegalArgumentException e) {
				numberOfUnsuccessfulInserts++;
			} catch (ServiceException e) {
				numberOfUnsuccessfulInserts++;
			}
		}

		Map<String, Integer> numberOfInserts = new HashMap<>();
		numberOfInserts.put("successful", numberOfSuccessfulInserts);
		numberOfInserts.put("unsuccessful", numberOfUnsuccessfulInserts);
		return numberOfInserts;
	}

}

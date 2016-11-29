package edu.msg.flightmanager.backend.repository;

import java.util.List;

import javax.ejb.Local;

import edu.msg.flightmanager.backend.model.Airport;
import edu.msg.flightmanager.backend.model.FlightTemplate;
import edu.msg.flightmanager.backend.model.Itinerary;

@Local
public interface FlightTemplateRepository extends BaseRepository<FlightTemplate, Long> {

	public List<FlightTemplate> getTheFlightTemplatesThatHaveAnItinerary(Itinerary itinerary) throws RepositoryException;

	public List<FlightTemplate> getTheFlightTemplatesThatHaveAnAirport(Airport airport) throws RepositoryException;

	public FlightTemplate getFlightTemplateThatHasACode(String code) throws RepositoryException;

}

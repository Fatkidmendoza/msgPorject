package edu.msg.flightmanager.backend.repository;

import javax.ejb.Local;

import edu.msg.flightmanager.backend.model.Itinerary;

@Local
public interface ItineraryRepository extends BaseRepository<Itinerary, Long> {

	Itinerary getByCode(String code) throws RepositoryException;

}
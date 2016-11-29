package edu.msg.flightmanager.backend.repository;

import javax.ejb.Local;

import edu.msg.flightmanager.backend.model.Airport;

@Local
public interface AirportRepository extends BaseRepository<Airport, Long> {

	Airport getByCode(String code) throws RepositoryException;

}

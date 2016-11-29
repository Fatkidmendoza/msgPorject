package edu.msg.flightmanager.backend.repository;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import edu.msg.flightmanager.backend.model.Plane;

@Local
public interface PlaneRepository extends BaseRepository<Plane, Long> {
	List<Plane> getByCompany(String companyName) throws RepositoryException;

	Plane getPlaneThatHasACode(String code) throws RepositoryException;

	List<Plane> getAvailablePlanes(Date date) throws RepositoryException;
}
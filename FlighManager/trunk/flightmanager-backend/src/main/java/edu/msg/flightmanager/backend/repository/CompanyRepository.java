package edu.msg.flightmanager.backend.repository;

import javax.ejb.Local;

import edu.msg.flightmanager.backend.model.Company;

@Local
public interface CompanyRepository extends BaseRepository<Company, Long>{

	Company getByName(String name) throws RepositoryException;

}
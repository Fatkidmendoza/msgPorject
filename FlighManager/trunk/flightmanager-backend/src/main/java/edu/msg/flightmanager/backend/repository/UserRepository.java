package edu.msg.flightmanager.backend.repository;

import java.util.List;

import javax.ejb.Local;

import edu.msg.flightmanager.backend.model.User;
import edu.msg.flightmanager.backend.model.UserType;

@Local
public interface UserRepository extends BaseRepository<User, Long> {

	User getByUserName(String userName) throws RepositoryException;

	List<User> getByTypeAndCompany(UserType userType, String companyName) throws RepositoryException;

	List<User> getAllCrewMembers() throws RepositoryException;

	List<User> getByCompany(String companyName) throws RepositoryException;
}
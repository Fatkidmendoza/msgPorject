package edu.msg.flightmanager.backend.repository;

import javax.ejb.Local;
import javax.persistence.NoResultException;

import edu.msg.flightmanager.backend.model.Token;
import edu.msg.flightmanager.backend.model.User;

@Local
public interface TokenRepository extends BaseRepository<Token, Long>{

	Token getByValue (String value) throws RepositoryException,NoResultException;

	void deleteAllHavingUser(User user) throws RepositoryException;
}

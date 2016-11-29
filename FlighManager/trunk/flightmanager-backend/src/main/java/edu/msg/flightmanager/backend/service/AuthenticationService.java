package edu.msg.flightmanager.backend.service;

import javax.ejb.Local;

import edu.msg.flightmanager.backend.dto.TokenDto;
import edu.msg.flightmanager.backend.dto.UserDto;
import edu.msg.flightmanager.backend.exception.LogInException;
import edu.msg.flightmanager.backend.exception.WrongEmailException;

@Local
public interface AuthenticationService {

	public UserDto login(String userName, String password);

	UserDto validateToken(String token) throws ServiceException, LogInException;

	TokenDto generateToken(String username, String email) throws ServiceException, WrongEmailException;
}
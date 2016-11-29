package edu.msg.flightmanager.backend.assembler;

import edu.msg.flightmanager.backend.dto.UserDto;
import edu.msg.flightmanager.backend.model.Company;
import edu.msg.flightmanager.backend.model.User;
import edu.msg.flightmanager.backend.model.UserType;

/**
 * The class converts from user model to dto object, and reverse
 */

public class UserAssembler {

	public static User dtoToModel(UserDto userDto) {
		User user = new User();
		user.setUuid(userDto.getUuid());
		user.setUserName(userDto.getUserName());
		user.setPassword(userDto.getPassword());
		user.setId(userDto.getId());
		user.setEmail(userDto.getEmail());
		user.setPhoneNumber(userDto.getPhoneNumber());
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setType(UserType.valueOf(userDto.getType()));
		user.setDeleted(userDto.isDeleted());
		Company company = new Company();
		company.setName(userDto.getCompanyName());
		user.setCompany(company);
		user.setVersion(userDto.getVersion());
		return user;
	}

	public static UserDto modelToDto(User user) {
		UserDto userDto = new UserDto();
		userDto.setUuid(user.getUuid());
		userDto.setId(user.getId());
		userDto.setUserName(user.getUserName());
		userDto.setPhoneNumber(user.getPhoneNumber());
		userDto.setEmail(user.getEmail());
		userDto.setPassword(user.getPassword());
		userDto.setFirstName(user.getFirstName());
		userDto.setLastName(user.getLastName());
		userDto.setType(user.getType().name());
		userDto.setDeleted(user.isDeleted());
		userDto.setCompanyName(user.getCompany().getName());
		userDto.setVersion(user.getVersion());
		return userDto;

	}
}

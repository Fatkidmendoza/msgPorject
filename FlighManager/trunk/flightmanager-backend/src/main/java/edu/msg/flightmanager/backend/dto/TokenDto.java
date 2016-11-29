package edu.msg.flightmanager.backend.dto;

import java.util.Date;

public class TokenDto extends EntityDto{

	private String value;
	private UserDto user;
	private Date createdAt;
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public UserDto getUser() {
		return user;
	}
	public void setUser(UserDto user) {
		this.user = user;
	}



}

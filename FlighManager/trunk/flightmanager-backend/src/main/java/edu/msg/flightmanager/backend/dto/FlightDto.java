package edu.msg.flightmanager.backend.dto;

import java.util.Date;
import java.util.List;

public class FlightDto extends EntityDto {

	private List<UserDto> aircrewUsers;
	private Date date;
	private PlaneDto plane;
	private FlightTemplateDto template;
	private String code;
	private boolean passed;

	public List<UserDto> getAircrewUsers() {
		return aircrewUsers;
	}

	public void setAircrewUsers(List<UserDto> aircrewUsers) {
		this.aircrewUsers = aircrewUsers;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public PlaneDto getPlane() {
		return plane;
	}

	public void setPlane(PlaneDto plane) {
		this.plane = plane;
	}

	public FlightTemplateDto getTemplate() {
		return template;
	}

	public void setTemplate(FlightTemplateDto template) {
		this.template = template;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public boolean isPassed() {
		return passed;
	}

	public void setPassed(boolean passed) {
		this.passed = passed;
	}

}
package edu.msg.flightmanager.backend.dto;

import java.util.List;

public class CompanyDto extends EntityDto {

	private List<AirportDto> airports;
	private List<UserDto> employees;
	private String name;
	private List<PlaneDto> planes;
	private String headquarterCountry;
	private String headquarterCity;

	public String getHeadquarterCountry() {
		return headquarterCountry;
	}
	public void setHeadquarterCountry(String headquarterCountry) {
		this.headquarterCountry = headquarterCountry;
	}
	public String getHeadquarterCity() {
		return headquarterCity;
	}
	public void setHeadquarterCity(String headquarterCity) {
		this.headquarterCity = headquarterCity;
	}
	public List<AirportDto> getAirports() {
		return airports;
	}
	public void setAirports(List<AirportDto> airports) {
		this.airports = airports;
	}
	public List<UserDto> getEmployees() {
		return employees;
	}
	public void setEmployees(List<UserDto> employees) {
		this.employees = employees;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<PlaneDto> getPlanes() {
		return planes;
	}
	public void setPlanes(List<PlaneDto> planes) {
		this.planes = planes;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((airports == null) ? 0 : airports.hashCode());
		result = prime * result + ((employees == null) ? 0 : employees.hashCode());
		result = prime * result + ((headquarterCity == null) ? 0 : headquarterCity.hashCode());
		result = prime * result + ((headquarterCountry == null) ? 0 : headquarterCountry.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((planes == null) ? 0 : planes.hashCode());
		return result;
	}


}

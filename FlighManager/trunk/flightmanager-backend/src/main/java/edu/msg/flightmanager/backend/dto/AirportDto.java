package edu.msg.flightmanager.backend.dto;

public class AirportDto extends EntityDto implements Comparable<AirportDto> {

	private String code;
	private String name;
	private String city;
	private String country;
	private String address;
	private String timezone;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	@Override
	public int compareTo(AirportDto o) {
		return this.getCode().compareTo(o.getCode());
	}

}
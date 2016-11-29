package edu.msg.flightmanager.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "airport")
public class Airport extends BaseEntity {

	private static final long serialVersionUID = -3400079123061996933L;

	@Column(name = "code", nullable = false, length = 3, unique = true)
	private String code;

	@Column(name = "name", nullable = false, length = 50, unique = true)
	private String name;
	@Column(name = "country", nullable = false, length = 50)
	private String country;
	@Column(name = "city", nullable = false, length = 50)
	private String city;
	@Column(name = "adress", nullable = false)
	private String address;
	@Column(name = "timezone", nullable = false)
	private String timezone;

	public Airport() {
		this(null, null, null, null, null, null);
	}

	public Airport(String name, String country, String city, String address, String code, String timezone) {
		super();
		this.name = name;
		this.country = country;
		this.city = city;
		this.address = address;
		this.code = code;
		this.timezone = timezone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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
}

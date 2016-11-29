package edu.msg.flightmanager.backend.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "company")
public class Company extends BaseEntity {

	private static final long serialVersionUID = -6961257658295014899L;

	@Column(name = "name", nullable = false, length = 50, unique = true)
	private String name;

	@OneToMany(mappedBy = "company")
	private List<User> employees;

	@OneToMany(mappedBy = "company")
	private List<Plane> planes;

	@ManyToMany(targetEntity = Airport.class)
	@JoinTable(name = "company_airport", joinColumns = @JoinColumn(name = "companyId", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "airportId", referencedColumnName = "id"))
	private List<Airport> airports;

	@Column(name = "headquarterCountry", nullable = false, length = 50)
	private String headquarterCountry;

	@Column(name = "headquarterCity", nullable = false, length = 50)
	private String headquarterCity;

	public Company() {
		this(null, null, null, null);
	}

	public Company(String name, List<User> employees, List<Plane> planes, List<Airport> airports) {
		super();
		this.name = name;
		this.employees = employees;
		this.planes = planes;
		this.airports = airports;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<User> getEmployees() {
		return employees;
	}

	public void setEmployees(List<User> employees) {
		this.employees = employees;
	}

	public List<Plane> getPlanes() {
		return planes;
	}

	public void setPlanes(List<Plane> planes) {
		this.planes = planes;
	}

	public List<Airport> getAirports() {
		return airports;
	}

	public void setAirports(List<Airport> airports) {
		this.airports = airports;
	}

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

}

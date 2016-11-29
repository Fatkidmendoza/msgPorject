package edu.msg.flightmanager.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "plane")
public class Plane extends BaseEntity {

	private static final long serialVersionUID = -1495827416080732739L;

	@ManyToOne
	private Company company;

	@Column(name = "numberOfPlaces")
	private Integer numberOfPlaces;

	@Column(name = "type")
	@Enumerated(EnumType.STRING)
	private PlaneType type;

	@Column(name = "code", length = 4, unique = true, nullable = false)
	private  String code;

	@Column(name = "make")
	@Enumerated(EnumType.STRING)
	private PlaneMake make;

	public Plane() {
		this(null, null, null);
	}

	public Plane(Company company, Integer numberOfPlaces, PlaneType type) {
		super();
		this.company = company;
		this.numberOfPlaces = numberOfPlaces;
		this.type = type;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Integer getNumberOfPlaces() {
		return numberOfPlaces;
	}

	public void setNumberOfPlaces(Integer numberOfPlaces) {
		this.numberOfPlaces = numberOfPlaces;
	}

	public PlaneType getType() {
		return type;
	}

	public void setType(PlaneType type) {
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public PlaneMake getMake() {
		return make;
	}

	public void setMake(PlaneMake make) {
		this.make = make;
	}

}

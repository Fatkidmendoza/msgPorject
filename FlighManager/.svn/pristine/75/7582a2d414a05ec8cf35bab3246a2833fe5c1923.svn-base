package edu.msg.flightmanager.backend.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "flightTemplate")
public class FlightTemplate extends BaseEntity {

	private static final long serialVersionUID = 2500982579028031851L;

	@ManyToOne(targetEntity = Airport.class)
	@JoinColumn(name = "departureId")
	private Airport departure;

	@ManyToOne(targetEntity = Airport.class)
	@JoinColumn(name = "arrivalId")
	private Airport arrival;

	@ManyToOne(targetEntity = Itinerary.class)
	@JoinColumn(name = "itineraryId")
	private Itinerary itinerary;

	@Column(name = "code", unique = true, nullable = false)
	private String code;

	@Temporal(TemporalType.DATE)
	@Column(name = "availableFrom")
	private Date availableFrom;

	@ManyToOne(targetEntity = Company.class)
	private Company company;

	public FlightTemplate() {
		this(null, null, null);
	}

	public FlightTemplate(Airport departure, Airport arrival, Itinerary itinerary) {
		super();
		this.departure = departure;
		this.arrival = arrival;
		this.itinerary = itinerary;
	}

	public Airport getDeparture() {
		return departure;
	}

	public void setDeparture(Airport departure) {
		this.departure = departure;
	}

	public Airport getArrival() {
		return arrival;
	}

	public void setArrival(Airport arrival) {
		this.arrival = arrival;
	}

	public Itinerary getItinerary() {
		return itinerary;
	}

	public void setItinerary(Itinerary itinerary) {
		this.itinerary = itinerary;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getAvailableFrom() {
		return availableFrom;
	}

	public void setAvailableFrom(Date availableFrom) {
		this.availableFrom = availableFrom;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((arrival == null) ? 0 : arrival.hashCode());
		result = prime * result + ((availableFrom == null) ? 0 : availableFrom.hashCode());
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result + ((departure == null) ? 0 : departure.hashCode());
		result = prime * result + ((itinerary == null) ? 0 : itinerary.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		FlightTemplate other = (FlightTemplate) obj;
		if(!arrival.getCode().equals(other.arrival.getCode())){
			return false;
		}
		if(!departure.getCode().equals(other.departure.getCode())){
			return false;
		}
		if(!itinerary.equals(other.itinerary))
			return false;
		if(!company.getName().equals(other.company.getName())){
			return false;
		}
		return false;

	}



}

package edu.msg.flightmanager.backend.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "flight")
public class Flight extends BaseEntity {

	private static final long serialVersionUID = 123871473129418981L;

	@ManyToMany(targetEntity = User.class)
	@JoinTable(name = "flight_user", joinColumns = @JoinColumn(name = "flightId", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "userId", referencedColumnName = "id"))
	private List<User> aircrewUsers;

	@ManyToOne(targetEntity = FlightTemplate.class)
	@JoinColumn(name = "templateId")
	private FlightTemplate template;

	@ManyToOne(targetEntity = Plane.class)
	@JoinColumn(name = "planeId")
	private Plane plane;

	@Temporal(TemporalType.DATE)
	@Column(name = "date")
	private Date date;

	@Column(name = "code", unique = true, nullable = false)
	private String code;

	@Column (name="passed",columnDefinition="tinyint(1) default 0")
	private boolean passed;



	public Flight() {
		this(null, null, null, null);
	}

	public Flight(List<User> aircrewUsers, FlightTemplate template, Plane plane, Date date) {
		super();
		this.aircrewUsers = aircrewUsers;
		this.template = template;
		this.plane = plane;
		this.date = date;
	}

	public List<User> getAircrewUsers() {
		return aircrewUsers;
	}

	public void setAircrewUsers(List<User> aircrewUsers) {
		this.aircrewUsers = aircrewUsers;
	}

	public FlightTemplate getTemplate() {
		return template;
	}

	public void setTemplate(FlightTemplate template) {
		this.template = template;
	}

	public Plane getPlane() {
		return plane;
	}

	public void setPlane(Plane plane) {
		this.plane = plane;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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

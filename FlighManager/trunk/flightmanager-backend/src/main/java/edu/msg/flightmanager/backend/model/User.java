package edu.msg.flightmanager.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User extends BaseEntity {

	private static final long serialVersionUID = 3688035067172052154L;

	@Column(name = "userName", nullable = false, length = 20, unique = true)
	private String userName;
	@Column(name = "password", nullable = false, length = 100)
	private String password;
	@Column(name = "firstName", nullable = false, length = 100)
	private String firstName;
	@Column(name = "lastName", nullable = false, length = 20)
	private String lastName;
	@Column(name = "email", nullable = false, length = 100, unique = true)
	private String email;
	@Column(name = "phoneNumber", nullable = false, length = 30, unique = true)
	private String phoneNumber;
	@Column(name = "type", nullable = false)
	@Enumerated(EnumType.STRING)
	private UserType type;

	@JoinColumn(nullable=false)
	@ManyToOne
	private Company company;

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public User() {
		this(null, null, null, null, null, null, null, null);
	}

	public User(String userName, String password, String firstName, String lastName, String email, String phoneNumber,
			UserType type, Company company) {
		super();
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.type = type;
		this.company = company;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "User [userName=" + userName + ", password=" + password + ", firstName=" + firstName + ", lastName="
				+ lastName + ", email=" + email + ", phoneNumber=" + phoneNumber + ", type=" + type + "]";
	}

}

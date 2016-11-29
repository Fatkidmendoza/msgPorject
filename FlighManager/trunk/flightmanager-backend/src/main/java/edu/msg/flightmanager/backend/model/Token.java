package edu.msg.flightmanager.backend.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Token extends BaseEntity {

	private static final long serialVersionUID = 6820328713516042578L;

	@ManyToOne
	private User user;

	@Column(name = "value", nullable = false, length = 32, unique = true)
	private String value;

	@Column(name = "createdAt")
	private Date createdAt;

	public Token() {
		this(null,null, null);
	}

	public Token(User user, String value, Date createdAt) {
		this.user = user;
		this.value = value;
		this.createdAt = createdAt;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

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

}

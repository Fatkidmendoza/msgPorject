package edu.msg.flightmanager.backend.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;

@MappedSuperclass
public abstract class AbstractModel implements Serializable {

	private static final long serialVersionUID = -8562775502292544303L;

	@Column(name = "uuid", nullable = false, length = 36, unique = true)
	private String uuid;

	@Column (name="deleted",columnDefinition="tinyint(1) default 0")
	private boolean deleted;

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public String getUuid() {
		ensureUuid();
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	private void ensureUuid() {
		if (uuid == null) {
			uuid = UUID.randomUUID().toString();
		}

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getUuid() == null) ? 0 : getUuid().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractModel other = (AbstractModel) obj;
		if (getUuid() == null) {
			if (other.getUuid() != null)
				return false;
		} else if (!getUuid().equals(other.getUuid()))
			return false;
		return true;
	}

	@PrePersist
	private void onPrePersist() {
		ensureUuid();
	}

}

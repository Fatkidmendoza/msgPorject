package edu.msg.flightmanager.backend.model;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "itinerary")
public class Itinerary extends BaseEntity {

	private static final long serialVersionUID = -2862301088891646334L;
	@Temporal(TemporalType.TIME)
	@Column(name = "startTime", nullable = false)
	private Date startTime;

	@Temporal(TemporalType.TIME)
	@Column(name = "endTime", nullable = false)
	private Date endTime;

	@Column(name = "periodicity", nullable = false)
	@Enumerated(EnumType.STRING)
	private PeriodicityType periodicity;

	@Column(name = "nextDay", columnDefinition = "tinyint(1) default 0")
	private boolean nextDay;

	@Column(name = "code", length = 4, unique = true, nullable = false)
	private String code;

	public Itinerary() {
		this(null, null, null, false);
	}

	public Itinerary(Date startTime, Date endTime, PeriodicityType periodicity, boolean nextDay) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
		this.periodicity = periodicity;
		this.nextDay = nextDay;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public PeriodicityType getPeriodicity() {
		return periodicity;
	}

	public void setPeriodicity(PeriodicityType periodicity) {
		this.periodicity = periodicity;
	}

	public boolean isNextDay() {
		return nextDay;
	}

	public void setNextDay(boolean nextDay) {
		this.nextDay = nextDay;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (getClass() != obj.getClass())
			return false;

		Itinerary other = (Itinerary) obj;
		Calendar calendarTimeForThis = Calendar.getInstance();
		Calendar calendarTimeForOther = Calendar.getInstance();
		calendarTimeForThis.setTime(startTime);
		calendarTimeForOther.setTime(other.startTime);
		if (calendarTimeForThis.get(Calendar.HOUR_OF_DAY) != calendarTimeForOther.get(Calendar.HOUR_OF_DAY)
				|| calendarTimeForThis.get(Calendar.MINUTE) != calendarTimeForOther.get(Calendar.MINUTE))
			return false;
		calendarTimeForThis.setTime(endTime);
		calendarTimeForOther.setTime(other.endTime);
		if (calendarTimeForThis.get(Calendar.HOUR_OF_DAY) != calendarTimeForOther.get(Calendar.HOUR_OF_DAY)
				|| calendarTimeForThis.get(Calendar.MINUTE) != calendarTimeForOther.get(Calendar.MINUTE))
			return false;

		if (!periodicity.equals(other.periodicity))
			return false;
		if (nextDay != other.nextDay) {
			return false;
		}

		return true;

	}

}

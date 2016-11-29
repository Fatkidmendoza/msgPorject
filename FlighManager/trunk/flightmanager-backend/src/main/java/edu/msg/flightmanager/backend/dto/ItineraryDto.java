package edu.msg.flightmanager.backend.dto;

import java.util.Date;

public class ItineraryDto extends EntityDto implements Comparable<ItineraryDto> {

	private Date startTime;
	private Date endTime;
	private String periodicityName;
	private boolean nextDay;
	private String code;

	public String getPeriodicityName() {
		return periodicityName;
	}

	public void setPeriodicityName(String periodicityName) {
		this.periodicityName = periodicityName;
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
	public int compareTo(ItineraryDto o) {
		int compareStartTime = this.startTime.compareTo(o.getStartTime());
		if (compareStartTime == 0 ) {
			return this.endTime.compareTo(o.getEndTime());
		} else  {
			return compareStartTime;
		}
	}

}

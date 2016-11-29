package edu.msg.flightmanager.web.beans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import edu.msg.flightmanager.backend.dto.UserDto;
import edu.msg.flightmanager.backend.service.FlightService;

@ManagedBean
@RequestScoped
public class ReportsBean implements Serializable {

	private static final long serialVersionUID = -1515484868531116418L;

	@EJB
	private FlightService flightService;

	private Date startDate;
	private Date endDate;
	private List<Entry<UserDto, Long>> usersHours;

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getFormatedStartDate() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		if (startDate != null) {
			return dateFormat.format(startDate);
		} else {

			return dateFormat.format(new Date());
		}
	}

	public String getFormatedEndDate() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		if (endDate != null) {
			return dateFormat.format(endDate);
		} else {
			return dateFormat.format(new Date());
		}
	}

	public List<Entry<UserDto, Long>> getUsersHours() {
		generateUsersHours();
		return usersHours;
	}

	public void setUsersHours(List<Entry<UserDto, Long>> usersHours) {
		this.usersHours = usersHours;
	}

	public void generateUsersHours() {
		String userType = UsersBean.userInSession().getType();
		String companyName = UsersBean.userInSession().getCompanyName();
		usersHours = new ArrayList<>();
		Map<UserDto, Long> usersMinutes = flightService.reportWithAllFlightHoursOfAllCrewMembers(startDate, endDate, userType, companyName);
		for (Entry<UserDto, Long> entry : usersMinutes.entrySet()) {
			usersHours.add(entry);
		}
	}

}

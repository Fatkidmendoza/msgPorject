package edu.msg.flightmanager.web.beans;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import edu.msg.flightmanager.backend.dto.CompanyDto;
import edu.msg.flightmanager.backend.dto.FlightTemplateDto;
import edu.msg.flightmanager.backend.dto.UserDto;
import edu.msg.flightmanager.backend.service.CompanyService;
import edu.msg.flightmanager.backend.service.FlightService;
import edu.msg.flightmanager.backend.service.FlightTemplateService;
import edu.msg.flightmanager.backend.service.ServiceException;
import edu.msg.flightmanager.web.util.Constants;

@ManagedBean
@ViewScoped
public class FlightTemplatesBean implements Serializable {

	private static final long serialVersionUID = 9048236267728877625L;

	@EJB
	private FlightTemplateService flightTemplateService;

	@EJB
	private FlightService flightService;

	private FlightTemplateDto flightTemplateToInsert = new FlightTemplateDto();
	private FlightTemplateDto flightTemplateToUpdate = new FlightTemplateDto();

	private List<FlightTemplateDto> filteredFlightTemplates;

	private Date currentDate = new Date();
	@EJB
	private CompanyService companyService;
	private String companyName = "";
	private CompanyDto companyByName = new CompanyDto();

	@PostConstruct
	public void initCompanyForFlightManager() {
		if (UsersBean.userInSession().getType().equals(Constants.FLIGHT_MANAGER)) {
			companyByName = companyService.getByName(UsersBean.userInSession().getCompanyName());
		}
	}

	public Date getCurrentDate() {

		return currentDate;
	}

	public List<FlightTemplateDto> getAllFlightTemplates() {
		return flightTemplateService.getAll();
	}

	public List<FlightTemplateDto> getFilteredFlightTemplates() {
		return filteredFlightTemplates;
	}

	public void setFilteredFlightTemplates(List<FlightTemplateDto> filteredFlightTemplates) {
		this.filteredFlightTemplates = filteredFlightTemplates;
	}

	public void deleteFlightTemplate(FlightTemplateDto flightTemplateDto) {
		try {
			flightTemplateService.delete(flightTemplateDto);
			FacesMessage errorMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Delete Successful.",
					"The flight template was deleted.");
			FacesContext.getCurrentInstance().addMessage(null, errorMessage);
		} catch (ServiceException e) {
			FacesMessage errorMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Delete Failed.",
					"The flight template was not deleted.");
			FacesContext.getCurrentInstance().addMessage(null, errorMessage);
		}
	}

	public void updateFlightTemplate() {
		try {
			flightTemplateService.update(flightTemplateToUpdate);
			FacesMessage errorMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Update Successful.",
					"The flight template was updated.");
			FacesContext.getCurrentInstance().addMessage(null, errorMessage);
		} catch (ServiceException e) {
			FacesMessage errorMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Update Failed.",
					"The flight template was not updated.");
			FacesContext.getCurrentInstance().addMessage(null, errorMessage);
		}
	}

	public String addFlightTemplate() {
		try {
			setCompany();
			if (flightTemplateToInsert.getAvailableFrom() == null) {
				FacesMessage errorMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Insert Failed.",
						"A date has to be selected.");
				FacesContext.getCurrentInstance().addMessage(null, errorMessage);
				return "addFlightTemplate";
			}
			flightTemplateToInsert = flightTemplateService.insert(flightTemplateToInsert);
			flightService.insertNextFlights(flightTemplateToInsert, flightTemplateToInsert.getAvailableFrom());
			flightTemplateToInsert = new FlightTemplateDto();
			FacesMessage errorMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Insert Successful.",
					"The flight template was inserted.");
			FacesContext.getCurrentInstance().addMessage(null, errorMessage);
		} catch (ServiceException e) {
			FacesMessage errorMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Insert Failed.",
					"The flight template was not inserted.");
			FacesContext.getCurrentInstance().addMessage(null, errorMessage);
			return "addFlightTemplate";
		}
		return "addFlightTemplate";
	}

	//
	public void setCompany() {
		UserDto userInSession = UsersBean.userInSession();
		if (userInSession.getType().equals(Constants.FLIGHT_MANAGER)) {
			companyByName = companyService.getByName(userInSession.getCompanyName());
		} else if (userInSession.getType().equals(Constants.ADMIN)) {
			companyByName = companyService.getByName(companyName);
		}
		flightTemplateToInsert.setCompany(companyByName);
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public CompanyDto getCompanyByName() {
		return companyByName;
	}

	public void setCompanyByName(CompanyDto companyByName) {
		this.companyByName = companyByName;
	}
	//

	public FlightTemplateDto getFlightTemplateToInsert() {
		return flightTemplateToInsert;
	}

	public void setFlightTemplateToInsert(FlightTemplateDto flightTemplateToInsert) {
		this.flightTemplateToInsert = flightTemplateToInsert;
	}

	public FlightTemplateDto getFlightTemplateToUpdate() {
		return flightTemplateToUpdate;
	}

	public void setFlightTemplateToUpdate(FlightTemplateDto flightTemplateToUpdate) {
		this.flightTemplateToUpdate = flightTemplateToUpdate;
	}

	public Long computeDuration(FlightTemplateDto currentFlightTemplate) {
		Calendar itineraryCalendar = Calendar.getInstance();

		Calendar startTimeCalendar = Calendar.getInstance();
		startTimeCalendar.setTime(currentFlightTemplate.getAvailableFrom());

		itineraryCalendar.setTime(currentFlightTemplate.getItinerary().getStartTime());
		startTimeCalendar.set(Calendar.HOUR_OF_DAY, itineraryCalendar.get(Calendar.HOUR_OF_DAY));
		startTimeCalendar.set(Calendar.MINUTE, itineraryCalendar.get(Calendar.MINUTE));
		startTimeCalendar.setTimeZone(TimeZone.getTimeZone(currentFlightTemplate.getDeparture().getTimezone()));
		startTimeCalendar.getTime(); // returns a date that is converted to the
		// default time zone
		Calendar endTimeCalendar = Calendar.getInstance();
		endTimeCalendar.setTime(currentFlightTemplate.getAvailableFrom());
		if (currentFlightTemplate.getItinerary().isNextDay()) {
			endTimeCalendar.add(Calendar.DAY_OF_YEAR, 1);
		}

		itineraryCalendar.setTime(currentFlightTemplate.getItinerary().getEndTime());
		endTimeCalendar.set(Calendar.HOUR_OF_DAY, itineraryCalendar.get(Calendar.HOUR_OF_DAY));
		endTimeCalendar.set(Calendar.MINUTE, itineraryCalendar.get(Calendar.MINUTE));
		endTimeCalendar.setTimeZone(TimeZone.getTimeZone(currentFlightTemplate.getArrival().getTimezone()));
		endTimeCalendar.getTime(); // returns a date that is converted to the
		// default time zone
		Long duration = endTimeCalendar.getTime().getTime() - startTimeCalendar.getTime().getTime();
		Long durationInMinutes = TimeUnit.MILLISECONDS.toMinutes(duration);
		return durationInMinutes;
	}
}

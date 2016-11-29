package edu.msg.flightmanager.web.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import edu.msg.flightmanager.backend.dto.ItineraryDto;
import edu.msg.flightmanager.backend.service.ItineraryService;
import edu.msg.flightmanager.backend.service.ServiceException;

@ManagedBean
@ViewScoped
public class ItinerariesBean implements Serializable {

	private static final long serialVersionUID = -2908422761939182720L;

	@EJB
	private ItineraryService itineraryService;
	private Integer hours;
	private Integer minutes;
	private ItineraryDto itineraryToInsert;
	private ItineraryDto itineraryToUpdate;

	private List<ItineraryDto> filteredItineraries;

	@PostConstruct
	private void initValues() {
		hours = 0;
		minutes = 0;
		itineraryToInsert = new ItineraryDto();

	}

	public List<ItineraryDto> getAllItineraries() {
		try{
			List<ItineraryDto> itineraries = itineraryService.getAll();
			Collections.sort(itineraries);
			return itineraries;
		} catch (ServiceException e) {
			FacesMessage errorMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
					"The itineraries list could not be retrieved.");
			FacesContext.getCurrentInstance().addMessage(null, errorMessage);
			return new ArrayList<>();
		}
	}

	public List<ItineraryDto> getFilteredItineraries() {
		return filteredItineraries;
	}

	public void setFilteredItineraries(List<ItineraryDto> filteredItineraries) {
		this.filteredItineraries = filteredItineraries;
	}

	public String addItinerary() {
		if(itineraryToInsert.getPeriodicityName() == null || itineraryToInsert.getPeriodicityName().equals("")) {
			FacesMessage errorMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "No periodicity was selected.",
					"Please select a periodicity.");
			FacesContext.getCurrentInstance().addMessage(null, errorMessage);
			return "addItinerary";
		}
		try {
			itineraryService.insert(itineraryToInsert);
			initValues();
			FacesMessage errorMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Insert Successful.",
					"The itinerary was inserted.");
			FacesContext.getCurrentInstance().addMessage(null, errorMessage);
		} catch (ServiceException e) {
			FacesMessage errorMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Insert Failed.",
					"The itinerary was not inserted.");
			FacesContext.getCurrentInstance().addMessage(null, errorMessage);
			return "addItinerary";
		}
		return "addItinerary";
	}

	public List<String> getPeriodicityTypes() {
		return itineraryService.getPeriodicityTypes();
	}

	public ItineraryDto getItineraryToInsert() {
		return itineraryToInsert;
	}

	public void setItineraryToInsert(ItineraryDto itineraryToInsert) {
		this.itineraryToInsert = itineraryToInsert;
	}

	public Integer getHours() {
		return hours;
	}

	public void setHours(Integer hours) {
		this.hours = hours;
	}

	public Integer getMinutes() {
		return minutes;
	}

	public void setMinutes(Integer minutes) {
		this.minutes = minutes;
	}

	public ItineraryDto getItineraryToUpdate() {
		return itineraryToUpdate;
	}

	public void setItineraryToUpdate(ItineraryDto itineraryToUpdate) {
		this.itineraryToUpdate = itineraryToUpdate;
	}

	public void updateItinerary() {
		try {
			itineraryToUpdate = itineraryService.update(itineraryToUpdate);
			FacesMessage errorMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Update Successful.",
					"The itinerary was updated.");
			FacesContext.getCurrentInstance().addMessage(null, errorMessage);
		} catch (ServiceException e) {
			FacesMessage errorMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Update Failed.",
					"The itinerary was not updated.");
			FacesContext.getCurrentInstance().addMessage(null, errorMessage);
		}
	}

	public void deleteItinerary(ItineraryDto itineraryDto) {
		try {
			itineraryService.delete(itineraryDto);
			FacesMessage errorMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Delete Successful.",
					"The itinerary was deleted.");
			FacesContext.getCurrentInstance().addMessage(null, errorMessage);
		} catch (ServiceException e) {
			FacesMessage errorMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Delete Failed.",
					"The itinerary was not deleted.");
			FacesContext.getCurrentInstance().addMessage(null, errorMessage);
		}
	}

}

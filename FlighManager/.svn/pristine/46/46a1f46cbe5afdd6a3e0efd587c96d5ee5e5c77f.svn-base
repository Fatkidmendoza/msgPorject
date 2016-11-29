package edu.msg.flightmanager.web.beans;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import edu.msg.flightmanager.backend.dto.FlightDto;
import edu.msg.flightmanager.backend.service.FlightService;

@ManagedBean
@RequestScoped
public class TimerBean implements Serializable{


	private static final long serialVersionUID = 1L;
	@EJB
	private FlightService flightService;
	private FlightDto flightToView;


	public FlightDto getFlightToView() {
		return flightToView;
	}


	public void setFlightToView(FlightDto flightToView) {
		this.flightToView = flightToView;
	}


	public List<FlightDto> getFlightsHistory(){
		return flightService.getHistory();
	}
}
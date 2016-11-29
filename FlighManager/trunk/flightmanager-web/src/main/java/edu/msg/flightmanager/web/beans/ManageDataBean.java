package edu.msg.flightmanager.web.beans;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import edu.msg.flightmanager.backend.dto.CompanyDto;
import edu.msg.flightmanager.backend.service.FlightService;
import edu.msg.flightmanager.backend.service.FlightTemplateService;
import edu.msg.flightmanager.backend.service.ItineraryService;
import edu.msg.flightmanager.backend.service.PlaneService;
import edu.msg.flightmanager.backend.service.ServiceException;

@ManagedBean
@RequestScoped
public class ManageDataBean implements Serializable {

	private static final long serialVersionUID = 7239022477112382516L;

	@EJB
	private FlightService flightService;

	private Date startDate;
	private Date endDate;

	private StreamedContent file;

	private StreamedContent planeFile;
	private CompanyDto companyDto = new CompanyDto();
	@EJB
	private PlaneService planeService;


	private StreamedContent flightTemplateFile;
	@EJB
	private FlightTemplateService flightTemplateService;

	private StreamedContent itineraryFile;
	@EJB
	private ItineraryService itineraryService;

	public StreamedContent getFile() {
		if(startDate.after(endDate)) {
			// throw exception
		}
		try {
			File flights = flightService.getCsvFileWithFlights(startDate, endDate);
			try {
				file = new DefaultStreamedContent(new FileInputStream(flights), "csv", "flights.csv");
				FacesMessage errorMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Download successful",
						"The file " + file.getName() + " was downloaded.");
				FacesContext.getCurrentInstance().addMessage(null, errorMessage);
			} catch (FileNotFoundException e) {
				FacesMessage errorMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Download unsuccessful",
						"The file " + file.getName() + " was not downloaded.");
				FacesContext.getCurrentInstance().addMessage(null, errorMessage);
			}
		} catch (ServiceException e) {
			FacesMessage errorMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Download unsuccessful",
					"The file could not be created.");
			FacesContext.getCurrentInstance().addMessage(null, errorMessage);
		}
		return file;
	}

	public StreamedContent getPlaneFile() {
		try {
			File planes = planeService.getCsvFileWithPlanes(companyDto.getName());
			try {
				planeFile = new DefaultStreamedContent(new FileInputStream(planes), "csv", "planes.csv");
				FacesMessage errorMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Download successful",
						"The file " + planeFile.getName() + " was downloaded.");
				FacesContext.getCurrentInstance().addMessage(null, errorMessage);
			} catch (FileNotFoundException e) {
				FacesMessage errorMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Download unsuccessful",
						"The file " + planeFile.getName() + " was not downloaded.");
				FacesContext.getCurrentInstance().addMessage(null, errorMessage);
			}
		} catch (ServiceException e) {
			FacesMessage errorMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Download unsuccessful",
					"The file could not be created.");
			FacesContext.getCurrentInstance().addMessage(null, errorMessage);
		}
		return planeFile;
	}

	public StreamedContent getFlightTemplateFile() {
		try {
			File flightTemplates = flightTemplateService.getCsvFileWithFlightTemplates();
			try {
				flightTemplateFile = new DefaultStreamedContent(new FileInputStream(flightTemplates), "csv", "flightTemplates.csv");
				FacesMessage errorMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Download successful",
						"The file " + flightTemplateFile.getName() + " was downloaded.");
				FacesContext.getCurrentInstance().addMessage(null, errorMessage);
			} catch (FileNotFoundException e) {
				FacesMessage errorMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Download unsuccessful",
						"The file " + flightTemplateFile.getName() + " was not downloaded.");
				FacesContext.getCurrentInstance().addMessage(null, errorMessage);
			}
		} catch (ServiceException e) {
			FacesMessage errorMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Download unsuccessful",
					"The file could not be created.");
			FacesContext.getCurrentInstance().addMessage(null, errorMessage);
		}
		return flightTemplateFile;
	}

	public StreamedContent getItineraryFile() {
		try {
			File itineraries = itineraryService.getCsvFileWithItineraries();
			try {
				itineraryFile = new DefaultStreamedContent(new FileInputStream(itineraries), "csv", "itineraries.csv");
				FacesMessage errorMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Download successful",
						"The file " + itineraryFile.getName() + " was downloaded.");
				FacesContext.getCurrentInstance().addMessage(null, errorMessage);
			} catch (FileNotFoundException e) {
				FacesMessage errorMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Download unsuccessful",
						"The file " + itineraryFile.getName() + " was not downloaded.");
				FacesContext.getCurrentInstance().addMessage(null, errorMessage);
			}
		} catch (ServiceException e) {
			FacesMessage errorMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Download unsuccessful",
					"The file could not be created.");
			FacesContext.getCurrentInstance().addMessage(null, errorMessage);
		}
		return itineraryFile;
	}

	public CompanyDto getCompanyDto() {
		return companyDto;
	}

	public void setCompanyDto(CompanyDto companyDto) {
		this.companyDto = companyDto;
	}

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

	// for flight data import
	public void handleFileUpload(FileUploadEvent event) {
		UploadedFile uploadedFile = event.getFile();
		try {
			InputStream inputStream = uploadedFile.getInputstream();
			Map<String, Integer> result = flightService.readFromCsvFile(inputStream);
			FacesMessage numberOfSuccessfulInserts = new FacesMessage(FacesMessage.SEVERITY_INFO, "Insert from file " + uploadedFile.getFileName() + " information",
					result.get("successful") + " flights were inserted.\n" + result.get("unsuccessful") + " flights could not be inserted.");
			FacesContext.getCurrentInstance().addMessage(null, numberOfSuccessfulInserts);
		} catch (IOException e) {
			FacesMessage errorMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "File upload unsuccessful",
					"The file could not be uploaded.");
			FacesContext.getCurrentInstance().addMessage(null, errorMessage);
		}
	}

	// for plane data import
	public void handlePlaneFileUpload(FileUploadEvent event) {
		UploadedFile uploadedFile = event.getFile();
		try {
			InputStream inputStream = uploadedFile.getInputstream();
			Map<String, Integer> result = planeService.readFromCsvFile(inputStream);
			FacesMessage numberOfSuccessfulInserts = new FacesMessage(FacesMessage.SEVERITY_INFO, "Insert from file " + uploadedFile.getFileName() + " information",
					result.get("successful") + " planes were inserted.\n" + result.get("unsuccessful") + " planes could not be inserted.");
			FacesContext.getCurrentInstance().addMessage(null, numberOfSuccessfulInserts);
		} catch (IOException e) {
			FacesMessage errorMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "File upload unsuccessful",
					"The file could not be uploaded.");
			FacesContext.getCurrentInstance().addMessage(null, errorMessage);
		}
	}

	// for flight template data import
	public void handleFlightTemplateFileUpload(FileUploadEvent event) {
		UploadedFile uploadedFile = event.getFile();
		try {
			InputStream inputStream = uploadedFile.getInputstream();
			Map<String, Integer> result = flightTemplateService.readFromCsvFile(inputStream);
			FacesMessage numberOfSuccessfulInserts = new FacesMessage(FacesMessage.SEVERITY_INFO, "Insert from file " + uploadedFile.getFileName() + " information",
					result.get("successful") + " flight templates were inserted.\n" + result.get("unsuccessful") + " flight templates could not be inserted.");
			FacesContext.getCurrentInstance().addMessage(null, numberOfSuccessfulInserts);
		} catch (IOException e) {
			FacesMessage errorMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "File upload unsuccessful",
					"The file could not be uploaded.");
			FacesContext.getCurrentInstance().addMessage(null, errorMessage);
		}
	}

	// for itinerary data upload
	public void handleItineraryFileUpload(FileUploadEvent event) {
		UploadedFile uploadedFile = event.getFile();
		try {
			InputStream inputStream = uploadedFile.getInputstream();
			Map<String, Integer> result = itineraryService.readFromCsvFile(inputStream);
			FacesMessage numberOfSuccessfulInserts = new FacesMessage(FacesMessage.SEVERITY_INFO, "Insert from file " + uploadedFile.getFileName() + " information",
					result.get("successful") + " itineraries were inserted.\n" + result.get("unsuccessful") + " itineraries could not be inserted.");
			FacesContext.getCurrentInstance().addMessage(null, numberOfSuccessfulInserts);
		} catch (IOException e) {
			FacesMessage errorMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "File upload unsuccessful",
					"The file could not be uploaded.");
			FacesContext.getCurrentInstance().addMessage(null, errorMessage);
		}
	}
}

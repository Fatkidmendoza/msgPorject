package edu.msg.flightmanager.web.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import edu.msg.flightmanager.backend.dto.AirportDto;
import edu.msg.flightmanager.backend.dto.CompanyDto;
import edu.msg.flightmanager.backend.dto.PlaneDto;
import edu.msg.flightmanager.backend.dto.UserDto;
import edu.msg.flightmanager.backend.service.CompanyService;
import edu.msg.flightmanager.backend.service.PlaneService;
import edu.msg.flightmanager.backend.service.ServiceException;
import edu.msg.flightmanager.backend.service.UserService;

@ManagedBean
@SessionScoped
public class CompaniesBean implements Serializable {

	private static final long serialVersionUID = -2644003918788450237L;

	@EJB
	private CompanyService companyService;

	//used for get all in order to load the planes, airports and users
	private CompanyDto selectedCompany;

	//user for insert
	private CompanyDto companyToInsert = new CompanyDto();
	private List<AirportDto> selectedAirports;

	//used for update
	private CompanyDto companyToUpdate;

	private List<CompanyDto> filteredCompanies;

	@EJB
	private PlaneService planeService;
	@EJB
	private UserService userService;

	public List<CompanyDto> getAllCompanies() {
		try {
			List<CompanyDto> companies =  companyService.getAll();
			for(CompanyDto company : companies) {
				String companyName = company.getName();
				List<PlaneDto> planes = planeService.getByCompany(companyName);
				List<UserDto> users = userService.getByCompany(companyName);
				company.setEmployees(users);
				company.setPlanes(planes);
			}
			return companies;
		} catch (ServiceException e) {
			FacesMessage errorMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error.",
					"The companies list could not be retrieved.");
			FacesContext.getCurrentInstance().addMessage(null, errorMessage);
			return new ArrayList<>();
		}
	}

	public List<CompanyDto> getFilteredCompanies() {
		return filteredCompanies;
	}

	public void setFilteredCompanies(List<CompanyDto> filteredCompanies) {
		this.filteredCompanies = filteredCompanies;
	}

	public void deleteCompany(CompanyDto companyDto) {
		try {
			companyService.delete(companyDto);
			FacesMessage errorMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Delete Successful.",
					"The company was deleted.");
			FacesContext.getCurrentInstance().addMessage(null, errorMessage);
		} catch (ServiceException e) {
			FacesMessage errorMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Delete Failed.",
					"The company was not deleted.");
			FacesContext.getCurrentInstance().addMessage(null, errorMessage);
		}
	}

	public List<PlaneDto> getSelectedCompanyPlanes() {
		if(selectedCompany != null) {
			return selectedCompany.getPlanes();
		} else {
			return new ArrayList<>();
		}
	}

	public List<AirportDto> getSelectedCompanyAirports() {
		if(selectedCompany != null) {
			return selectedCompany.getAirports();
		} else {
			return new ArrayList<>();
		}
	}

	public List<UserDto> getSelectedCompanyUsers() {
		if(selectedCompany != null) {
			return selectedCompany.getEmployees();
		} else {
			return new ArrayList<>();
		}
	}

	public void selectAction(CompanyDto company) {
		selectedCompany = company;
	}

	public String addCompany() {
		companyToInsert.setEmployees(new ArrayList<>());
		companyToInsert.setPlanes(new ArrayList<>());
		companyToInsert.setAirports(selectedAirports);
		try {
			companyService.insert(companyToInsert);
			companyToInsert = new CompanyDto();
			setSelectedAirports(new ArrayList<>());
			FacesMessage errorMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Insert Successful.",
					"The company was inserted.");
			FacesContext.getCurrentInstance().addMessage(null, errorMessage);
		} catch (ServiceException e){
			FacesMessage errorMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Insert Failed.",
					"The company was not inserted.");
			FacesContext.getCurrentInstance().addMessage(null, errorMessage);
			return "addCompany";
		}
		return "addCompany";
	}

	public CompanyDto getCompanyToInsert() {
		return companyToInsert;
	}

	public void setCompanyToInsert(CompanyDto companyToInsert) {
		this.companyToInsert = companyToInsert;
	}

	public List<AirportDto> getSelectedAirports() {
		return selectedAirports;
	}

	public void setSelectedAirports(List<AirportDto> selectedAirports) {
		this.selectedAirports = selectedAirports;
	}

	public void updateCompany() {
		try {
			companyService.update(companyToUpdate);
			selectedCompany = new CompanyDto();
			FacesMessage errorMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Update Successful.",
					"The company was updated.");
			FacesContext.getCurrentInstance().addMessage(null, errorMessage);
		} catch(ServiceException e) {
			FacesMessage errorMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Update Failed.",
					"The company was not updated.");
			FacesContext.getCurrentInstance().addMessage(null, errorMessage);
		}
	}

	public CompanyDto getCompanyToUpdate() {
		return companyToUpdate;
	}

	public void setCompanyToUpdate(CompanyDto companyToUpdate) {
		this.companyToUpdate = companyToUpdate;
	}

	public List<CompanyDto> getCompanyToUpdateAsList() {
		List<CompanyDto> companyToUpdateAsList = new ArrayList<>();
		companyToUpdateAsList.add(companyToUpdate);
		return companyToUpdateAsList;
	}

}

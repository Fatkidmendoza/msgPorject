package edu.msg.flightmanager.web.beans;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import edu.msg.flightmanager.backend.dto.UserDto;
import edu.msg.flightmanager.backend.service.ServiceException;
import edu.msg.flightmanager.backend.service.UserService;

@ManagedBean
@ViewScoped
public class ChangePasswordBean {

	@EJB
	private UserService userService;

	private UserDto userForChangePassword;

	private String newPassword;

	public String changePassword() {
		try {
			userForChangePassword.setPassword(newPassword);
			userService.changePassword(userForChangePassword);
			FacesMessage errorMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Password changed.",
					"");
			FacesContext.getCurrentInstance().addMessage(null, errorMessage);
			return "changePasswordSuccess";
		} catch (ServiceException e) {
			FacesMessage errorMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Retype password.",
					"");
			FacesContext.getCurrentInstance().addMessage(null, errorMessage);
			return "changePasswordFailed";

		}
	}

	public UserDto getUserForChangePassword() {
		if (userForChangePassword == null) {
			HttpServletRequest request = (HttpServletRequest) (FacesContext.getCurrentInstance().getExternalContext()
					.getRequest());
			userForChangePassword = (UserDto) request.getAttribute("userForChangePassword");
		}
		return userForChangePassword;
	}

	public void setUserForChangePassword(UserDto userForChangePassword) {
		this.userForChangePassword = userForChangePassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

}

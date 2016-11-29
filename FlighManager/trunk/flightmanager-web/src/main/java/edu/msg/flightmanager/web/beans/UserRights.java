package edu.msg.flightmanager.web.beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import edu.msg.flightmanager.backend.dto.UserDto;
import edu.msg.flightmanager.web.util.Constants;

@ManagedBean
@SessionScoped
public class UserRights implements Serializable {

	private static final long serialVersionUID = -6490518784833078319L;

	private UserDto user = UsersBean.userInSession();

	public boolean isFlightManAdminRight() {
		if (user.getType().equals(Constants.FLIGHT_MANAGER) || user.getType().equals(Constants.ADMIN)) {
			return true;
		}
		return false;
	}

	public boolean isCompManAdminRight() {
		if (user.getType().equals(Constants.COMPANY_MANAGER) || user.getType().equals(Constants.ADMIN)) {
			return true;
		}
		return false;
	}

	public boolean isAdminRight() {
		if (user.getType().equals(Constants.ADMIN)) {
			return true;
		}
		return false;
	}

	public boolean isFlightHistory() {
		if (user.getType().equals(Constants.COMPANY_MANAGER) || user.getType().equals(Constants.ADMIN)
				|| user.getType().equals(Constants.FLIGHT_MANAGER)) {
			return true;
		}
		return false;
	}

}

/**
 * 
 */
package jsf01;

/**
 * @author fodorm
 *
 */
import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import jsf01.entities.User;
import jsf01.facades.LoginFacade;

@ManagedBean
@RequestScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = -2617767540112561117L;

	private User user = new User();

	@EJB
	private LoginFacade loginFacade;

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	public String processLogin() {
		if (loginFacade.isValidUser(user)) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("We logged in, yey"));
			return "loginSuccess";
		} else {
			FacesContext.getCurrentInstance().addMessage("loginForm:username",
					new FacesMessage("Password or Username wrong!"));
			return "loginFailed";
		}
	}
}
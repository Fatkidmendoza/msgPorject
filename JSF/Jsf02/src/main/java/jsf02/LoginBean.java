/**
 * 
 */
package jsf02;

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
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

import jsf02.entities.User;
import jsf02.facades.LoginFacade;

@ManagedBean
@RequestScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;

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

	public void loginActionListener(ActionEvent event) {
		System.err.println("something something event from "
				+ event.getComponent().getClientId());
	}

	public String processLogin() {
		if (loginFacade.isValidUser(user)) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("We logged in, yey"));
			HttpSession session = (HttpSession) FacesContext
					.getCurrentInstance().getExternalContext()
					.getSession(false);
			session.setAttribute("username", user.getUserName());
			return "loginSuccess";
		} else {
			FacesContext.getCurrentInstance().addMessage("loginForm:username",
					new FacesMessage("Password or Username wrong!"));
			return "loginFailed";
		}
	}

	public String processLogout() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
		session.invalidate();
		return "login";
	}
}
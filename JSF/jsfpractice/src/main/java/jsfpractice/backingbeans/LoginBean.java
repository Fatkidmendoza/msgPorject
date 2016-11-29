/**
 * 
 */
package jsfpractice.backingbeans;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import jsfpractice.entities.User;
import jsfpractice.facades.LoginFacade;

/**
 * Awesome Class.
 * 
 * @author fodorm
 *
 */
@ManagedBean
@RequestScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = -5640839936548437205L;
	
	private User user = new User();
	
	@EJB
	private LoginFacade loginFacade;
	
	public String validateLogin() {
		if (loginFacade.isValidUser(user)) {
			return handleLoginSuccess();
			
		}
		else {
			return handleLoginFailed();
		}
	}

	private String handleLoginFailed() {
		FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "SUmmary of err", "Detail of ok"));
		return "login";
	}

	private String handleLoginSuccess() {
		FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_INFO, "SUmmaryOK", "Detail of ok"));
		HttpSession session = getSession();
		session.setAttribute("username", user.getUserName());
		return "users";
	}
	
	public String logout() {
		HttpSession session = getSession();
		session.invalidate();
		return "login";
	}

	private HttpSession getSession() {
		HttpSession session = (HttpSession) FacesContext
				.getCurrentInstance().getExternalContext()
				.getSession(false);
		return session;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}

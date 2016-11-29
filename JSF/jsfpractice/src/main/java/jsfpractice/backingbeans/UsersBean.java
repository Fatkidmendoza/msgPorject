/**
 * 
 */
package jsfpractice.backingbeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import jsfpractice.entities.User;
import jsfpractice.facades.UsersFacade;


/**
 * @author fodorm
 *
 */
@ManagedBean
@SessionScoped
public class UsersBean implements Serializable {

	private static final long serialVersionUID = 4860933911207569401L;
	
	private List<User> usersList ;
	
	private User selectedUser;

	@EJB
	private UsersFacade userFacade;
	
	@PostConstruct
	public void initBean() {
		usersList = userFacade.getRandomUsers(13);
	}
	
	public List<User> getAllUsers() {
		return usersList;
	}
	
	public String deleteUser(User user) {
		usersList.remove(user);
		return "users";
	}
	
	public String enableModifyUser(User user) {
		setSelectedUser(user);
		return "users";
	}
	
	public String modifyUser() {
		setSelectedUser(null);
		return "users";
	}
	
	public boolean getShouldShow() {
		return true;
	}
	
	public boolean getShouldShowEditUser() {
		return selectedUser != null;
	}

	public User getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(User selectedUser) {
		this.selectedUser = selectedUser;
	}
}

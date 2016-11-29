/**
 * 
 */
package jsf01;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import jsf01.entities.User;
import jsf01.facades.UsersFacade;

/**
 * @author fodorm
 *
 */
@ManagedBean
@RequestScoped
public class UsersBean implements Serializable {

	private static final long serialVersionUID = 4860933911207569401L;

	@EJB
	private UsersFacade userFacade;

	public List<User> getAllUsers() {
		return userFacade.getRandomUsers(13);
	}
}

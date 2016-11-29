/**
 * 
 */
package jsf02.facades;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import jsf02.daos.UserDataAccessObject;
import jsf02.entities.User;

/**
 * @author fodorm
 *
 */
@Stateless
public class LoginFacade {

	@EJB
	private UserDataAccessObject userDao;

	public boolean isValidUser(User user) {
		if (user == null) {
			return false;
		}

		User savedUser = userDao
				.getUser(user.getUserName(), user.getPassword());

		if (savedUser == null) {
			return false;
		}

		return savedUser.equals(user);
	}
}

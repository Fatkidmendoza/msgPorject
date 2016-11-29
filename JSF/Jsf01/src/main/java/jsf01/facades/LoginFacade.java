/**
 * 
 */
package jsf01.facades;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import jsf01.daos.UserDataAccessObject;
import jsf01.entities.User;

/**
 * @author fodorm
 *
 */
@Stateless
public class LoginFacade {

	@EJB
	private UserDataAccessObject userDao;

	public boolean isValidUser(User user) {

		User savedUser = userDao
				.getUser(user.getUserName(), user.getPassword());

		if (savedUser == null) {
			return false;
		}

		return savedUser.equals(user);
	}
}

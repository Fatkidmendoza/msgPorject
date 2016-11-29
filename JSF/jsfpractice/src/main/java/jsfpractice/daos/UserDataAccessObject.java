/**
 * 
 */
package jsfpractice.daos;

import javax.ejb.Stateless;
import jsfpractice.entities.User;

/**
 * @author fodorm
 *
 */
@Stateless
public class UserDataAccessObject {

	public User getUser(String userName, String password) {
		return new User("test", "test");
	}

}

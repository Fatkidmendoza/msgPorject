/**
 * 
 */
package jsf02.daos;

import javax.ejb.Stateless;

import jsf02.entities.User;

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

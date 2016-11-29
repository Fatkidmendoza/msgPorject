/**
 * 
 */
package jsf01.daos;

import javax.ejb.Stateless;

import jsf01.entities.User;

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

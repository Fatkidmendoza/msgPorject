/**
 * 
 */
package jsf01.facades;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import jsf01.entities.User;
import jsf01.util.Utility;

/**
 * @author fodorm
 *
 */
@Stateless
public class UsersFacade {

	public List<User> getRandomUsers(int numberOfUsers) {
		List<User> userList = new ArrayList<>();
		for (int i = 0; i < numberOfUsers; i++) {
			String randomUsername = "User" + Utility.INSTANCE.getRandomNumber();
			String randomPassword = "Password"
					+ Utility.INSTANCE.getRandomNumber();
			User user = new User(randomUsername, randomPassword);
			userList.add(user);
		}

		return userList;
	}
}

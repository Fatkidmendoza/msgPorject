/**
 * 
 */
package jsf.HelloBean;

/**
 * @author fodorm
 *
 */
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class HelloBean implements Serializable {

	private static final long serialVersionUID = -7828490240688231419L;

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// JSF Action, with navigation outcome
	public String sayHello() {
		// hardcoded navigation, it's a bad idea :)
		name = name + "i almost made a huge mistake";
		return "welcome";
		// return "welcomeWrongPage";
	}
}
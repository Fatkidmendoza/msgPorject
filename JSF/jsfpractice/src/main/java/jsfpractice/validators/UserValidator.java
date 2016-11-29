/**
 * 
 */
package jsfpractice.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * @author fodorm
 *
 */
@FacesValidator("userValidator")
public class UserValidator implements Validator  {

	@Override
	public void validate(FacesContext facesContext, UIComponent uiComponent, Object value) throws ValidatorException {
		String stringValue = value.toString();
		
		if (stringValue.length() < 3) {
			FacesMessage message = new FacesMessage("Username and/or wassword too short!");
			throw new ValidatorException(message);
		}
		
		if (uiComponent.getClientId().contains("userName")) {
			if (stringValue.length() > 10) {
				FacesMessage message = new FacesMessage("Username too long!");
				throw new ValidatorException(message);
			}
		} else {
			if (stringValue.length() > 15) {
				FacesMessage message = new FacesMessage("Password too long!");
				throw new ValidatorException(message);
			}
		}
		
	}

}

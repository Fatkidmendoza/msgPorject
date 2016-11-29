package edu.msg.flightmanager.web.util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("minuteToHourConverter")
public class MinuteToHourConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		return value;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		Long allMinutes = (Long) value;
		Long hours = allMinutes/60;
		Long minutes = allMinutes % 60;
		StringBuilder builder = new StringBuilder();
		builder.append(hours);
		builder.append(" h ");
		builder.append(minutes);
		builder.append(" min");
		return builder.toString();

	}

}

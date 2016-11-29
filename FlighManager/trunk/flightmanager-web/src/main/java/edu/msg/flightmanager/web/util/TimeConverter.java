package edu.msg.flightmanager.web.util;

import java.util.Calendar;
import java.util.Date;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("timeConvertor")
public class TimeConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		return value;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		Date date = (Date) value;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		StringBuilder builder = new StringBuilder();
		int numberOfHours = cal.get(Calendar.HOUR_OF_DAY);
		if (numberOfHours < 10) {
			builder.append("0");
		}
		builder.append(numberOfHours);
		builder.append(":");

		int numberOfMinutes = cal.get(Calendar.MINUTE);
		if (numberOfMinutes < 10) {
			builder.append("0");
		}
		builder.append(numberOfMinutes);
		return builder.toString();

	}

}

package br.com.aibetesda.converters;

import java.text.ParseException;
import java.util.Date;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.joda.time.DateTimeZone;
import org.joda.time.LocalTime;

import br.com.aibetesda.util.SimpleFormatterUtils;

public class ConverterLocalTime implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		try {
			Date date = SimpleFormatterUtils.getFormatterHoras().parse(arg2);
			LocalTime lt = new LocalTime(date, DateTimeZone.forID("America/Sao_Paulo"));
			
			return lt;
		} catch (ParseException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if(arg2 instanceof LocalTime)
		{
			String hour = ((LocalTime) arg2).toString("HH:mm");
			return hour;
		}
		return "";
	}

}

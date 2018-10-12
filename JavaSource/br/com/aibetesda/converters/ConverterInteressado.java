package br.com.aibetesda.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import br.com.aibetesda.controladores.ControladorInteressado;

public class ConverterInteressado implements Converter {

	public ConverterInteressado() {}

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String nomeInteressado) {
		return new ControladorInteressado().getByName(nomeInteressado);
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object interessado) {
		return interessado.toString();
	}

}

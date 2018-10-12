package br.com.aibetesda.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import br.com.aibetesda.controladores.ControladorPais;

public class ConverterPais implements Converter{

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String nome) {
		return new ControladorPais().getPaisNome(nome);
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object pais) {
		return pais.toString();
	}

}

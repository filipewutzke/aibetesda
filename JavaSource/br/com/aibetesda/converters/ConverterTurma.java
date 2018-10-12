package br.com.aibetesda.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import br.com.aibetesda.controladores.ControladorTurma;

public class ConverterTurma implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String turmaString) {
		String id = turmaString.subSequence(0, turmaString.indexOf("-")).toString();
		return new ControladorTurma().getById(new Long(id.trim()));
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object turma) {
		return turma.toString();
	}
}

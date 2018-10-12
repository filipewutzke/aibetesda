package br.com.aibetesda.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import br.com.aibetesda.controladores.ControladorAluno;

public class ConverterAluno implements Converter {

	public ConverterAluno() {}

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String nomeAluno) {
		return new ControladorAluno().getByName(nomeAluno);
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object aluno) {
		return aluno.toString();
	}

}

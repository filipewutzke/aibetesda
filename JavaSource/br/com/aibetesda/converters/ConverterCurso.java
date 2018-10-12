package br.com.aibetesda.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import br.com.aibetesda.controladores.ControladorCurso;

public class ConverterCurso implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String nomeCurso) {
		return new ControladorCurso().getCursoPorNome(nomeCurso);
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object curso) {
		return curso.toString();
	}

}

package br.com.aibetesda.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import br.com.aibetesda.controladores.ControladorBeneficioSocial;

public class ConverterBeneficioSocial implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String nomeBeneficio) {
		return new ControladorBeneficioSocial().getBeneficioPorNome(nomeBeneficio);
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object beneficio) {
		return beneficio.toString();
	}

}

package br.com.aibetesda.validation;

import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.util.regex.Pattern;

import org.brazilutils.br.cpfcnpj.Cpf;
import org.brazilutils.validation.ValidationException;

import br.com.aibetesda.annotations.CPF;
import br.com.aibetesda.annotations.Comprimento;
import br.com.aibetesda.annotations.Email;
import br.com.aibetesda.annotations.NaoNulo;
import br.com.aibetesda.annotations.NaoVazio;
import br.com.aibetesda.annotations.ValorDiferenteDe;
import br.com.aibetesda.annotations.Variacao;

public class DefaultValidator implements Validator{

	private static final String EMAIL_STRING = 
		"^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$";
	private static final Pattern REGEX_EMAIL = Pattern.compile(EMAIL_STRING); 
	
	public StatusValidacao validar(Object o) throws Exception
	{
		StatusValidacao status = new StatusValidacao();
		Class<?> clazz = o.getClass();
		for(Field f : clazz.getDeclaredFields()){
			if(!f.isAccessible())
				f.setAccessible(true);
			if(f.isAnnotationPresent(NaoVazio.class))
			{
				NaoVazio vv = f.getAnnotation(NaoVazio.class);
				String valor = (String) f.get(o);
				if(valor.isEmpty())
				{
					status.setOk(false);
					
					status.addMensagenValidacao(vv.mensagem());
				}
			}
			
			if(f.isAnnotationPresent(CPF.class))
			{
				String cpf = (String) f.get(o);
				if(!isCpfValido(cpf))
				{
					status.setOk(false);
					
					CPF cpfAnn = f.getAnnotation(CPF.class);
					status.addMensagenValidacao(cpfAnn.mensagem());
				}
			}
			
			if(f.isAnnotationPresent(NaoNulo.class))
			{
				NaoNulo vv = f.getAnnotation(NaoNulo.class);
				Object valor = f.get(o);
				if(valor == null)
				{
					status.setOk(false);
					
					status.addMensagenValidacao(vv.mensagem());
				}
			}
			
			if(f.isAnnotationPresent(Variacao.class))
			{
				Variacao v = f.getAnnotation(Variacao.class);
				Number valor = (Number) f.get(o);
				if(valor.floatValue() < v.min() || valor.floatValue() > v.max())
				{
					status.setOk(false);
					status.addMensagenValidacao(v.mensagem());
				}
			}
			
			if(f.isAnnotationPresent(Comprimento.class))
			{
				Comprimento v = f.getAnnotation(Comprimento.class);
				String valor = (String) f.get(o);
				if(valor.length() < v.tamanhoMinimo())
				{
					status.setOk(false);
					status.addMensagenValidacao(v.mensagem());
				}
			}
			if(f.isAnnotationPresent(Email.class)){
				String valor = (String) f.get(o);
				Email v = f.getAnnotation(Email.class);
				if(valor == null || valor.isEmpty()){
					//Ok
				}
				else if(!REGEX_EMAIL.matcher(valor).matches()){
					//Não é um email valido
					status.setOk(false);
					status.addMensagenValidacao(v.mensagem());
				}
			}
			
			if(f.isAnnotationPresent(ValorDiferenteDe.class)){
				try{
					Number d = (Number) f.get(o);
					ValorDiferenteDe ann = f.getAnnotation(ValorDiferenteDe.class);
					if(d == null || d.intValue() == 0){
						status.setOk(false);
						new String();
						MessageFormat formatter = new MessageFormat(ann.mensagem());
						Object[] testArgs = {f.getName()};
						status.addMensagenValidacao(formatter.format(testArgs));
					}
				} catch (Exception e) {
					//
					System.err.println("Exception: "+e.getMessage());
				}
			}
		}
		return status;
	}
	
	public boolean isCpfValido(String obj){
		try {
			String cpfString = "";
			
			if(!("".equals(obj)) || !(obj == null)){
				cpfString = obj.toString().replace(".", "").replaceAll("-", "").replaceAll("_", "");
			}
			
			if("".equals(cpfString)){
				return false;
			}
			Cpf cpf = new Cpf(cpfString);
			return cpf.isValid();
		} catch (ValidationException e) {
			e.printStackTrace();
			return false;
		}
	}

}

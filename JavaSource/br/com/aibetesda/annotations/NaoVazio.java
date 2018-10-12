package br.com.aibetesda.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Verifica se uma {@link String} n�o est� vazia
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value={ElementType.FIELD})
public @interface NaoVazio {
	
	/**
	 * Mensagem para caso de erro da valida��o
	 */
	public String mensagem();
	
}

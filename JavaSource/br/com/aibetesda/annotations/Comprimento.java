package br.com.aibetesda.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Verifica se uma {@link String} tem o tamanho mínimo desejado
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Comprimento {
	public String mensagem();
	public int tamanhoMinimo();
}

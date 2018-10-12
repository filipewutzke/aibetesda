package br.com.aibetesda.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *	Verifica se algum valor (obrigatoriamente número - <code>extends {@link Number}</code>)
 *	tem o tamanho entre {@link #min()} e {@link #max()} 
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Variacao {
	public String mensagem();
	public int max();
	public int min() default 0;
}

package br.com.aibetesda.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(value={ElementType.FIELD})
public @interface ValorDiferenteDe {
	public double valor() default 0;
	public String mensagem() default "O valor de {0} deve ser diferente de 0";
}

package br.com.aibetesda.util;

import java.io.IOException;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

public abstract class FacesUtils {
	
	/**
	 * Dispatch não gera um histórico no browser, e serve para fazer a comunicação de Servet/Servlet <br />
	 * A URL não será alterada no browser
	 * @param url
	 */
	public static void dispatch(String url){
		try {
			ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
			ec.dispatch(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Redirect gera uma nova requisição, consome mais rede, mas gera históricos
	 * @param url
	 */
	public static void redirect(String url){
		try {
			ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
			ec.redirect(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getCaminhoAplicacao()
	{
		return getExternalContext().getRealPath("/");
	}
	
	public static ExternalContext getExternalContext(){
		return FacesContext.getCurrentInstance().getExternalContext();
	}
	
	public static HttpSession getSession(){
		return (HttpSession) getExternalContext().getSession(true);
	}
}

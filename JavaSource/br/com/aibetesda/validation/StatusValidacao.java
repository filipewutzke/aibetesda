package br.com.aibetesda.validation;

import java.util.ArrayList;
import java.util.List;

public class StatusValidacao {
	
public StatusValidacao() {}
	
	private boolean ok = true;
	private List<String> mensagens = new ArrayList<String>();
	
	public boolean isOk() {
		return ok;
	}
	public void setOk(boolean ok) {
		this.ok = ok;
	}
	public List<String> getMensagens() {
		return mensagens;
	}
	public void setMensagens(List<String> mensagens) {
		this.mensagens = mensagens;
	}
	
	public void addMensagenValidacao(String msg) {
		this.mensagens.add(msg);
	}
	
}

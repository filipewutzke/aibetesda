package br.com.aibetesda.manageds;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.event.CloseEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.aibetesda.modelos.Usuario;
import br.com.aibetesda.util.UserUtils;

@ManagedBean(name="login")
@SessionScoped
public class ManagedLogin {

	private static final Logger log = LoggerFactory.getLogger(ManagedLogin.class);
	
	private String logado;
	private String nomeUsuario;
	
	public ManagedLogin() {
		Usuario logado = UserUtils.getUsuarioLogado();
		log.info(logado.getLogin()+" acabou de se logar");
		setNomeUsuario(logado.getNome());
		setLogado(logado.getLogin());
	}
	
	public void ausenteClosed(CloseEvent close){
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(false);
        session.invalidate();
        try {
            externalContext.redirect("aibetesda/quit");
        } catch (IOException ioe) {
            // Redirect failed
        	System.out.println(ioe.getLocalizedMessage());
        }
	}
	
	public String getLogado() {
		return logado;
	}
	
	public void setLogado(String logado) {
		this.logado = logado;
	}
	
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	
	public String getNomeUsuario() {
		return nomeUsuario;
	}
}

package br.com.aibetesda.manageds;

import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

import br.com.aibetesda.validation.StatusValidacao;
import br.com.aibetesda.validation.DefaultValidator;

public abstract class ManagedCadastroConsulta {
	
private static Logger log = Logger.getLogger(ManagedCadastroConsulta.class);
	
	public abstract void novo();
	
	public abstract void salvar();
	
	public abstract void excluir();
	
	public abstract void selecionar();
	
	public abstract void loadAll();
	
	private boolean showSalvar = true;
	private boolean showNovo = true;
	private boolean showExcluir = true;
	private boolean showProcurar = true;
	private boolean ajaxStatus = true;
	
	
	public StatusValidacao validar(Object o) throws Exception
	{
		DefaultValidator v = new DefaultValidator();
		StatusValidacao status = v.validar(o);
		
		return status;
	}
	
	public boolean objetoValido(Object o) throws Exception
	{
		StatusValidacao sv = validar(o);
		if(!sv.isOk())
		{
			for(String s : sv.getMensagens())
				addMensagemErro(s);
		}
		
		return sv.isOk();
		
	}
	
	protected String encriptarString(String dec) {
		ShaPasswordEncoder md5 = new ShaPasswordEncoder(256);
		return md5.encodePassword(dec, null);
	}
	
	public boolean isEmail(String email) {
		Pattern p = Pattern.compile(".+@.+\\.[a-z]+");

		Matcher m = p.matcher(email);

		boolean matchFound = m.matches();

		StringTokenizer st = new StringTokenizer(email, ".");
		String lastToken = null;
		while (st.hasMoreTokens()) {
			lastToken = st.nextToken();
		}

		if (matchFound && lastToken.length() >= 2
				&& email.length() - 1 != lastToken.length()) {

			return true;
		} else
			return false;
	}

	
	protected void addMensagem(String mensagem) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem,  null);  
		FacesContext.getCurrentInstance().addMessage(null, message);
		log.info("Mensagem adicionada");
	}
	
	protected void addMensagemWarn(String mensagem) {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro ao executar operação",  mensagem);  
		
		context.addMessage(null, message);
		log.info("Mensagem de aviso adicionada");
	}
	
	protected void addMensagemWarn(String titulo, String mensagem) {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, titulo,  mensagem);  
		
		context.addMessage(null, message);
		log.info("Mensagem de aviso adicionada");
	}
	
	protected void addMensagem(String mensagem, String titulo) {
		FacesMessage message = new FacesMessage(titulo, mensagem);  
		FacesContext.getCurrentInstance().addMessage(null, message);
		log.info("Mensagem normal adicionada");
	}
	
	protected void addMensagemErro(String mensagem) {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao executar operação",  mensagem);  
		
		context.addMessage(null, message);
		log.info("Mensagem de erro adicionada");
	}
	
	public void salvarHotKey(ActionEvent event)
	{
		salvar();
		return;
	}
	
	protected void addCallbackParam(Object obj)
	{
		RequestContext context = RequestContext.getCurrentInstance();
		context.addCallbackParam("msg", obj);
	}
	
	protected void addCallbackParam(String key, Object obj)
	{
		RequestContext context = RequestContext.getCurrentInstance();
		context.addCallbackParam(key, obj);
	}
	
	protected void addMensagemErro(Exception e)
	{
		addMensagemErro(e.getMessage());
	}
	
	public void ativaBotoes() {
		setShowSalvar(true);
		setShowExcluir(true);
		setShowNovo(true);
		setShowProcurar(true);
	}
	
	public void desativaBotoes() {
		setShowSalvar(false);
		setShowExcluir(false);
		setShowNovo(false);
		setShowProcurar(false);
	}
	
	public boolean isShowSalvar() {
		return showSalvar;
	}
	
	public void setShowSalvar(boolean showSalvar) {
		this.showSalvar = showSalvar;
	}

	public boolean isShowNovo() {
		return showNovo;
	}

	public void setShowNovo(boolean showNovo) {
		this.showNovo = showNovo;
	}

	public boolean isShowExcluir() {
		return showExcluir;
	}

	public void setShowExcluir(boolean showExcluir) {
		this.showExcluir = showExcluir;
	}

	public boolean isShowProcurar() {
		return showProcurar;
	}

	public void setShowProcurar(boolean showBotoes) {
		this.showProcurar = showBotoes;
	}

	public boolean isAjaxStatus() {
		return ajaxStatus;
	}

	public void setAjaxStatus(boolean ajaxStatus) {
		this.ajaxStatus = ajaxStatus;
	}

	
}

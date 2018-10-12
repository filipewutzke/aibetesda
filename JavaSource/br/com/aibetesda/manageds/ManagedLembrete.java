package br.com.aibetesda.manageds;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.aibetesda.controladores.ControladorLembrete;
import br.com.aibetesda.modelos.Lembrete;
import br.com.aibetesda.util.UserUtils;

@ManagedBean(name="managedLembrete")
@SessionScoped
public class ManagedLembrete extends ManagedCadastroConsulta{
	
	private static final Logger log = LoggerFactory.getLogger(ManagedLembrete.class);
	
	private Lembrete lembrete = new Lembrete();
	private List<Lembrete> lembretes = new ArrayList<Lembrete>();
	private List<Lembrete> avisoDatas = new ArrayList<Lembrete>();
	private Lembrete lembreteSelecionado;
	
	private String idPage;
	
	public ManagedLembrete(){
		novo();
		loadAvisoDatasReunioes();
		setShowSalvar(false);
		setShowExcluir(false);
	}
	
	@Override
	public void novo() {
		lembrete = new Lembrete();
	}

	@Override
	public void salvar() {
		addMensagemWarn("Utilize a opção adicionar abaixo!");
		//médotodo que adiciona: adicionarDataLembrete()
	}

	@Override
	public void excluir() {
		addMensagemWarn("Utilize a opção remover abaixo!");
		//método que remove: removerDataLembrete()
	}

	@Override
	public void selecionar() {
		if(lembreteSelecionado != null && lembreteSelecionado.getId() != null)
			setLembrete(new ControladorLembrete().getById(lembreteSelecionado.getId()));
	}

	@Override
	public void loadAll() {
		novo();
		ControladorLembrete controlador = new ControladorLembrete();
		lembretes = controlador.loadAll();
		avisoDatas = controlador.getAvisoDatas();
	}
	
	public void adicionarDataLembrete(){
		try{
			if (!objetoValido(lembrete))
				return;
			
			if(lembrete.getDataReuniao() == null){
				addMensagemErro("Deve informar uma data!");
				return;
			}
			
			new ControladorLembrete().save(lembrete);
			avisoDatas.add(lembrete);
			addMensagem("Lembrete salvo com sucesso!");
			novo();
			log.info("Lembrete foi salvo!");
			
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void removerDataLembrete(){
		try{
			if(lembreteSelecionado == null){
				addMensagemErro("Deve selecionar uma data de reunião!");
				return;
			}
			avisoDatas.remove(lembreteSelecionado);
			addMensagem("Lembrete removido com sucesso!");
			log.info(UserUtils.getUsuarioLogado()+" excluiu um planejamento anual: "+lembrete);
			new ControladorLembrete().delete(lembreteSelecionado);
			lembreteSelecionado = new Lembrete();
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public String loadAvisoDatasId(){
		if(idPage != null && !("".equalsIgnoreCase(idPage))) {
			lembrete = new ControladorLembrete().getReuniaoID(Long.parseLong(idPage));
			setLembrete(lembrete);
		}
		return null;
	}
	
	private void loadAvisoDatasReunioes(){
		avisoDatas = new ControladorLembrete().getAvisoDatas();
	}

	
	public Lembrete getLembrete() {
		return lembrete;
	}

	public void setLembrete(Lembrete lembrete) {
		this.lembrete = lembrete;
	}

	public List<Lembrete> getLembretes() {
		return lembretes;
	}

	public void setLembretes(List<Lembrete> lembretes) {
		this.lembretes = lembretes;
	}

	public Lembrete getLembreteSelecionado() {
		return lembreteSelecionado;
	}

	public void setLembreteSelecionado(Lembrete lembreteSelecionado) {
		this.lembreteSelecionado = lembreteSelecionado;
	}

	public String getIdPage() {
		return idPage;
	}

	public void setIdPage(String idPage) {
		this.idPage = idPage;
	}

	public List<Lembrete> getAvisoDatas() {
		return avisoDatas;
	}

	public void setAvisoDatas(List<Lembrete> avisoDatas) {
		this.avisoDatas = avisoDatas;
	}
	

}

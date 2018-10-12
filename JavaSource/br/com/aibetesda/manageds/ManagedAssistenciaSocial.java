package br.com.aibetesda.manageds;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.aibetesda.controladores.ControladorAssistenciaSocial;
import br.com.aibetesda.modelos.AssistenciaSocial;
import br.com.aibetesda.util.UserUtils;

@ManagedBean(name="managedAssistenciaSocial")
@SessionScoped
public class ManagedAssistenciaSocial extends ManagedCadastroConsulta{
	
	private static final Logger log = LoggerFactory.getLogger(ManagedAssistenciaSocial.class);
	
	private AssistenciaSocial assistenciaSocial = new AssistenciaSocial();
	private List<AssistenciaSocial> assistenciaSociais = new ArrayList<AssistenciaSocial>();
	private AssistenciaSocial assistenciaSocialSelecionada;
	
	public ManagedAssistenciaSocial(){
		novo();
		loadAll();
	}
	
	@Override
	public void novo() {
		assistenciaSocial = new AssistenciaSocial();
	}

	@Override
	public void salvar() {
		try{
			if(!objetoValido(assistenciaSocial))
				return;
			
			assistenciaSocial.setDataAssistencia(new Date());
			
			ControladorAssistenciaSocial cc = new ControladorAssistenciaSocial();
			cc.save(assistenciaSocial);
			addMensagem("Assistencia Social salva com sucesso!");
			novo();
			loadAll();
			log.info("Assistencia Social salvo com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			addMensagemErro("Erro ao salvar assistencia social: "+e.getMessage());
			log.error("Assistencia Social não foi salvo: "+e.getMessage());
		}
	}

	@Override
	public void excluir() {
		try{
			if(assistenciaSocial.getId() == null){
				addMensagemErro("Assistencia Social vazio!");
				return;
			}
			ControladorAssistenciaSocial cc = new ControladorAssistenciaSocial();
			if(cc.delete(assistenciaSocial)){
				addMensagem("Assistencia Social excluido!");
				log.info(UserUtils.getUsuarioLogado()+" excluiu uma Assistencia Social: "+assistenciaSocial);
				novo();
				loadAll();
			}else{
				addMensagem("Assistencia Social não foi excluido! Verifique os dados");
			}
		} catch(Exception e){
			e.printStackTrace();
			addMensagem(e.getMessage(), "Erro ao excluir Assistencia Social!");
			log.error("Erro ao excluir Assistencia Social: "+e.getMessage());
		}
	}

	@Override
	public void selecionar() {
		if(assistenciaSocialSelecionada != null && assistenciaSocialSelecionada.getId() != null){
			this.assistenciaSocial = new ControladorAssistenciaSocial().getById(assistenciaSocialSelecionada.getId());
		}	
	}

	@Override
	public void loadAll() {
		this.assistenciaSociais = new ControladorAssistenciaSocial().loadAll();
	}

	public AssistenciaSocial getAssistenciaSocial() {
		return assistenciaSocial;
	}

	public void setAssistenciaSocial(AssistenciaSocial assistenciaSocial) {
		this.assistenciaSocial = assistenciaSocial;
	}

	public List<AssistenciaSocial> getAssistenciaSociais() {
		return assistenciaSociais;
	}

	public void setAssistenciaSociais(List<AssistenciaSocial> assistenciaSociais) {
		this.assistenciaSociais = assistenciaSociais;
	}

	public AssistenciaSocial getAssistenciaSocialSelecionada() {
		return assistenciaSocialSelecionada;
	}

	public void setAssistenciaSocialSelecionada(
			AssistenciaSocial assistenciaSocialSelecionada) {
		this.assistenciaSocialSelecionada = assistenciaSocialSelecionada;
	}
	
}

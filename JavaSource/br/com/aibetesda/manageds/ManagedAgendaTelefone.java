package br.com.aibetesda.manageds;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.aibetesda.controladores.ControladorAgendaTelefone;
import br.com.aibetesda.modelos.AgendaTelefone;
import br.com.aibetesda.util.UserUtils;

@ManagedBean(name="managedAgendaTelefone")
@SessionScoped
public class ManagedAgendaTelefone extends ManagedCadastroConsulta{
	
	private static final Logger log = LoggerFactory.getLogger(ManagedAgendaTelefone.class);
	
	private AgendaTelefone agendaTelefone;
	private AgendaTelefone agendaTelefoneConsulta;
	private List<AgendaTelefone> todaAgenda;
	
	public ManagedAgendaTelefone(){
		novo();
	}

	@Override
	public void novo() {
		this.agendaTelefone = new AgendaTelefone();
	}

	@Override
	public void salvar() {
		try{
			if(!objetoValido(agendaTelefone))
				return;
			
			ControladorAgendaTelefone controlador = new ControladorAgendaTelefone();
			controlador.save(agendaTelefone);
			addMensagem("Telefone cadastrado com sucesso na agenda");
			novo();
			loadAll();
			log.info("Telefone salvo na Agenda");
		} catch(Exception e){
			e.printStackTrace();
			addMensagem(e.getMessage(), "Erro ao salvar telefone na Agenda: "+e.getMessage());
			log.info("Erro ao salvar telefone na Agenda: "+e.getMessage());
		}
	}

	@Override
	public void excluir() {
		try{
			if(agendaTelefone.getId() == null){
				addMensagemErro("Agenda vazia!");
				return;
			}
			
			ControladorAgendaTelefone controlador = new ControladorAgendaTelefone();
			if(controlador.delete(agendaTelefone)){
				addMensagem("Telefone removido com sucesso da Agenda");
				log.info(UserUtils.getUsuarioLogado()+" excluiu uma telefone da Agenda: "+agendaTelefone);
				novo();
				loadAll();
			} else 
				addMensagem("Erro ao excluir telefone da agenda");
		} catch(Exception e){
			e.printStackTrace();
			addMensagem(e.getMessage(), "Erro ao excluir telefone da agenda");
			log.error("Erro ao excluir telefone: "+e.getMessage());
		}
	}

	@Override
	public void selecionar() {
		if(agendaTelefoneConsulta != null && agendaTelefoneConsulta.getId() != null)
			this.agendaTelefone = new ControladorAgendaTelefone().getById(agendaTelefoneConsulta.getId());
	}
	
	
	@Override
	public void loadAll() {
		this.todaAgenda = new ControladorAgendaTelefone().loadAll();
	}
	
	
	public AgendaTelefone getAgendaTelefone() {
		return agendaTelefone;
	}

	public void setAgendaTelefone(AgendaTelefone agendaTelefone) {
		this.agendaTelefone = agendaTelefone;
	}

	public AgendaTelefone getAgendaTelefoneConsulta() {
		return agendaTelefoneConsulta;
	}

	public void setAgendaTelefoneConsulta(AgendaTelefone agendaTelefoneConsulta) {
		this.agendaTelefoneConsulta = agendaTelefoneConsulta;
	}

	public List<AgendaTelefone> getTodaAgenda() {
		return todaAgenda;
	}

	public void setTodaAgenda(List<AgendaTelefone> todaAgenda) {
		this.todaAgenda = todaAgenda;
	}
	
}

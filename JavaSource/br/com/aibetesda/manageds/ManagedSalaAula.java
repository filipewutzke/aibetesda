package br.com.aibetesda.manageds;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.aibetesda.controladores.ControladorSalaAula;
import br.com.aibetesda.modelos.SalaAula;

@ManagedBean(name="managedSalaAula")
@SessionScoped
public class ManagedSalaAula extends ManagedCadastroConsulta {
	
	private static final Logger log = LoggerFactory.getLogger(ManagedCurso.class);
	
	private SalaAula salaAula = new SalaAula();
	private List<SalaAula> salasAula = new ArrayList<SalaAula>();
	private SalaAula salaAulaConsulta = new SalaAula();
	
	public ManagedSalaAula() {
		novo();
		loadAll();
	}
	
	@Override
	public void novo() {
		this.salaAula = new SalaAula();
	}

	@Override
	public void salvar() {
		try {
			if(!objetoValido(salaAula))
				return;
			
			new ControladorSalaAula().save(salaAula);
			addMensagem("Sala salva com sucesso");
			log.info("Sala salva com sucesso!");
			novo();
			loadAll();
		} catch (Exception e) {
			addMensagemErro("Erro ao salvar sala: "+e.getMessage());
			log.error("Sala não foi salvo: "+e.getMessage());
		}
	}

	@Override
	public void excluir() {
		try {
			new ControladorSalaAula().delete(salaAula);
			addMensagem("Sala excluida com sucessso");
			log.info("Sala excluida com sucesso!");
			novo();
			loadAll();
		} catch (Exception e) {
			addMensagemErro("Erro ao excluir sala: "+e.getMessage());
			log.error("Sala não foi excluida: "+e.getMessage());
		}
	}

	@Override
	public void selecionar() {
		if(salaAulaConsulta != null)
			setSalaAula(new ControladorSalaAula().getById(salaAulaConsulta.getId()));
	}
	
	public List<SalaAula> getSalaQuery(String query){
		return new ControladorSalaAula().getSalaQuery(query);
	}

	@Override
	public void loadAll() {
		this.salasAula = new ControladorSalaAula().loadAll();
	}
	
	
	public SalaAula getSalaAula() {
		return salaAula;
	}
	
	public void setSalaAula(SalaAula salaAula) {
		this.salaAula = salaAula;
	}
	
	public List<SalaAula> getSalasAula() {
		return salasAula;
	}
	
	public void setSalasAula(List<SalaAula> salasAula) {
		this.salasAula = salasAula;
	}
	
	public SalaAula getSalaAulaConsulta() {
		return salaAulaConsulta;
	}
	
	public void setSalaAulaConsulta(SalaAula salaAulaConsulta) {
		this.salaAulaConsulta = salaAulaConsulta;
	}
	

}

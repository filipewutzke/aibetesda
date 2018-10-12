package br.com.aibetesda.manageds;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.aibetesda.controladores.ControladorProfissao;
import br.com.aibetesda.modelos.Profissao;

@ManagedBean(name="managedProfissao")
@SessionScoped
public class ManagedProfissao extends ManagedCadastroConsulta {
	
	private static final Logger log = LoggerFactory.getLogger(ManagedProfissao.class);
	
	private Profissao profissao = new Profissao();
	private List<Profissao> profissoes = new ArrayList<Profissao>();
	private List<Profissao> profissoesOrdenada = new ArrayList<Profissao>();
	private Profissao profissaoConsulta = new Profissao();
	
	public ManagedProfissao() {
		novo();
		loadAll();
		loadProfissaoOrdenada();
	}
	
	@Override
	public void novo() {
		this.profissao = new Profissao();
	}

	@Override
	public void salvar() {
		try {
			if(!objetoValido(profissao))
				return;
			
			new ControladorProfissao().save(profissao);
			addMensagem("Profissão salva com sucesso");
			log.info("profissao salva com sucesso!");
			novo();
			loadAll();
		} catch (Exception e) {
			addMensagemErro("Erro ao salvar profissão: "+e.getMessage());
			log.error("profissao não foi salvo: "+e.getMessage());
		}
	}

	@Override
	public void excluir() {
		try {
			new ControladorProfissao().delete(profissao);
			addMensagem("Profissão excluida com sucessso");
			log.info("Profissao excluida com sucesso!");
			novo();
			loadAll();
		} catch (Exception e) {
			addMensagemErro("Erro ao excluir profissão: "+e.getMessage());
			log.error("Profissao não foi excluida: "+e.getMessage());
		}
	}

	@Override
	public void selecionar() {
		if(profissaoConsulta != null)
			setProfissao(new ControladorProfissao().getById(profissaoConsulta.getId()));
	}

	@Override
	public void loadAll() {
		this.profissoes = new ControladorProfissao().loadAll();
	}
	
	public void loadProfissaoOrdenada(){
		this.profissoesOrdenada = new ControladorProfissao().loadProfissaoOrdenada();
	}
	
	public List<Profissao> getProfissaoQuery(String query){
		return new ControladorProfissao().getProfissaoQuery(query);
	}
	
	
	public Profissao getProfissao() {
		return profissao;
	}

	public void setProfissao(Profissao profissao) {
		this.profissao = profissao;
	}

	public List<Profissao> getProfissoes() {
		return profissoes;
	}

	public void setProfissoes(List<Profissao> profissoes) {
		this.profissoes = profissoes;
	}

	public Profissao getProfissaoConsulta() {
		return profissaoConsulta;
	}

	public void setProfissaoConsulta(Profissao profissaoConsulta) {
		this.profissaoConsulta = profissaoConsulta;
	}

	public List<Profissao> getProfissoesOrdenada() {
		return profissoesOrdenada;
	}

	public void setProfissoesOrdenada(List<Profissao> profissoesOrdenada) {
		this.profissoesOrdenada = profissoesOrdenada;
	}
	

}

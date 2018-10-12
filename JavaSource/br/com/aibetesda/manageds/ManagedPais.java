package br.com.aibetesda.manageds;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.aibetesda.controladores.ControladorPais;
import br.com.aibetesda.modelos.Pais;

@ManagedBean(name="managedPais")
@SessionScoped
public class ManagedPais extends ManagedCadastroConsulta{
	
	private static final Logger log = LoggerFactory.getLogger(ManagedProfissao.class);
	
	private Pais pais = new Pais();
	private List<Pais> todosPais = new ArrayList<Pais>();
	private Pais paisConsulta = new Pais();
	
	
	public ManagedPais(){
		novo();
		loadAll();
	}
	
	@Override
	public void novo() {
		this.pais = new Pais();
	}

	@Override
	public void salvar() {
		try {
			if(!objetoValido(pais))
				return;
			
			new ControladorPais().save(pais);
			addMensagem("Pessoa salva com sucesso!");
			log.info("pessoa salva com sucesso");
			novo();
			loadAll();
		} catch (Exception e) {
			addMensagemErro("Erro ao salvar pessoa: "+e.getMessage());
			log.error("pessoa não foi salvo: "+e.getMessage());
		}
	}

	@Override
	public void excluir() {
		try {
			new ControladorPais().delete(pais);
			addMensagem("Pessoa excluida com sucessso!");
			log.info("Pessoa excluida com sucesso");
			novo();
			loadAll();
		} catch (Exception e) {
			addMensagemErro("Erro ao excluir pessoa: "+e.getMessage());
			log.error("Pessoa não foi excluida: "+e.getMessage());
		}
	}

	@Override
	public void selecionar() {
		if(paisConsulta != null)
			setPais(new ControladorPais().getById(paisConsulta.getId()));
	}

	@Override
	public void loadAll() {
		this.todosPais = new ControladorPais().loadAll();
	}
	
	public List<Pais> getPaisQuery(String query){
		return new ControladorPais().getPaisQuery(query);
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public List<Pais> getTodosPais() {
		return todosPais;
	}

	public void setTodosPais(List<Pais> todosPais) {
		this.todosPais = todosPais;
	}

	public Pais getPaisConsulta() {
		return paisConsulta;
	}

	public void setPaisConsulta(Pais paisConsulta) {
		this.paisConsulta = paisConsulta;
	}

}

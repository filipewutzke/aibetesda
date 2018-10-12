package br.com.aibetesda.manageds;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.aibetesda.controladores.ControladorBairro;
import br.com.aibetesda.modelos.Bairro;

@ManagedBean(name="managedBairro")
@SessionScoped
public class ManagedBairro extends ManagedCadastroConsulta {
	
	private static final Logger log = LoggerFactory.getLogger(ManagedBairro.class);
	
	private Bairro bairro = new Bairro();
	private Bairro bairroConsulta = new Bairro();
	private List<Bairro> bairros = new ArrayList<Bairro>();
	
	
	public ManagedBairro(){
		novo();
		loadAll();
	}
	
	@Override
	public void novo() {
		this.bairro = new Bairro();
	}

	@Override
	public void salvar() {
		try {
			if(!objetoValido(bairro))
				return;
			
			new ControladorBairro().save(bairro);
			addMensagem("Bairro salvo com sucesso");
			log.info("bairro salvo com sucesso");
			novo();
			loadAll();
		} catch (Exception e) {
			addMensagemErro("Erro ao salvar bairro: "+e.getMessage());
			log.error("bairro não foi salvo: "+e.getMessage());
		}
	}

	@Override
	public void excluir() {
		try {
			new ControladorBairro().delete(bairro);
			addMensagem("Bairro excluido com sucessso");
			log.info("Bairro excluido com sucesso");
			novo();
			loadAll();
		} catch (Exception e) {
			addMensagemErro("Erro ao excluir bairro: "+e.getMessage());
			log.error("Bairro não foi excluido: "+e.getMessage());
		}
	}

	@Override
	public void selecionar() {
		if(bairroConsulta != null)
			setBairro(new ControladorBairro().getById(bairroConsulta.getId()));
	}

	@Override
	public void loadAll() {
		this.bairros = new ControladorBairro().loadAll();
	}
	
	public List<Bairro> getBairroQuery(String nome){
		return new ControladorBairro().getBairroQuery(nome);
	}
	
	public Bairro getBairroNome(String nome){
		return new ControladorBairro().getBairroNome(nome);
	}

	
	public Bairro getBairro() {
		return bairro;
	}

	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}

	public Bairro getBairroConsulta() {
		return bairroConsulta;
	}

	public void setBairroConsulta(Bairro bairroConsulta) {
		this.bairroConsulta = bairroConsulta;
	}

	public List<Bairro> getBairros() {
		return bairros;
	}

	public void setBairros(List<Bairro> bairros) {
		this.bairros = bairros;
	}
	

}

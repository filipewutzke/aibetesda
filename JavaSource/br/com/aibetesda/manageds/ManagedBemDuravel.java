package br.com.aibetesda.manageds;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.aibetesda.controladores.ControladorBemDuravel;
import br.com.aibetesda.modelos.BemDuravel;

@ManagedBean(name="managedBemDuravel")
@SessionScoped
public class ManagedBemDuravel extends ManagedCadastroConsulta{
	
	private static final Logger log = LoggerFactory.getLogger(ManagedBemDuravel.class);
	
	private BemDuravel bemDuravel = new BemDuravel();
	private BemDuravel bemDuravelConsulta = new BemDuravel();
	private List<BemDuravel> bensDuraveis = new ArrayList<BemDuravel>();
	
	public ManagedBemDuravel() {
		novo();
		loadAll();
	}
	
	@Override
	public void novo() {
		this.bemDuravel = new BemDuravel();
		loadAll();
	}

	@Override
	public void salvar() {
		try {
			if(!objetoValido(bemDuravel))
				return;
			
			new ControladorBemDuravel().save(bemDuravel);
			log.info("Bem Duravel salvo");
			addMensagem("Bem Duravel salvo com sucesso!");
			novo();
		} catch (Exception e) {
			e.printStackTrace();
			addMensagemErro(e);
			log.error("Erro ao salvar bem duravel: " + e.getMessage());
		}
	}

	@Override
	public void excluir() {
		try {
			if (bemDuravel.getId() == null) {
				addMensagemErro("Bem Duravel vazio!");
				return;
			}
			
			new ControladorBemDuravel().delete(bemDuravel);
			log.info("Bem Duravel excluido");
			addMensagem("Bem Duravel excluído com sucesso!");
			novo();
		} catch (Exception e) {
			e.printStackTrace();
			addMensagemErro(e);
			log.error("Erro ao excluir bem duravel: " + e.getMessage());
		}
	}

	@Override
	public void selecionar() {
		if(bemDuravelConsulta != null)
			setBemDuravel(new ControladorBemDuravel().getById(bemDuravelConsulta.getId()));
	}

	@Override
	public void loadAll() {
		this.bensDuraveis = new ControladorBemDuravel().loadAll();
	}
	
	public List<BemDuravel> getSalaQuery(String query){
		return new ControladorBemDuravel().getBemDuravelQuery(query);
	}
	
	
	public BemDuravel getBemDuravel() {
		return bemDuravel;
	}

	public void setBemDuravel(BemDuravel bemDuravel) {
		this.bemDuravel = bemDuravel;
	}

	public BemDuravel getBemDuravelConsulta() {
		return bemDuravelConsulta;
	}

	public void setBemDuravelConsulta(BemDuravel bemDuravelConsulta) {
		this.bemDuravelConsulta = bemDuravelConsulta;
	}

	public List<BemDuravel> getBensDuraveis() {
		return bensDuraveis;
	}

	public void setBensDuraveis(List<BemDuravel> bensDuraveis) {
		this.bensDuraveis = bensDuraveis;
	}
	
}

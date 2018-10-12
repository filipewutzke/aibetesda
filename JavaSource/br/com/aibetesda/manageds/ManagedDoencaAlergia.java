package br.com.aibetesda.manageds;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.aibetesda.controladores.ControladorDoencaAlergia;
import br.com.aibetesda.modelos.DoencaAlergia;
import br.com.aibetesda.util.UserUtils;

@ManagedBean(name="managedDoencaAlergia")
@SessionScoped
public class ManagedDoencaAlergia extends ManagedCadastroConsulta{

	private DoencaAlergia doencaAlergia = new DoencaAlergia();
	private DoencaAlergia DoencaAlergiaConsulta = new DoencaAlergia();
	private List<DoencaAlergia> DoencaAlergias = new ArrayList<DoencaAlergia>();

	private static final Logger log = LoggerFactory.getLogger(ManagedDoencaAlergia.class);

	public ManagedDoencaAlergia() {
		novo();
		loadAll();
	}
	
	@Override
	public void novo() {
		doencaAlergia = new DoencaAlergia();
	}

	@Override
	public void salvar() {
		try{
			if(!objetoValido(doencaAlergia))
				return;
			
			ControladorDoencaAlergia da = new ControladorDoencaAlergia();
			da.save(doencaAlergia);
			addMensagem("Doenca/Alergia salvo com sucesso!");
			novo();
			loadAll();
			log.info("Doenca/Alergia salvo com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			addMensagemErro("Erro ao salvar Doenca/Alergia: "+e.getMessage());
			log.error("Doenca/Alergia não foi salvo: "+e.getMessage());
		}
	}

	
	@Override
	public void excluir() {
		try{
			if(doencaAlergia.getId() == null){
				addMensagemErro("Curso vazio!");
				return;
			}
			ControladorDoencaAlergia da = new ControladorDoencaAlergia();
			if(da.delete(doencaAlergia)){
				addMensagem("Doenca/Alergia excluido!");
				log.info(UserUtils.getUsuarioLogado()+" excluiu uma Doenca/Alergia: "+doencaAlergia);
				novo();
				loadAll();
			}else{
				addMensagem("Doenca/Alergia não foi excluido! Verifique os dados");
			}
		} catch(Exception e){
			e.printStackTrace();
			addMensagem(e.getMessage(), "Erro ao excluir Doenca/Alergia!");
			log.error("Erro ao excluir Doenca/Alergia: "+e.getMessage());
		}		
	}

	@Override
	public void selecionar() {
		if(DoencaAlergiaConsulta != null && DoencaAlergiaConsulta.getId() != null){
			this.doencaAlergia = new ControladorDoencaAlergia().getById(DoencaAlergiaConsulta.getId());
		}	
	}

	@Override
	public void loadAll() {
		this.DoencaAlergias = new ControladorDoencaAlergia().loadAll();
	}
	
	public List<DoencaAlergia> getDoencaAlergiaQuery(String query){
		return new ControladorDoencaAlergia().getDoencaAlergiaQuery(query);
	}

	public DoencaAlergia getDoencaAlergia() {
		return doencaAlergia;
	}

	public void setDoencaAlergia(DoencaAlergia doencaAlergia) {
		this.doencaAlergia = doencaAlergia;
	}

	public DoencaAlergia getDoencaAlergiaConsulta() {
		return DoencaAlergiaConsulta;
	}

	public void setDoencaAlergiaConsulta(DoencaAlergia doencaAlergiaConsulta) {
		DoencaAlergiaConsulta = doencaAlergiaConsulta;
	}

	public List<DoencaAlergia> getDoencaAlergias() {
		return DoencaAlergias;
	}

	public void setDoencaAlergias(List<DoencaAlergia> doencaAlergias) {
		DoencaAlergias = doencaAlergias;
	}

}

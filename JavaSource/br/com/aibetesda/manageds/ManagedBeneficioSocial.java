package br.com.aibetesda.manageds;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.aibetesda.controladores.ControladorBeneficioSocial;
import br.com.aibetesda.modelos.BeneficioSocial;
import br.com.aibetesda.util.UserUtils;

@ManagedBean(name="managedBeneficioSocial")
@SessionScoped
public class ManagedBeneficioSocial extends ManagedCadastroConsulta{
	
	private BeneficioSocial beneficioSocial = new BeneficioSocial();
	private BeneficioSocial beneficioSocialConsulta = new BeneficioSocial();
	private List<BeneficioSocial> beneficiosSociais = new ArrayList<BeneficioSocial>();
	
	private static final Logger log = LoggerFactory.getLogger(ManagedCurso.class);

	public ManagedBeneficioSocial() {
		novo();
		loadAll();
	}
	
	@Override
	public void novo() {
		beneficioSocial = new BeneficioSocial();
	}

	@Override
	public void salvar() {
		try{
			if(!objetoValido(beneficioSocial))
				return;
			
			ControladorBeneficioSocial cc = new ControladorBeneficioSocial();
			cc.save(beneficioSocial);
			addMensagem("Beneficio Social salvo com sucesso!");
			log.info("Beneficio Social salvo com sucesso!");
			novo();
			loadAll();
		} catch (Exception e) {
			e.printStackTrace();
			addMensagemErro("Erro ao salvar beneficio social: "+e.getMessage());
			log.error("Beneficio social não foi salvo: "+e.getMessage());
		}
	}

	
	@Override
	public void excluir() {
		try{
			if(beneficioSocial.getId() == null){
				addMensagemErro("Beneficio Social vazio!");
				return;
			}
			ControladorBeneficioSocial cc = new ControladorBeneficioSocial();
			if(cc.delete(beneficioSocial)){
				addMensagem("Benefício Social excluido!");
				log.info(UserUtils.getUsuarioLogado()+" excluiu uma beneficio social: "+beneficioSocial);
				novo();
				loadAll();
			}else{
				addMensagem("Beneficio Social não foi excluido! Verifique os dados");
			}
		} catch(Exception e){
			log.error("Erro ao excluir beneficio social: "+e.getMessage());
			e.printStackTrace();
			addMensagem(e.getMessage(), "Erro ao excluir beneficio social!");	
		}		
	}

	@Override
	public void selecionar() {
		if(beneficioSocialConsulta != null && beneficioSocialConsulta.getId() != null){
			this.beneficioSocial = new ControladorBeneficioSocial().getById(beneficioSocialConsulta.getId());
		}	
	}

	@Override
	public void loadAll() {
		this.beneficiosSociais = new ControladorBeneficioSocial().loadAll();
	}
	
	public List<BeneficioSocial> getCursoQuery(String query){
		return new ControladorBeneficioSocial().getBeneficioQuery(query);
	}
	

	public BeneficioSocial getBeneficioSocial() {
		return beneficioSocial;
	}

	public void setBeneficioSocial(BeneficioSocial beneficioSocial) {
		this.beneficioSocial = beneficioSocial;
	}

	public BeneficioSocial getBeneficioSocialConsulta() {
		return beneficioSocialConsulta;
	}

	public void setBeneficioSocialConsulta(BeneficioSocial beneficioSocialConsulta) {
		this.beneficioSocialConsulta = beneficioSocialConsulta;
	}

	public List<BeneficioSocial> getBeneficiosSociais() {
		return beneficiosSociais;
	}

	public void setBeneficiosSociais(List<BeneficioSocial> beneficiosSociais) {
		this.beneficiosSociais = beneficiosSociais;
	}
	
}

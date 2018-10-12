package br.com.aibetesda.controladores;

import java.util.Date;
import java.util.List;

import br.com.aibetesda.dao.DAOUtils;
import br.com.aibetesda.dao.especificos.IBeneficioSocialDAO;
import br.com.aibetesda.modelos.BeneficioSocial;
import br.com.aibetesda.util.ApplicationContextUtils;
import br.com.aibetesda.util.UserUtils;

public class ControladorBeneficioSocial extends Controlador<BeneficioSocial>{
	
	public ControladorBeneficioSocial() {}
	
	@Override
	public IBeneficioSocialDAO getDAO() {
		return (IBeneficioSocialDAO) ApplicationContextUtils.getDAOBean(DAOUtils.BENEFICIOSOCIAL);
	}
	
	public BeneficioSocial save(BeneficioSocial beneficio) throws Exception{
		if(beneficio.getUsuarioRegistro() == null){
			beneficio.setUsuarioRegistro(UserUtils.getUsuarioLogado());
			beneficio.setDtRegistro(new Date());
		}
		beneficio.setUsuarioAlteracao(UserUtils.getUsuarioLogado());
		beneficio.setDtAlteracao(new Date());
		
		getDAO().create(beneficio);
		return beneficio;
	}
	
	public boolean delete(BeneficioSocial beneficio) throws Exception{
		getDAO().delete(beneficio.getId());
		return true;
	}

	@Override
	public List<BeneficioSocial> loadAll() {
		return getDAO().readAll();
	}

	@Override
	public BeneficioSocial getById(Long id) {
		return getDAO().read(id);
	}
	
	public BeneficioSocial getBeneficioPorNome(String nome) {
		return getDAO().getBeneficioPorNome(nome);
	}
	
	public List<BeneficioSocial> getBeneficioQuery(String nome){
		return getDAO().getBeneficioQuery(nome);
	}

}

package br.com.aibetesda.controladores;

import java.util.Date;
import java.util.List;

import br.com.aibetesda.dao.DAOUtils;
import br.com.aibetesda.dao.especificos.IPlanejamentoPedagogicoAnualDAO;
import br.com.aibetesda.modelos.PlanejamentoAnualDatasReuniao;
import br.com.aibetesda.modelos.PlanejamentoPedagogicoAnual;
import br.com.aibetesda.util.ApplicationContextUtils;
import br.com.aibetesda.util.UserUtils;

public class ControladorPlanejamentoPedagogicoAnual extends Controlador<PlanejamentoPedagogicoAnual> {

	@Override
	public IPlanejamentoPedagogicoAnualDAO getDAO() {
		return (IPlanejamentoPedagogicoAnualDAO) ApplicationContextUtils.getDAOBean(DAOUtils.PLANEJAMENTOPEDAGOGICOANUAL);
	}

	@Override
	public PlanejamentoPedagogicoAnual getById(Long id) {
		try{
			PlanejamentoPedagogicoAnual planejamentoAnual = getDAO().read(id);
			return planejamentoAnual;
		} catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public PlanejamentoPedagogicoAnual save(PlanejamentoPedagogicoAnual ppa)
			throws Exception {
		if(ppa.getUsuarioRegistro()==null){
			ppa.setUsuarioRegistro(UserUtils.getUsuarioLogado());
			ppa.setDtRegistro(new Date());
		}
		ppa.setUsuarioAlteracao(UserUtils.getUsuarioLogado());
		ppa.setDtAlteracao(new Date());
		
		getDAO().create(ppa);
		return ppa;
	}

	@Override
	public boolean delete(PlanejamentoPedagogicoAnual planejamentoAnual) throws Exception {
		getDAO().delete(planejamentoAnual.getId());
		return true;
	}

	@Override
	public List<PlanejamentoPedagogicoAnual> loadAll() {
		return getDAO().readAll();
	}
	
	public PlanejamentoPedagogicoAnual getPlanejamentoPorAno(String ano){
		return getDAO().getPlanejamentoPorAno(ano);
	}
	
	public List<PlanejamentoAnualDatasReuniao> getAvisoDatasReunioes(){
		return getDAO().getAvisoDatasReunioes();
	}
	
	public PlanejamentoPedagogicoAnual getReuniaoPorAno (Long id){
		return getDAO().getReuniaoPorAno(id);
	}

}

package br.com.aibetesda.controladores;

import java.util.List;

import br.com.aibetesda.dao.DAOUtils;
import br.com.aibetesda.dao.especificos.IAssistenciaSocialDAO;
import br.com.aibetesda.modelos.AssistenciaSocial;
import br.com.aibetesda.util.ApplicationContextUtils;

public class ControladorAssistenciaSocial extends Controlador<AssistenciaSocial>{
	
	@Override
	public IAssistenciaSocialDAO getDAO() {
		return (IAssistenciaSocialDAO) ApplicationContextUtils.getDAOBean(DAOUtils.ASSISTENCIASOCIAL);
	}

	@Override
	public AssistenciaSocial getById(Long id) {
		try{
			AssistenciaSocial assistenciaSocial = getDAO().read(id);
			return assistenciaSocial;
		} catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public AssistenciaSocial save(AssistenciaSocial assistenciaSocial) throws Exception {
		getDAO().create(assistenciaSocial);
		return assistenciaSocial;
	}

	@Override
	public boolean delete(AssistenciaSocial assistenciaSocial) throws Exception {
		getDAO().delete(assistenciaSocial.getId());
		return true;
	}

	@Override
	public List<AssistenciaSocial> loadAll() {
		return getDAO().readAll();
	}
	

}

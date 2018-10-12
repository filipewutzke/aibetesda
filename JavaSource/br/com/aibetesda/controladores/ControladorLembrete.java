package br.com.aibetesda.controladores;

import java.util.Date;
import java.util.List;

import br.com.aibetesda.dao.DAOUtils;
import br.com.aibetesda.dao.especificos.ILembreteDAO;
import br.com.aibetesda.modelos.Lembrete;
import br.com.aibetesda.util.ApplicationContextUtils;
import br.com.aibetesda.util.UserUtils;

public class ControladorLembrete extends Controlador<Lembrete>{
	
	@Override
	public ILembreteDAO getDAO() {
		return (ILembreteDAO) ApplicationContextUtils.getDAOBean(DAOUtils.LEMBRETE);
	}

	@Override
	public Lembrete getById(Long id) {
		try{
			Lembrete lembrete = getDAO().read(id);
			return lembrete;
		} catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Lembrete save(Lembrete lembrete) throws Exception {
		if(lembrete.getUsuarioRegistro() == null){
			lembrete.setUsuarioRegistro(UserUtils.getUsuarioLogado());
			lembrete.setDtRegistro(new Date());
		}
		lembrete.setUsuarioAlteracao(UserUtils.getUsuarioLogado());
		lembrete.setDtAlteracao(new Date());
		
		getDAO().create(lembrete);
		return lembrete;
	}

	@Override
	public boolean delete(Lembrete lembrete) throws Exception {
		getDAO().delete(lembrete.getId());
		return true;
	}

	@Override
	public List<Lembrete> loadAll() {
		return getDAO().readAll();
	}
	
	public List<Lembrete> getAvisoDatas(){
		return getDAO().getAvisoDatas();
	}
	
	public Lembrete getReuniaoID(Long id){
		return getDAO().getReuniaoID(id);
	}

}

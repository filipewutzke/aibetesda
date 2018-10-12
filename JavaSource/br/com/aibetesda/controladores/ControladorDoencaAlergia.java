package br.com.aibetesda.controladores;

import java.util.Date;
import java.util.List;

import br.com.aibetesda.dao.DAOUtils;
import br.com.aibetesda.dao.especificos.IDoencaAlergiaDAO;
import br.com.aibetesda.modelos.DoencaAlergia;
import br.com.aibetesda.util.ApplicationContextUtils;
import br.com.aibetesda.util.UserUtils;

public class ControladorDoencaAlergia extends Controlador<DoencaAlergia> {
	
	public ControladorDoencaAlergia() {}
	
	@Override
	public IDoencaAlergiaDAO getDAO() {
		return (IDoencaAlergiaDAO) ApplicationContextUtils.getDAOBean(DAOUtils.DOENCAALERGIA);
	}
	
	public DoencaAlergia save(DoencaAlergia doencaAlergia) throws Exception{
		if(doencaAlergia.getUsuarioRegistro() == null){
			doencaAlergia.setUsuarioRegistro(UserUtils.getUsuarioLogado());
			doencaAlergia.setDtRegistro(new Date());
		}
		doencaAlergia.setUsuarioAlteracao(UserUtils.getUsuarioLogado());
		doencaAlergia.setDtAlteracao(new Date());
		
		getDAO().create(doencaAlergia);
		return doencaAlergia;
	}
	
	public boolean delete(DoencaAlergia doencaAlergia) throws Exception{
		getDAO().delete(doencaAlergia.getId());
		return true;
	}

	@Override
	public List<DoencaAlergia> loadAll() {
		return getDAO().readAll();
	}

	@Override
	public DoencaAlergia getById(Long id) {
		return getDAO().read(id);
	}
	
	public DoencaAlergia getDoencaAlergiaPorNome(String nome) {
		return getDAO().getDoencaAlergiaPorNome(nome);
	}
	
	public List<DoencaAlergia> getDoencaAlergiaQuery(String nome){
		return getDAO().getDoencaAlergiaQuery(nome);
	}

}

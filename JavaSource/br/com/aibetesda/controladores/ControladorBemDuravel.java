package br.com.aibetesda.controladores;

import java.util.Date;
import java.util.List;

import br.com.aibetesda.dao.DAOUtils;
import br.com.aibetesda.dao.especificos.IBemDuravelDAO;
import br.com.aibetesda.modelos.BemDuravel;
import br.com.aibetesda.util.ApplicationContextUtils;
import br.com.aibetesda.util.UserUtils;

public class ControladorBemDuravel extends Controlador<BemDuravel>{
	
	@Override
	public IBemDuravelDAO getDAO() {
		return (IBemDuravelDAO) ApplicationContextUtils.getDAOBean(DAOUtils.BEMDURAVEL);
	}

	@Override
	public BemDuravel getById(Long id) {
		return getDAO().read(id);
	}

	@Override
	public BemDuravel save(BemDuravel b) throws Exception {
		if(b.getUsuarioRegistro() == null){
			b.setUsuarioRegistro(UserUtils.getUsuarioLogado());
			b.setDtRegistro(new Date());
		}
		b.setUsuarioAlteracao(UserUtils.getUsuarioLogado());
		b.setDtAlteracao(new Date());
		
		getDAO().create(b);
		return b;
	}

	@Override
	public boolean delete(BemDuravel b) throws Exception {
		getDAO().delete(b.getId());
		return true;
	}

	@Override
	public List<BemDuravel> loadAll() {
		return getDAO().readAll();
	}
	
	public List<BemDuravel> getBemDuravelQuery(String query){
		return getDAO().getBemDuravelQuery(query);
	}
	
	public BemDuravel getBemDuravelNome(String nome){
		return getDAO().getBemDuravelNome(nome);
	}

}

package br.com.aibetesda.controladores;

import java.util.List;

import br.com.aibetesda.dao.DAOUtils;
import br.com.aibetesda.dao.especificos.IBairroDAO;
import br.com.aibetesda.modelos.Bairro;
import br.com.aibetesda.util.ApplicationContextUtils;

public class ControladorBairro extends Controlador<Bairro>{

	@Override
	public IBairroDAO getDAO() {
		return (IBairroDAO) ApplicationContextUtils.getDAOBean(DAOUtils.BAIRRO);
	}

	@Override
	public Bairro getById(Long id) {
		return getDAO().read(id);
	}

	@Override
	public Bairro save(Bairro bairro) throws Exception {
		return getDAO().create(bairro);
	}

	@Override
	public boolean delete(Bairro bairro) throws Exception {
		getDAO().delete(bairro.getId());
		return true;
	}

	@Override
	public List<Bairro> loadAll() {
		return getDAO().readAll();
	}
	
	public List<Bairro> getBairroQuery(String bairro){
		return getDAO().getBairroQuery(bairro);
	}
	
	public Bairro getBairroNome(String nome){
		return getDAO().getBairroNome(nome);
	}

}

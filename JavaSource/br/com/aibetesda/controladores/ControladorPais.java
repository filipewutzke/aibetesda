package br.com.aibetesda.controladores;

import java.util.Date;
import java.util.List;

import br.com.aibetesda.dao.DAOUtils;
import br.com.aibetesda.dao.especificos.IPaisDAO;
import br.com.aibetesda.modelos.Pais;
import br.com.aibetesda.util.ApplicationContextUtils;
import br.com.aibetesda.util.UserUtils;

public class ControladorPais extends Controlador<Pais>{

	@Override
	public IPaisDAO getDAO() {
		return (IPaisDAO) ApplicationContextUtils.getDAOBean(DAOUtils.PAIS);
	}

	@Override
	public Pais getById(Long id) {
		return getDAO().read(id);
	}

	@Override
	public Pais save(Pais p) throws Exception {
		if(p.getUsuarioRegistro() == null){
			p.setUsuarioRegistro(UserUtils.getUsuarioLogado());
			p.setDtRegistro(new Date());
		}
		p.setUsuarioAlteracao(UserUtils.getUsuarioLogado());
		p.setDtAlteracao(new Date());
		
		getDAO().create(p);
		return p;
	}

	@Override
	public boolean delete(Pais p) throws Exception {
		getDAO().delete(p.getId());
		return true;
	}

	@Override
	public List<Pais> loadAll() {
		return getDAO().readAll();
	}
	
	public List<Pais> getPaisQuery(String query){
		return getDAO().getPaisQuery(query);
	}
	
	public Pais getPaisNome(String nome){
		return getDAO().getPaisNome(nome);
	}

}

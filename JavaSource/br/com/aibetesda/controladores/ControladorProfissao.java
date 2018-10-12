package br.com.aibetesda.controladores;

import java.util.Date;
import java.util.List;

import br.com.aibetesda.dao.DAOUtils;
import br.com.aibetesda.dao.especificos.IProfissaoDAO;
import br.com.aibetesda.modelos.Profissao;
import br.com.aibetesda.util.ApplicationContextUtils;
import br.com.aibetesda.util.UserUtils;

public class ControladorProfissao extends Controlador<Profissao>{

	@Override
	public IProfissaoDAO getDAO() {
		return (IProfissaoDAO) ApplicationContextUtils.getDAOBean(DAOUtils.PROFISSAO);
	}

	@Override
	public Profissao getById(Long id) {
		return getDAO().read(id);
	}

	@Override
	public Profissao save(Profissao p) throws Exception {
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
	public boolean delete(Profissao p) throws Exception {
		getDAO().delete(p.getId());
		return true;
	}

	@Override
	public List<Profissao> loadAll() {
		return getDAO().readAll();
	}
	
	public List<Profissao> loadProfissaoOrdenada() {
		return getDAO().loadProfissaoOrdenada();
	}
	
	public List<Profissao> getProfissaoQuery(String query){
		return getDAO().getProfissaoQuery(query);
	}
	
	public Profissao getProfissaoNome(String nome){
		return getDAO().getProfissaoNome(nome);
	}

}

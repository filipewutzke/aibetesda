package br.com.aibetesda.controladores;

import java.util.Date;
import java.util.List;

import br.com.aibetesda.dao.DAOUtils;
import br.com.aibetesda.dao.especificos.ISalaAulaDAO;
import br.com.aibetesda.modelos.SalaAula;
import br.com.aibetesda.util.ApplicationContextUtils;
import br.com.aibetesda.util.UserUtils;

public class ControladorSalaAula extends Controlador<SalaAula> {

	@Override
	public ISalaAulaDAO getDAO() {
		return (ISalaAulaDAO) ApplicationContextUtils.getDAOBean(DAOUtils.SALAAULA);
	}

	@Override
	public SalaAula getById(Long id) {
		return getDAO().read(id);
	}

	@Override
	public SalaAula save(SalaAula sala) throws Exception {
		if(sala.getUsuarioRegistro() == null){
			sala.setUsuarioRegistro(UserUtils.getUsuarioLogado());
			sala.setDtRegistro(new Date());
		}
		sala.setUsuarioAlteracao(UserUtils.getUsuarioLogado());
		sala.setDtAlteracao(new Date());
		
		getDAO().create(sala);
		return sala;
	}

	@Override
	public boolean delete(SalaAula obj) throws Exception {
		getDAO().delete(obj.getId());
		return true;
	}

	@Override
	public List<SalaAula> loadAll() {
		return getDAO().readAll();
	}
	
	public List<SalaAula> getSalaQuery(String query){
		return getDAO().getSalaQuery(query);
	}
	
	public SalaAula getSalaNome(String nome){
		return getDAO().getSalaNome(nome);
	}
	
}

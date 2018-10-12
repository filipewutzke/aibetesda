package br.com.aibetesda.controladores;

import java.util.Date;
import java.util.List;

import br.com.aibetesda.dao.DAOUtils;
import br.com.aibetesda.dao.especificos.IAgendaTelefoneDAO;
import br.com.aibetesda.modelos.AgendaTelefone;
import br.com.aibetesda.util.ApplicationContextUtils;
import br.com.aibetesda.util.UserUtils;

public class ControladorAgendaTelefone extends Controlador<AgendaTelefone>{

	public ControladorAgendaTelefone() {}
	
	public AgendaTelefone getById(Long id) {
		try {
			AgendaTelefone agendaTelefone = getDAO().read(id);
			return agendaTelefone;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public AgendaTelefone save(AgendaTelefone agendaTelefone) throws Exception {
		if(agendaTelefone.getUsuarioRegistro() == null){
			agendaTelefone.setUsuarioRegistro(UserUtils.getUsuarioLogado());
			agendaTelefone.setDtRegistro(new Date());
		}
		agendaTelefone.setUsuarioRegistro(UserUtils.getUsuarioLogado());
		agendaTelefone.setDtRegistro(new Date());
		
		getDAO().create(agendaTelefone);
		return agendaTelefone;
	}

	public boolean delete(AgendaTelefone agendaTelefone) throws Exception {
		getDAO().delete(agendaTelefone.getId());
		return true;
	}

	@Override
	public List<AgendaTelefone> loadAll() {
		return getDAO().readAll();
	}
	
	@Override
	public IAgendaTelefoneDAO getDAO() {
		return (IAgendaTelefoneDAO) ApplicationContextUtils.getDAOBean(DAOUtils.AGENDATELEFONE);
	}

	public Object getByName(String nome) {
		return getDAO().getByName(nome);
	}

	
}

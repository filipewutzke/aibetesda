package br.com.aibetesda.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.aibetesda.dao.especificos.IAgendaTelefoneDAO;
import br.com.aibetesda.modelos.AgendaTelefone;

@Repository(value=DAOUtils.AGENDATELEFONE)
public class AgendaTelefoneDAO extends HibernateAibetesdaDAO implements IAgendaTelefoneDAO{

	@Transactional
	public AgendaTelefone create(AgendaTelefone newInstance) throws Exception {
		getHibernateTemplate().saveOrUpdate(newInstance);
		return newInstance;
	}

	@Override
	public AgendaTelefone read(Long id) {
		AgendaTelefone at = getHibernateTemplate().get(AgendaTelefone.class, id);
		return at;
	}
	
	@Override
	public List<AgendaTelefone> readAll() {
		return getHibernateTemplate().loadAll(AgendaTelefone.class);
	}
	
	@Transactional
	@Override
	public void delete(Long id) {
		if(id == null)
			return;
		AgendaTelefone todelete = read(id);
		getHibernateTemplate().delete(todelete);
	}

	@Override
	public AgendaTelefone getByName(String nome) {		
		return (AgendaTelefone) getHibernateTemplate().find("from AgendaTelefone where nome = ?",nome).get(0);
	}
	
	
}

package br.com.aibetesda.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.aibetesda.dao.especificos.IAssistenciaSocialDAO;
import br.com.aibetesda.modelos.AssistenciaSocial;

@Repository (value = DAOUtils.ASSISTENCIASOCIAL)
public class AssistenciaSocialDAO extends HibernateAibetesdaDAO implements IAssistenciaSocialDAO{

	@Override
	@Transactional
	public AssistenciaSocial create(AssistenciaSocial assistenciaSocial) throws Exception {
		getHibernateTemplate().saveOrUpdate(assistenciaSocial);
		return assistenciaSocial;
	}

	@Override
	@Transactional
	public void delete(Long id) throws Exception {
		if (id == null)
			return;
		AssistenciaSocial todelete = read(id);
		getHibernateTemplate().delete(todelete);
	}

	@Override
	public AssistenciaSocial read(Long id) {
		AssistenciaSocial as = getHibernateTemplate().get(AssistenciaSocial.class, id);
		return as;
	}

	@Override
	public List<AssistenciaSocial> readAll() {
		return getHibernateTemplate().loadAll(AssistenciaSocial.class);
	}

	@Override
	public AssistenciaSocial getAssistenciaPorInteressado(String nome) {
		Criteria c = getSession().createCriteria(AssistenciaSocial.class);
		c.add(Restrictions.eq("nome", nome));
		
		return (AssistenciaSocial) c.uniqueResult();
	}

	
}
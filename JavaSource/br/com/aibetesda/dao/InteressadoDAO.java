package br.com.aibetesda.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.aibetesda.dao.especificos.IInteressadoDAO;
import br.com.aibetesda.modelos.Interessado;
import br.com.aibetesda.util.HibernateUtils;

@Repository(value=DAOUtils.INTERESSADO)
public class InteressadoDAO extends HibernateAibetesdaDAO implements IInteressadoDAO{

	@Transactional
	public Interessado create(Interessado interessado) throws DataAccessException {
		getHibernateTemplate().saveOrUpdate(interessado);
		
		return interessado;
	}

	@Transactional
	public void delete(Long id) {
		if (id == null)
			return;

		Interessado todelete = read(id);
		getHibernateTemplate().delete(todelete);
	}

	@Override
	public Interessado read(Long id) {
		Interessado i = getHibernateTemplate().get(Interessado.class, id);	
		HibernateUtils.initList(i.getBensDuraveis());
		return i;
	}

	@Override
	public List<Interessado> readAll() {
		return getHibernateTemplate().loadAll(Interessado.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Interessado> getByNameLimit(String nome) {
		Criteria c = getSession().createCriteria(Interessado.class);
		c.add(Restrictions.ilike("nome", nome+"%", MatchMode.ANYWHERE));
		c.setMaxResults(5);
		
		return c.list();
	}

	@Override
	public Interessado getByName(String nome) {
		Criteria c = getSession().createCriteria(Interessado.class);
		c.add(Restrictions.eq("nome", nome));
		
		return (Interessado) c.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Interessado> getByInteressadoAlunoLimit(String nome) {
		Criteria c = getSession().createCriteria(Interessado.class);
		c.add(Restrictions.ilike("nome", nome+"%", MatchMode.ANYWHERE));
		c.add(Restrictions.eq("aptoMatricula", true));
		
		return c.list();
	}

}

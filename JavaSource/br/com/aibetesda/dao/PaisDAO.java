package br.com.aibetesda.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.aibetesda.dao.especificos.IPaisDAO;
import br.com.aibetesda.modelos.Pais;

@Repository(value=DAOUtils.PAIS)
public class PaisDAO extends HibernateAibetesdaDAO implements IPaisDAO{
	
	@Transactional
	public Pais create(Pais pais) throws Exception {
		getHibernateTemplate().saveOrUpdate(pais);
		return pais;
	}

	@Transactional
	public void delete(Long id) throws Exception {
		if (id == null)
			return;
		Pais toDelete = read(id);
		getHibernateTemplate().delete(toDelete);
	}

	@Override
	public Pais read(Long id) {
		Pais pais = getHibernateTemplate().get(Pais.class, id);
		return pais;
	}

	@Override
	public List<Pais> readAll() {
		return getHibernateTemplate().loadAll(Pais.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Pais> getPaisQuery(String query) {
		Criteria c = getSession().createCriteria(Pais.class);
		c.add(Restrictions.ilike("nome", query, MatchMode.ANYWHERE));
		
		c.setMaxResults(5);
		return c.list();
	}

	@Override
	public Pais getPaisNome(String nome) {
		Criteria c = getSession().createCriteria(Pais.class);
		c.add(Restrictions.eq("nome", nome));
		
		return (Pais) c.uniqueResult();
	}

}

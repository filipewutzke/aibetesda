package br.com.aibetesda.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.aibetesda.dao.especificos.IBairroDAO;
import br.com.aibetesda.modelos.Bairro;

@Repository(value=DAOUtils.BAIRRO)
public class BairroDAO extends HibernateAibetesdaDAO implements IBairroDAO{
	
	@Transactional
	@Override
	public Bairro create(Bairro bairro) throws Exception {
		getHibernateTemplate().saveOrUpdate(bairro);
		return bairro;
	}
	
	@Transactional
	@Override
	public void delete(Long id) throws Exception {
		Bairro toRemove = read(id);
		getHibernateTemplate().delete(toRemove);
	}

	@Override
	public Bairro read(Long id) {
		return getHibernateTemplate().get(Bairro.class, id);
	}

	@Override
	public List<Bairro> readAll() {
		return getHibernateTemplate().loadAll(Bairro.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Bairro> getBairroQuery(String nome) {
		Criteria c = getSession().createCriteria(Bairro.class);
		c.add(Restrictions.ilike("nome", nome, MatchMode.ANYWHERE));
		
		c.setMaxResults(5);
		return c.list();
	}

	@Override
	public Bairro getBairroNome(String nome) {
		Criteria c = getSession().createCriteria(Bairro.class);
		c.add(Restrictions.eq("nome", nome));
		
		return (Bairro) c.uniqueResult();
	}

}

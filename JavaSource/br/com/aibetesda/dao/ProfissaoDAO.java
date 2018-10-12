package br.com.aibetesda.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.aibetesda.dao.especificos.IProfissaoDAO;
import br.com.aibetesda.modelos.Profissao;

@Repository(value=DAOUtils.PROFISSAO)
public class ProfissaoDAO extends HibernateAibetesdaDAO implements IProfissaoDAO{
	
	@Transactional
	public Profissao create(Profissao prof) throws Exception {
		getHibernateTemplate().saveOrUpdate(prof);
		return prof;
	}

	@Transactional
	public void delete(Long id) throws Exception {
		Profissao toRemove = read(id);
		getHibernateTemplate().delete(toRemove);
	}

	@Override
	public Profissao read(Long id) {
		return getHibernateTemplate().get(Profissao.class, id);
	}

	@Override
	public List<Profissao> readAll() {
		return getHibernateTemplate().loadAll(Profissao.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Profissao> loadProfissaoOrdenada(){
		Criteria c = getSession().createCriteria(Profissao.class);
		c.addOrder(Order.asc("nome"));
		
		return c.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Profissao> getProfissaoQuery(String query) {
		Criteria c = getSession().createCriteria(Profissao.class);
		c.add(Restrictions.ilike("nome", query, MatchMode.ANYWHERE));
		
		c.setMaxResults(5);
		return c.list();
	}

	@Override
	public Profissao getProfissaoNome(String nome) {
		Criteria c = getSession().createCriteria(Profissao.class);
		c.add(Restrictions.eq("nome", nome));
		
		return (Profissao) c.uniqueResult();
	}

}

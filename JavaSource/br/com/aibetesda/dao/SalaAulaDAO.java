package br.com.aibetesda.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.aibetesda.dao.especificos.ISalaAulaDAO;
import br.com.aibetesda.modelos.SalaAula;
import br.com.aibetesda.util.CacheUtils;

import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.TriggersRemove;
import com.googlecode.ehcache.annotations.When;

@Repository(value=DAOUtils.SALAAULA)
public class SalaAulaDAO extends HibernateAibetesdaDAO implements ISalaAulaDAO {

	@Override
	@Transactional
	@TriggersRemove(cacheName=CacheUtils.SALAS_AULA,when=When.AFTER_METHOD_INVOCATION,removeAll=true)
	public SalaAula create(SalaAula newInstance) throws Exception {
		getHibernateTemplate().saveOrUpdate(newInstance);
		return newInstance;
	}

	@Override
	@Transactional
	@TriggersRemove(cacheName=CacheUtils.SALAS_AULA, when=When.AFTER_METHOD_INVOCATION,removeAll=true)
	public void delete(Long id) {
		SalaAula toRemove = read(id);
		getHibernateTemplate().delete(toRemove);
	}

	@Override
	@Cacheable(cacheName=CacheUtils.SALA_AULA)
	public SalaAula read(Long id) {
		return getHibernateTemplate().get(SalaAula.class, id);
	}

	@Override
	@Cacheable(cacheName=CacheUtils.SALAS_AULA)
	public List<SalaAula> readAll() {
		return getHibernateTemplate().loadAll(SalaAula.class);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Cacheable(cacheName=CacheUtils.SALAS_AULA)
	public List<SalaAula> getSalaQuery(String query) {
		Criteria c = getSession().createCriteria(SalaAula.class);
		c.add(Restrictions.ilike("nome", query, MatchMode.ANYWHERE));
		
		c.setMaxResults(5);
		return c.list();
	}
	
	@Override
	public SalaAula getSalaNome(String nome) {
		Criteria c = getSession().createCriteria(SalaAula.class);
		c.add(Restrictions.eq("nome", nome));
		
		return (SalaAula) c.uniqueResult();
	}
	
}

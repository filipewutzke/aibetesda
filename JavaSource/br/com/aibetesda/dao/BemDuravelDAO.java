package br.com.aibetesda.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.aibetesda.dao.especificos.IBemDuravelDAO;
import br.com.aibetesda.modelos.BemDuravel;
import br.com.aibetesda.util.CacheUtils;

import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.TriggersRemove;
import com.googlecode.ehcache.annotations.When;

@Repository(value=DAOUtils.BEMDURAVEL)
public class BemDuravelDAO extends HibernateAibetesdaDAO implements IBemDuravelDAO{

	@Override
	@Transactional
	@TriggersRemove(cacheName=CacheUtils.BENS_DURAVEIS,when=When.AFTER_METHOD_INVOCATION,removeAll=true)
	public BemDuravel create(BemDuravel b) throws Exception {
		getHibernateTemplate().saveOrUpdate(b);
		return b;
	}

	@Override
	@Transactional
	@TriggersRemove(cacheName=CacheUtils.BENS_DURAVEIS,when=When.AFTER_METHOD_INVOCATION,removeAll=true)
	public void delete(Long id) throws Exception {
		BemDuravel toDelete = read(id);
		getHibernateTemplate().delete(toDelete);
	}

	@Override
	@Cacheable(cacheName=CacheUtils.BEM_DURAVEL)
	public BemDuravel read(Long id) {
		return getHibernateTemplate().get(BemDuravel.class, id);
	}

	@Override
	@Cacheable(cacheName=CacheUtils.BENS_DURAVEIS)
	public List<BemDuravel> readAll() {
		return getHibernateTemplate().loadAll(BemDuravel.class);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Cacheable(cacheName=CacheUtils.BENS_DURAVEIS)
	public List<BemDuravel> getBemDuravelQuery(String query) {
		Criteria c = getSession().createCriteria(BemDuravel.class);
		c.add(Restrictions.ilike("nome", query, MatchMode.ANYWHERE));
		
		c.setMaxResults(5);
		return c.list();
	}

	@Override
	public BemDuravel getBemDuravelNome(String nome) {
		Criteria c = getSession().createCriteria(BemDuravel.class);
		c.add(Restrictions.eq("nome", nome));
		
		return (BemDuravel) c.uniqueResult();
	}

}

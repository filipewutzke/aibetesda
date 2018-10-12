package br.com.aibetesda.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.aibetesda.dao.especificos.ICursoDAO;
import br.com.aibetesda.modelos.Curso;
import br.com.aibetesda.util.CacheUtils;

import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.TriggersRemove;
import com.googlecode.ehcache.annotations.When;

@Repository(value=DAOUtils.CURSO)
public class CursoDAO extends HibernateAibetesdaDAO implements ICursoDAO{

	@Transactional
	@Override
	@TriggersRemove(cacheName=CacheUtils.CURSOS, when=When.AFTER_METHOD_INVOCATION,removeAll=true)
	public Curso create(Curso newInstance) throws Exception {
		getHibernateTemplate().saveOrUpdate(newInstance);
		return newInstance;
	}

	@Override
	@Cacheable(cacheName=CacheUtils.CURSO)
	public Curso read(Long id) {
		Curso c = getHibernateTemplate().get(Curso.class, id);
		return c;
	}

	@Override
	@Cacheable(cacheName=CacheUtils.CURSOS)
	public List<Curso> readAll() {
		return getHibernateTemplate().loadAll(Curso.class);
	}
	
	@Transactional
	@Override
	@TriggersRemove(cacheName=CacheUtils.CURSOS, when=When.AFTER_METHOD_INVOCATION,removeAll=true)
	public void delete(Long id) {
		if (id == null)
			return;
		
		Curso todelete = read(id);
		getHibernateTemplate().delete(todelete);
	}
	
	@Override
	public Curso getCursoPorNome(String nome) {
		Criteria c = getSession().createCriteria(Curso.class);
		c.add(Restrictions.eq("nome", nome));
		
		return (Curso) c.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Curso> getCursoQuery(String nome) {
		Criteria c = getSession().createCriteria(Curso.class);
		c.add(Restrictions.ilike("nome", nome, MatchMode.ANYWHERE));
		c.setMaxResults(5);
		
		return c.list();
	}
	
	
}

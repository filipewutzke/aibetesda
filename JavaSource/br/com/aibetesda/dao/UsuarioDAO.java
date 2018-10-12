package br.com.aibetesda.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.TriggersRemove;
import com.googlecode.ehcache.annotations.When;

import br.com.aibetesda.dao.especificos.IUsuarioDAO;
import br.com.aibetesda.modelos.Usuario;
import br.com.aibetesda.util.CacheUtils;

@Repository(value=DAOUtils.USUARIO)
public class UsuarioDAO extends HibernateAibetesdaDAO implements IUsuarioDAO {

	@Transactional
	@Override
	@TriggersRemove(cacheName=CacheUtils.USUARIOS, when=When.AFTER_METHOD_INVOCATION,removeAll=true)
	public Usuario create(Usuario newInstance) {
		getHibernateTemplate().saveOrUpdate(newInstance);
		return newInstance;
	}

	@Override
	@Cacheable(cacheName=CacheUtils.USUARIO)
	public Usuario read(Long id) {
		return getHibernateTemplate().get(Usuario.class, id);
	}

	@Override
	@Cacheable(cacheName=CacheUtils.USUARIOS)
	public List<Usuario> readAll() {
		return getHibernateTemplate().loadAll(Usuario.class);
	}

	@Transactional
	@Override
	@TriggersRemove(cacheName=CacheUtils.USUARIOS, when=When.AFTER_METHOD_INVOCATION,removeAll=true)
	public void delete(Long id) {
		if(id == null)
			return;
		
		Usuario todelete = read(id);
		getHibernateTemplate().delete(todelete);
	}
	
	public Usuario getByName(String login) {
		Criteria q = getSession().createCriteria(Usuario.class);
		q.add(Restrictions.eq("login", login));
		
		return (Usuario) q.uniqueResult();
	}
	
	public Usuario getByNome(String nome)
	{
		Criteria q = getSession().createCriteria(Usuario.class);
		q.add(Restrictions.eq("nome", nome));
		
		return (Usuario) q.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> getUsuariosLimit(String nome) {
		Criteria c = getSession().createCriteria(Usuario.class);
		c.add(Restrictions.ilike("nome", nome, MatchMode.ANYWHERE));
		c.setMaxResults(5);
		
		return c.list();
	}
}

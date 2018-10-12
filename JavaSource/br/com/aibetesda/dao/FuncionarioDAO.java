package br.com.aibetesda.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.aibetesda.dao.especificos.IFuncionarioDAO;
import br.com.aibetesda.modelos.Funcionario;
import br.com.aibetesda.util.CacheUtils;

import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.TriggersRemove;
import com.googlecode.ehcache.annotations.When;

@Repository(value=DAOUtils.FUNCIONARIO)
public class FuncionarioDAO extends HibernateAibetesdaDAO implements IFuncionarioDAO{
	
	@Transactional
	@Override
	@TriggersRemove(cacheName=CacheUtils.FUNCIONARIOS, when=When.AFTER_METHOD_INVOCATION,removeAll=true)
	public Funcionario create(Funcionario funcionario) throws Exception {
		getHibernateTemplate().saveOrUpdate(funcionario);
		return funcionario;
	}

	@Transactional
	@Override
	@TriggersRemove(cacheName=CacheUtils.FUNCIONARIOS, when=When.AFTER_METHOD_INVOCATION,removeAll=true)
	public void delete(Long id) {
		if (id == null)
			return;
		Funcionario toDelete = read(id);
		getHibernateTemplate().delete(toDelete);
	}

	@Override
	@Cacheable(cacheName=CacheUtils.FUNCIONARIO)
	public Funcionario read(Long id) {
		Funcionario funcionario = getHibernateTemplate().get(Funcionario.class, id);
		return funcionario;
	}

	@Override
	@Cacheable(cacheName=CacheUtils.FUNCIONARIOS)
	public List<Funcionario> readAll() {
		return getHibernateTemplate().loadAll(Funcionario.class);
	}
	
	@Override
	public Funcionario getPorNome(String nome) {
		Criteria c = getSession().createCriteria(Funcionario.class);
		c.add(Restrictions.eq("nome", nome));
		
		return (Funcionario) c.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Funcionario> getFuncionarioLimit(String nome) {
		Criteria c = getSession().createCriteria(Funcionario.class);
		c.add(Restrictions.ilike("nome", nome, MatchMode.ANYWHERE));
		c.setMaxResults(5);
		
		return c.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Funcionario> getProfessorLimit(String nome) {
		Criteria c = getSession().createCriteria(Funcionario.class);
		c.add(Restrictions.ilike("nome", nome, MatchMode.ANYWHERE));
		c.add(Restrictions.eq("professor", true));
		c.setMaxResults(5);
		
		return c.list();
	}
	
	
}

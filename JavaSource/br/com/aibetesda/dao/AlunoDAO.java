package br.com.aibetesda.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.aibetesda.dao.especificos.IAlunoDAO;
import br.com.aibetesda.modelos.Aluno;
import br.com.aibetesda.modelos.AlunoTurmaPk;
import br.com.aibetesda.modelos.Matricula;
import br.com.aibetesda.modelos.Pedagogico;
import br.com.aibetesda.modelos.Turma;
import br.com.aibetesda.util.CacheUtils;

import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.TriggersRemove;
import com.googlecode.ehcache.annotations.When;

@Repository(value=DAOUtils.ALUNO)
public class AlunoDAO extends HibernateAibetesdaDAO implements IAlunoDAO{

	@Transactional
	@TriggersRemove(cacheName={CacheUtils.ALUNO, CacheUtils.ALUNOS},when=When.AFTER_METHOD_INVOCATION,removeAll=true)
	public Aluno create(Aluno newInstance) throws DataAccessException {
		if(newInstance.getId() != null)
			getHibernateTemplate().merge(newInstance);
		else
			getHibernateTemplate().saveOrUpdate(newInstance);
		
		return newInstance;
	}

	@Transactional
	@Override
	@TriggersRemove(cacheName={CacheUtils.ALUNO, CacheUtils.ALUNOS},when=When.AFTER_METHOD_INVOCATION,removeAll=true)
	public void delete(Long id) {
		if (id == null)
			return;

		Aluno todelete = read(id);
		getHibernateTemplate().delete(todelete);
	}

	@Override
	@Cacheable(cacheName=CacheUtils.ALUNO)
	public Aluno read(Long id) {
		Aluno a = getHibernateTemplate().get(Aluno.class, id);	
		Hibernate.initialize(a.getAlunoTurma());
		Hibernate.initialize(a.getPedagogico().getContatos());
		Hibernate.initialize(a.getPedagogico().getAtrasos());
		
		for(Matricula m : a.getAlunoTurma()){
			Hibernate.initialize(m.getPk().getTurma().getAlunoTurma());
		}
		
		return a;
	}

	@Override
	@Cacheable(cacheName=CacheUtils.ALUNOS)
	public List<Aluno> readAll() {
		return getHibernateTemplate().loadAll(Aluno.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Aluno> getByNameLimit(String nome) {
		Criteria c = getSession().createCriteria(Aluno.class);
		c.add(Restrictions.ilike("nome", nome+"%", MatchMode.ANYWHERE));
		
		return c.list();
	}

	@Override
	public Aluno getByName(String nome) {
		Criteria c = getSession().createCriteria(Aluno.class);
		c.add(Restrictions.eq("nome", nome));
		
		return (Aluno) c.uniqueResult();
	}
	
	@Override
	public Aluno getByPedagogico(Pedagogico pedagogico) {
		Criteria c = getSession().createCriteria(Aluno.class);
		c.add(Restrictions.eq("pedagogico", pedagogico));
		
		return (Aluno) c.uniqueResult();
	}
	
	@Override
	public Matricula getMatricula(Long id){
		Criteria c = getSession().createCriteria(Matricula.class);
		c.add(Restrictions.eq("matricula", id));
		
		return (Matricula) c.uniqueResult();
	}
	
	@Transactional
	@TriggersRemove(cacheName={CacheUtils.ALUNO, CacheUtils.ALUNOS},when=When.AFTER_METHOD_INVOCATION,removeAll=true)
	public Matricula salvarMatricula(Matricula mat) throws Exception {
		getHibernateTemplate().saveOrUpdate(mat);
		return mat;
	}
	
	@Override
	@Transactional
	@TriggersRemove(cacheName={CacheUtils.ALUNO, CacheUtils.ALUNOS},when=When.AFTER_METHOD_INVOCATION,removeAll=true)
	public AlunoTurmaPk transferirAluno(Turma turma, Matricula mat) {
		String query = "update matricula set turma_id = "+turma.getId()+" where matricula = "+mat.getMatricula()+"; ";
		System.out.println("transferencia de turma do aluno. SQL: "+query);
		getSession().createSQLQuery(query).executeUpdate();
		
		return null;
	}


}

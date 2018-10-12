package br.com.aibetesda.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.aibetesda.dao.especificos.ITurmaDAO;
import br.com.aibetesda.modelos.Curso;
import br.com.aibetesda.modelos.SalaAula;
import br.com.aibetesda.modelos.Turma;

@Repository(value=DAOUtils.TURMA)
public class TurmaDAO extends HibernateAibetesdaDAO implements ITurmaDAO {
	
	@Override
	@Transactional
	public Turma create(Turma turma) throws Exception {
		if(turma.getId() == null)
			turma.setNumeroVagasDisp(turma.getNumeroVagas());
		
		getHibernateTemplate().saveOrUpdate(turma);
		return turma;
	}

	@Transactional
	public void delete(Long id) {
		Turma turma = read(id);
//		String query = "delete from matricula_aud where turma_id = :id";
//		getSession().createSQLQuery(query).setParameter("id", turma.getId()).executeUpdate();
		getHibernateTemplate().delete(turma);
	}

	@Override
	public Turma read(Long id) {
		Turma t = getHibernateTemplate().get(Turma.class, id);		
		return t;
	}

	@Override
	public List<Turma> readAll() {
		return getHibernateTemplate().loadAll(Turma.class);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Turma> getTurmasAbertas() {
		Criteria c = getSession().createCriteria(Turma.class);
		c.add(Restrictions.gt("numeroVagas", 0));
		
		return c.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Turma> getTurmasAbertasCurso(Curso curso) {
		Criteria c = getSession().createCriteria(Turma.class);
		c.add(Restrictions.gt("numeroVagas", 0));
		c.add(Restrictions.eq("curso", curso));
		
		return c.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Turma> getTurmasSala(SalaAula sala) {
		Criteria c = getSession().createCriteria(Turma.class);
		c.add(Restrictions.eq("sala", sala));
		
		return c.list();
	}

}

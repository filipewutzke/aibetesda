package br.com.aibetesda.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.aibetesda.dao.especificos.ILancamentoAulaDAO;
import br.com.aibetesda.modelos.Aluno;
import br.com.aibetesda.modelos.AlunoPresenca;
import br.com.aibetesda.modelos.LancamentoAula;
import br.com.aibetesda.modelos.Turma;

@Repository(value=DAOUtils.LANCAMENTOAULA)
public class LancamentoAulaDAO extends HibernateAibetesdaDAO implements ILancamentoAulaDAO {

	@Override
	@Transactional
	public LancamentoAula create(LancamentoAula newInstance) throws Exception {
		if(newInstance.getId() != null)
			getHibernateTemplate().merge(newInstance);
		else
			getHibernateTemplate().saveOrUpdate(newInstance);
		return newInstance;
	}

	@Override
	public void delete(Long id) throws Exception {
		//não se deleta um lançamento de aula
	}

	@Override
	public LancamentoAula read(Long id) {
		return getHibernateTemplate().get(LancamentoAula.class, id);
	}

	@Override
	public List<LancamentoAula> readAll() {
		return getHibernateTemplate().loadAll(LancamentoAula.class);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<LancamentoAula> getLancamentoDataTurma(Date data, Turma turma) {
		Criteria c = getSession().createCriteria(LancamentoAula.class);
		
		c.add(Restrictions.eq("data", data)).add(Restrictions.eq("turma", turma));
		
		return (List<LancamentoAula>) c.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<LancamentoAula> getLancamentosData(Date data) {	
		Criteria c = getSession().createCriteria(LancamentoAula.class);
		c.add(Restrictions.eq("data", data));
		
		List<LancamentoAula> lancamentos = (List<LancamentoAula>) c.list();
		for (LancamentoAula lancamentoAula : lancamentos) {
			Hibernate.initialize(lancamentoAula.getAlunos());
		}
		return lancamentos;
	}
	
	
	@Override
	public LancamentoAula getLancamentosDataAluno(Date data, Aluno aluno) {
		Criteria c = getSession().createCriteria(LancamentoAula.class);
		c.add(Restrictions.eq("data", data));
		
		Criteria sub = c.createCriteria("alunos");
		sub.add(Restrictions.eq("aluno", aluno));
		
		return (LancamentoAula) sub.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<AlunoPresenca> getAlunosFaltaDia(Date dataInicial, Date dataFinal){
		
		Criteria c = getSession().createCriteria(AlunoPresenca.class);
		c.add(Restrictions.eq("presente", false));
		
		if (dataInicial != null && dataFinal != null)
			c.add(Restrictions.between("data", dataInicial, dataFinal));
		else
			if (dataInicial != null)
				c.add(Restrictions.ge("data", dataInicial));
			else
				if (dataFinal != null)
					c.add(Restrictions.le("data", dataFinal));
		c.addOrder(Order.asc("data"));
		
		return c.list();
	}
	
}

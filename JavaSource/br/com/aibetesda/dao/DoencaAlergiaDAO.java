package br.com.aibetesda.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.aibetesda.dao.especificos.IDoencaAlergiaDAO;
import br.com.aibetesda.modelos.DoencaAlergia;

@Repository(value=DAOUtils.DOENCAALERGIA)
public class DoencaAlergiaDAO extends HibernateAibetesdaDAO implements IDoencaAlergiaDAO{

	@Transactional
	@Override
	public DoencaAlergia create(DoencaAlergia newInstance) throws Exception {
		getHibernateTemplate().saveOrUpdate(newInstance);
		return newInstance;
	}

	@Override
	public DoencaAlergia read(Long id) {
		DoencaAlergia da = getHibernateTemplate().get(DoencaAlergia.class, id);
		return da;
	}

	@Override
	public List<DoencaAlergia> readAll() {
		return getHibernateTemplate().loadAll(DoencaAlergia.class);
	}
	
	@Transactional
	@Override
	public void delete(Long id) {
		if (id == null)
			return;
		
		DoencaAlergia todelete = read(id);
		getHibernateTemplate().delete(todelete);
	}
	
	@Override
	public DoencaAlergia getDoencaAlergiaPorNome(String nome) {
		Criteria c = getSession().createCriteria(DoencaAlergia.class);
		c.add(Restrictions.eq("nome", nome));
		
		return (DoencaAlergia) c.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<DoencaAlergia> getDoencaAlergiaQuery(String nome) {
		Criteria c = getSession().createCriteria(DoencaAlergia.class);
		c.add(Restrictions.ilike("nome", nome, MatchMode.ANYWHERE));
		c.setMaxResults(5);
		
		return c.list();
	}
	
	
}

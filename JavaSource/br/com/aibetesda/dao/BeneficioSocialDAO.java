package br.com.aibetesda.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.aibetesda.dao.especificos.IBeneficioSocialDAO;
import br.com.aibetesda.modelos.BeneficioSocial;

@Repository(value=DAOUtils.BENEFICIOSOCIAL)
public class BeneficioSocialDAO extends HibernateAibetesdaDAO implements IBeneficioSocialDAO{

	@Transactional
	@Override
	public BeneficioSocial create(BeneficioSocial newInstance) throws Exception {
		getHibernateTemplate().saveOrUpdate(newInstance);
		return newInstance;
	}

	@Override
	public BeneficioSocial read(Long id) {
		BeneficioSocial bs = getHibernateTemplate().get(BeneficioSocial.class, id);
		return bs;
	}

	@Override
	public List<BeneficioSocial> readAll() {
		return getHibernateTemplate().loadAll(BeneficioSocial.class);
	}
	
	@Transactional
	@Override
	public void delete(Long id) {
		if (id == null)
			return;
		
		BeneficioSocial todelete = read(id);
		getHibernateTemplate().delete(todelete);
	}
	
	@Override
	public BeneficioSocial getBeneficioPorNome(String nome) {
		Criteria c = getSession().createCriteria(BeneficioSocial.class);
		c.add(Restrictions.eq("nome", nome));
		
		return (BeneficioSocial) c.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<BeneficioSocial> getBeneficioQuery(String nome) {
		Criteria c = getSession().createCriteria(BeneficioSocial.class);
		c.add(Restrictions.ilike("nome", nome, MatchMode.ANYWHERE));
		c.setMaxResults(5);
		
		return c.list();
	}

}

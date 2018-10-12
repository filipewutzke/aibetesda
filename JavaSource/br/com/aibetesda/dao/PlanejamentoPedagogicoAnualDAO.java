package br.com.aibetesda.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.aibetesda.dao.especificos.IPlanejamentoPedagogicoAnualDAO;
import br.com.aibetesda.modelos.PlanejamentoAnualDatasReuniao;
import br.com.aibetesda.modelos.PlanejamentoPedagogicoAnual;

@Repository (value = DAOUtils.PLANEJAMENTOPEDAGOGICOANUAL)
public class PlanejamentoPedagogicoAnualDAO extends HibernateAibetesdaDAO implements IPlanejamentoPedagogicoAnualDAO{

	@Override
	@Transactional
	public PlanejamentoPedagogicoAnual create(PlanejamentoPedagogicoAnual ppa) throws Exception {
		getHibernateTemplate().saveOrUpdate(ppa);
		return ppa;
	}

	@Override
	@Transactional
	public void delete(Long id) throws Exception {
		if (id == null)
			return;
		PlanejamentoPedagogicoAnual todelete = read(id);
		getHibernateTemplate().delete(todelete);
	}

	@Override
	public PlanejamentoPedagogicoAnual read(Long id) {
		PlanejamentoPedagogicoAnual ppa = getHibernateTemplate().get(PlanejamentoPedagogicoAnual.class, id);
		return ppa;
	}

	@Override
	public List<PlanejamentoPedagogicoAnual> readAll() {
		return getHibernateTemplate().loadAll(PlanejamentoPedagogicoAnual.class);
	}

	@Override
	public PlanejamentoPedagogicoAnual getPlanejamentoPorAno(String ano){
		Criteria c = getSession().createCriteria(PlanejamentoPedagogicoAnual.class);
		c.add(Restrictions.eq("ano", ano));
		
		return (PlanejamentoPedagogicoAnual) c.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<PlanejamentoAnualDatasReuniao> getAvisoDatasReunioes(){
		Integer dias = 15;  
		Date dataAlterada = new Date();
		           
		GregorianCalendar gc = new GregorianCalendar();     
		gc.setTime(dataAlterada);     
		gc.set(Calendar.DATE, gc.get(Calendar.DATE) + dias);     
		dataAlterada = gc.getTime();  
		        
		Criteria c = getSession().createCriteria(PlanejamentoAnualDatasReuniao.class);
		c.add(Restrictions.between("dataReuniao", new Date(), dataAlterada));
				
		return c.list();
	}
	
	@Override
	public PlanejamentoPedagogicoAnual getReuniaoPorAno(Long id) {
		Criteria criteria = getSession().createCriteria(PlanejamentoAnualDatasReuniao.class);
		criteria.add(Restrictions.eq("id", id));
		
		PlanejamentoAnualDatasReuniao planeDatasReuniao = (PlanejamentoAnualDatasReuniao) criteria.uniqueResult();
		return planeDatasReuniao.getPlanejamento();
	}

}

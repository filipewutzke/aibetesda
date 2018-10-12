package br.com.aibetesda.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.aibetesda.dao.especificos.ILembreteDAO;
import br.com.aibetesda.modelos.Lembrete;
import br.com.aibetesda.util.UserUtils;

@Repository (value = DAOUtils.LEMBRETE)
public class LembreteDAO extends HibernateAibetesdaDAO implements ILembreteDAO{

	@Override
	@Transactional
	public Lembrete create(Lembrete lembrete) throws Exception {
		getHibernateTemplate().saveOrUpdate(lembrete);
		return lembrete;
	}

	@Override
	@Transactional
	public void delete(Long id) throws Exception {
		if (id == null)
			return;
		Lembrete todelete = read(id);
		getHibernateTemplate().delete(todelete);
	}

	@Override
	public Lembrete read(Long id) {
		Lembrete l = getHibernateTemplate().get(Lembrete.class, id);
		return l;
	}

	@Override
	public List<Lembrete> readAll() {
		return getHibernateTemplate().loadAll(Lembrete.class);
	}

	@SuppressWarnings("unchecked")
	public List<Lembrete> getAvisoDatas() {
		Integer dias = 15;  
        Date dataAlterada = new Date();
           
        GregorianCalendar gc = new GregorianCalendar();     
        gc.setTime(dataAlterada);     
        gc.set(Calendar.DATE, gc.get(Calendar.DATE) + dias);     
        dataAlterada = gc.getTime();  
        
        Criteria c = getSession().createCriteria(Lembrete.class);
		c.add(Restrictions.between("dataReuniao", new Date(), dataAlterada));
		c.add(Restrictions.eq("usuarioAlteracao", UserUtils.getUsuarioLogado()));
		
		return c.list();
	}

	@Override
	public Lembrete getReuniaoID(Long id) {
		Criteria criteria = getSession().createCriteria(Lembrete.class);
		criteria.add(Restrictions.eq("id", id));
		
		Lembrete dtReuniao = (Lembrete) criteria.uniqueResult();
		
		return dtReuniao;
	}
	
}
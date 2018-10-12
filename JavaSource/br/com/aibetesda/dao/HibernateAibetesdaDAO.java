package br.com.aibetesda.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class HibernateAibetesdaDAO extends HibernateDaoSupport {

	@Autowired
	public void init(SessionFactory localSessionFactoryBean) {
		setSessionFactory(localSessionFactoryBean);
	}
	
}

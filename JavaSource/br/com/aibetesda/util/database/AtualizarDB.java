package br.com.aibetesda.util.database;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaUpdate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import br.com.aibetesda.factory.AibetesdaFactoryBean;

public class AtualizarDB {
	
	public static void main(String[] args) {
		ApplicationContext path = 
			new FileSystemXmlApplicationContext(
					System.getProperty("user.dir") + "/WebContent/WEB-INF/applicationContext-hibernate.xml");
		
		AibetesdaFactoryBean sf = (AibetesdaFactoryBean) path.getBean("&sessionFactory");
		AnnotationConfiguration cfg = (AnnotationConfiguration) sf.getConfiguration();
		
		SchemaUpdate se = new SchemaUpdate(cfg);
		se.execute(true, true);
	}
	
}

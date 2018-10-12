package br.com.aibetesda.util.database;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import br.com.aibetesda.factory.AibetesdaFactoryBean;


public class GerarDB {
	
	public static void main(String[] args) {
		ApplicationContext path = 
			new FileSystemXmlApplicationContext(
					System.getProperty("user.dir") + "/WebContent/WEB-INF/applicationContext-hibernate.xml");
		
		AibetesdaFactoryBean sf = (AibetesdaFactoryBean) path.getBean("&sessionFactory");
		AnnotationConfiguration cfg = (AnnotationConfiguration) sf.getConfiguration();
		
		SchemaExport se = new SchemaExport(cfg);
		se.create(true, true);
	}
	
}

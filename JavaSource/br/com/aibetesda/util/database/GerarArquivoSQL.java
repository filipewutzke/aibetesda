package br.com.aibetesda.util.database;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import br.com.aibetesda.factory.AibetesdaFactoryBean;
import br.com.aibetesda.util.CaminhoUtils;

public class GerarArquivoSQL {
	
	public static void main(String[] args) {
		ApplicationContext path = 
			new FileSystemXmlApplicationContext(
					System.getProperty("user.dir") + "/WebContent/WEB-INF/applicationContext-hibernate.xml");
		
		AibetesdaFactoryBean sf = (AibetesdaFactoryBean) path.getBean("&sessionFactory");
		AnnotationConfiguration cfg = (AnnotationConfiguration) sf.getConfiguration();
		
		SchemaExport se = new SchemaExport(cfg);
		se.setOutputFile(CaminhoUtils.AIBETESDA_DB_SQL_FILE);
		se.create(true, false);
	}
	
}

package br.com.aibetesda.factory;

import java.util.ArrayList;

import javax.persistence.Embeddable;
import javax.persistence.Entity;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;

public class AibetesdaFactoryBean extends AnnotationSessionFactoryBean{
	
	//Lista dos pacotes que devem ser escaneados
	public static final String[] PACOTES_SCAN = new String[]{
			"br.com.aibetesda.modelos"};
	
	@Override
	protected SessionFactory buildSessionFactory() throws Exception {
		ArrayList<Class<?>> classes = new ArrayList<Class<?>>();
		
		ClassPathScanningCandidateComponentProvider scanner = 
			new ClassPathScanningCandidateComponentProvider(false);
		
		// Vai procurar por todas as classes com @Entity e @Embeddable (Endereço)
		scanner.addIncludeFilter(new AnnotationTypeFilter(Entity.class));
		scanner.addIncludeFilter(new AnnotationTypeFilter(Embeddable.class));

		//Para cada pacote descrito logo acima, o Spring fara o scanner das classes compativeis com as
		//anotações definidas
		for(String pacote : PACOTES_SCAN)
		{
			for (BeanDefinition bd : scanner.findCandidateComponents(pacote)) {
				try {
					String name = bd.getBeanClassName();
					classes.add(Class.forName(name));
				} catch (Exception e) {
					System.err.println(e);
				}
			}
		}
		//Aqui as classes encontradas são registradas como classes anotadas do hibernate
		setAnnotatedClasses(classes.toArray(new Class[classes.size()]));
		return super.buildSessionFactory();
	}
}

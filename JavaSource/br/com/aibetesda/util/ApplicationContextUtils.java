package br.com.aibetesda.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ApplicationContextUtils implements ApplicationContextAware {
	
	private static final Logger log = LoggerFactory.getLogger(ApplicationContextUtils.class);
	
	private static ApplicationContext context;
	
	@Override
	public void setApplicationContext(ApplicationContext contextoRecebido) throws BeansException {
		context = contextoRecebido;
		log.info("Recebendo contexto: "+context.getDisplayName());
	}
	
	public static ApplicationContext getContext() {
		return context;
	}

	public static Object getDAOBean(String dao){
		log.debug("Carregando bean "+dao);
		return getBean(dao);
	}
	
	public static Object getBean(String bean){
		return context.getBean(bean);
	}
}
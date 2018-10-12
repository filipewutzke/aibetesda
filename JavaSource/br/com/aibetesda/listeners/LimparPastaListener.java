package br.com.aibetesda.listeners;

import java.io.File;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.aibetesda.util.CaminhoUtils;

public class LimparPastaListener implements ServletContextListener {

	private static final Logger log = LoggerFactory.getLogger(LimparPastaListener.class);
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		log.info("Inicializando listener: "+LimparPastaListener.class.getName());
		
		File folder = new File(CaminhoUtils.CAMINHO_PASTA_AIBETESDA);
		File[] files = folder.listFiles();
		for(File f : files)
		{
			if(!f.delete())
				log.info("Arquivo "+f.getName()+" não pode ser deletado. Está em uso?");
		}
	}

}

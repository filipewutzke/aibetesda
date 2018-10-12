package br.com.aibetesda.util;

import java.io.File;

public class CaminhoUtils {
	//Pacotes
	public static final String DEF_PACOTE_ANNOTATIONS = "br.com.aibetesda.annotations";
	
	//Variáveis únicas
	private static final String SEPARATOR = File.separator;
	private static final String LOG_FILE_NAME = "aibetesda.log";
	
	//Caminhos do sistema
	public static final String CAMINHO_PASTA_AIBETESDA = System.getProperty("user.home") + SEPARATOR + ".aibetesda" + SEPARATOR;
	public static final String CAMINHO_ARQUIVO_LOG = CAMINHO_PASTA_AIBETESDA + LOG_FILE_NAME;
	
	//Mesmo que CAMINHO_ARQUIVO_LOG, mas em forma de arquivo
	public static final File FILE_LOG = new File(CAMINHO_ARQUIVO_LOG);
	
	//Para enviar email
	public static final String MAIL_SERVICE = "classpath:br/com/aibetesda/mail-service.xml";
	
	//Para criar o banco
	public static final String AIBETESDA_DB_SQL_FILE = System.getProperty("user.dir") + 
		File.separatorChar + "aibetesda-db.sql";
}


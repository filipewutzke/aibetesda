package br.com.aibetesda.mail;

import java.io.File;
import java.io.IOException;

import javax.mail.MessagingException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import br.com.aibetesda.util.CaminhoUtils;

public class EnviarEmailScheduler {

	public void enviaEmail() {
		try {
			ApplicationContext context = new FileSystemXmlApplicationContext(CaminhoUtils.MAIL_SERVICE);
			SendMailService mailService = (SendMailService) context.getBean("mailService");
			
			String de = "fi_lipe_@hotmail.com";
			String para = "filipewutzke@gmail.com";
			String titulo = "Log do sistema Aibetesda";
			String conteudo = "Em anexo: log do sistema." + "e-mail: "+de;
			
			mailService.enviarEmail(de,para,titulo,conteudo);
			limparPasta(CaminhoUtils.FILE_LOG.getParentFile().getPath());
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("ERRO: "+e.getMessage());
		}
	}

	
	private void limparPasta(String pasta) throws IOException
	{
		File file = new File(pasta);
		File[] arquivosDaPasta = file.listFiles();
		for(File f : arquivosDaPasta)
		{
			f.delete();
		}
	}
}

package br.com.aibetesda.mail;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import br.com.aibetesda.util.CaminhoUtils;

@Service("mailService")
public class SendMailService {

	@Autowired
	private JavaMailSenderImpl mailSender;

	public void enviarEmail(String de, String para, String titulo, String conteudo) throws MessagingException {
		
		MimeMessage mime = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mime, true);
		
		helper.setTo(para);
		helper.setFrom(de);
		helper.setSubject(titulo);
		helper.setText(conteudo);
		
		FileSystemResource file = new FileSystemResource(CaminhoUtils.FILE_LOG);
		helper.addAttachment("log.log", file);
		
		mailSender.send(mime);
	}
	
}

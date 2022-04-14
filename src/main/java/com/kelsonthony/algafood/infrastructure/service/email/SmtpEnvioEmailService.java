package com.kelsonthony.algafood.infrastructure.service.email;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.kelsonthony.algafood.core.email.EmailProperties;
import com.kelsonthony.algafood.domain.service.EnvioEmailService;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class SmtpEnvioEmailService implements EnvioEmailService {

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private EmailProperties emailProperties;

	@Autowired
	private Configuration freemarkerConfig;

	@Override
	public void enviar(Mensagem mensagem) {
		try {
			MimeMessage mimeMessage = criarMimeMessage(mensagem);

			mailSender.send(mimeMessage);
		} catch (Exception e) {
			throw new EmailException("Não foi possivel enviar e-mail", e);
		}
	}

	protected MimeMessage criarMimeMessage(Mensagem mensagem) throws MessagingException, TemplateException  {
		
		String corpo = processarTemplate(mensagem);
		
		MimeMessage mimeMessage = mailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
		helper.setFrom(emailProperties.getRemetente());
		helper.setTo(mensagem.getDestinatarios().toArray(new String[0]));
		helper.setSubject(mensagem.getAssunto());
		// helper.setText(mensagem.getCorpo(), true);
		helper.setText(corpo, true);

		mailSender.send(mimeMessage);

		return mimeMessage;
	}

	protected String processarTemplate(Mensagem mensagem) throws TemplateException {
		try {
			Template template = freemarkerConfig.getTemplate(mensagem.getCorpo());

			return FreeMarkerTemplateUtils.processTemplateIntoString(template,
					mensagem.getVariaveis());
		} catch (IOException e) {
			throw new EmailException("Não foi possivel montar o template do e-mail", e);

		}

	}

}

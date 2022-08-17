package com.kelsonthony.algafood.infrastructure.service.email;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.kelsonthony.algafood.domain.service.EnvioEmailService.Mensagem;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Component
public class ProcessadorEmailTemplate {
	
	@Autowired
	private Configuration freemarkerConfig;

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

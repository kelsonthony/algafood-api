package com.kelsonthony.algafood.infrastructure.service.email;

import org.springframework.beans.factory.annotation.Autowired;

import com.kelsonthony.algafood.domain.service.EnvioEmailService;

import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FakeEnvioEmailService implements EnvioEmailService {
	
	@Autowired
	private ProcessadorEmailTemplate processadorEmailTemplate;

	@Override
	public void enviar(Mensagem mensagem) {
		
		String corpo = "";
		try {
			corpo = processadorEmailTemplate.processarTemplate(mensagem);
	
		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		log.info("[FAKE E-MAIL] Para: {}\n{}", mensagem.getDestinatarios(), corpo);
		
	}
}

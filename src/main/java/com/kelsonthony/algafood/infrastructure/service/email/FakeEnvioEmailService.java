package com.kelsonthony.algafood.infrastructure.service.email;

import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FakeEnvioEmailService extends SmtpEnvioEmailService {

	@Override
	public void enviar(Mensagem mensagem) {
		
		String corpo = "";
		try {
			corpo = processarTemplate(mensagem);
	
		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		log.info("[FAKE E-MAIL] Para: {}\n{}", mensagem.getDestinatarios(), corpo);
		
	}
}

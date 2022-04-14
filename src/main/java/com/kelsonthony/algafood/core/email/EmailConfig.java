package com.kelsonthony.algafood.core.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.kelsonthony.algafood.domain.service.EnvioEmailService;
import com.kelsonthony.algafood.infrastructure.service.email.FakeEnvioEmailService;
import com.kelsonthony.algafood.infrastructure.service.email.SandboxEnvioEmailService;
import com.kelsonthony.algafood.infrastructure.service.email.SmtpEnvioEmailService;

@Configuration
public class EmailConfig {

	@Autowired
	private EmailProperties emailProperties;

	@Bean
	public EnvioEmailService envioEmailService() {

		switch (emailProperties.getImpl()) {
		case FAKE:
			return new FakeEnvioEmailService();
		case SMTP:
			return new SmtpEnvioEmailService();
		case SANDBOX:
			return new SandboxEnvioEmailService();
		default:
			return null;
		}
	}
}

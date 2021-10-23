package com.kelsonthony.algafood.di.notificacao;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.kelsonthony.algafood.di.modelo.Cliente;

@Profile("prod")
@TipoDoNotificador(NivelUrgencia.BaixaPrioridade)
@Component
public class NotificadorEmail implements Notificador {
	
	public NotificadorEmail() {
		System.out.println("Notificador email real");
	}
	
	@Override
	public void notificar(Cliente cliente, String mensagem) {
		
		System.out.printf("Notificação %s através do e-mail %s: %s\n",
				cliente.getNome(), cliente.getEmail(), mensagem);
	}

	
	
	
}

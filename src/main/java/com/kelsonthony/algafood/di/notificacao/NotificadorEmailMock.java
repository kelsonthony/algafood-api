package com.kelsonthony.algafood.di.notificacao;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.kelsonthony.algafood.di.modelo.Cliente;

@Profile("dev")
@TipoDoNotificador(NivelUrgencia.BaixaPrioridade)
@Component
public class NotificadorEmailMock implements Notificador {

	public NotificadorEmailMock() {
		System.out.println("Notificador email real Mock");
	}
	@Override
	public void notificar(Cliente cliente, String mensagem) {
		
		System.out.printf("MOCK: Notificação %s através do e-mail %s: %s\n",
				cliente.getNome(), cliente.getEmail(), mensagem);
	}

	
	
	
}

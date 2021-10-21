package com.kelsonthony.algafood.di.notificacao;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.kelsonthony.algafood.di.modelo.Cliente;

@Qualifier("urgente")
@Component
public class NotificadorSMS implements Notificador {
	

	@Override
	public void notificar(Cliente cliente, String mensagem) {
		
		System.out.printf("Notificação %s por SMS através do telefone %s: %s\n",
				cliente.getNome(), cliente.getTelefone(), mensagem);
	}

	
	
	
}

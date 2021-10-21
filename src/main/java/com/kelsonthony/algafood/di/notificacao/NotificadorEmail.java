package com.kelsonthony.algafood.di.notificacao;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.kelsonthony.algafood.di.modelo.Cliente;

//@Primary
@Qualifier("normal")
@Component
public class NotificadorEmail implements Notificador {
	
	@Override
	public void notificar(Cliente cliente, String mensagem) {
		
		System.out.printf("Notificação %s através do e-mail %s: %s\n",
				cliente.getNome(), cliente.getEmail(), mensagem);
	}

	
	
	
}

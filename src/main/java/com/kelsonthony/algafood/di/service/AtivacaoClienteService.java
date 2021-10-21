package com.kelsonthony.algafood.di.service;

import com.kelsonthony.algafood.di.modelo.Cliente;
import com.kelsonthony.algafood.di.notificacao.Notificador;

//@Component
public class AtivacaoClienteService {
	
	private Notificador notificador;
	
	public AtivacaoClienteService(Notificador notificador) {
		this.notificador = notificador;
		
		System.out.println("AtivacaoClienteService: " + notificador);
	}

	public void ativar(Cliente cliente) {
		cliente.ativar();
	
		notificador.notificar(cliente, "Seu cadastro está ativo");
		
	}
}

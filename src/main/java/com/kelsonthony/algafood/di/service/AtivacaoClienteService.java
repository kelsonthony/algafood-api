package com.kelsonthony.algafood.di.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.kelsonthony.algafood.di.modelo.Cliente;
import com.kelsonthony.algafood.di.notificacao.NivelUrgencia;
import com.kelsonthony.algafood.di.notificacao.Notificador;
import com.kelsonthony.algafood.di.notificacao.TipoDoNotificador;

//@Component
public class AtivacaoClienteService {

	@TipoDoNotificador(NivelUrgencia.BaixaPrioridade)
	@Autowired
	private Notificador notificador;
	
	//@PostConstruct
	public void init() {
		System.out.println("INIT " + notificador);
	}
	
	//@PreDestroy
	public void destroy() {
		System.out.println("DESTROY");
	}

	public void ativar(Cliente cliente) {
		cliente.ativar();

		notificador.notificar(cliente, "Seu cadastro está ativo");

	}

}

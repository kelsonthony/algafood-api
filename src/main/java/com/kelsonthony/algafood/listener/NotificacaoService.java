package com.kelsonthony.algafood.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.kelsonthony.algafood.di.notificacao.NivelUrgencia;
import com.kelsonthony.algafood.di.notificacao.Notificador;
import com.kelsonthony.algafood.di.notificacao.TipoDoNotificador;
import com.kelsonthony.algafood.di.service.ClienteAtivadoEvent;

@Component
public class NotificacaoService {
	
	@TipoDoNotificador(NivelUrgencia.BaixaPrioridade)
	@Autowired
	private Notificador notificador;

	@EventListener
	public void clienteAtivadoListener(ClienteAtivadoEvent event) {
		//System.out.println("Cliente: "+ event.getCliente().getNome() + " agora está ativo");
		notificador.notificar(event.getCliente(), "Seu cadastro está ativo");
	}
}

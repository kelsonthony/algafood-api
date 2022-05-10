package com.kelsonthony.algafood.domain.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import com.kelsonthony.algafood.domain.event.PedidoConfirmadoEvent;
import com.kelsonthony.algafood.domain.model.Pedido;
import com.kelsonthony.algafood.domain.service.EnvioEmailService;
import com.kelsonthony.algafood.domain.service.EnvioEmailService.Mensagem;

@Component
public class NotifacacaoClientePedidoConfirmadoListener {
	
	@Autowired
	private EnvioEmailService envioMail;

	@TransactionalEventListener //(phase = TransactionPhase.BEFORE_COMMIT)
	public void aoConfirmarPedido(PedidoConfirmadoEvent event) {
		//if(true) throw new IllegalAccessException();
		
		Pedido pedido = event.getPedido();
		
		var mensagem = Mensagem.builder()
				.assunto(pedido.getRestaurante().getNome() + " - Pedido confirmado")
				.corpo("pedido-confirmado.html")
				//.corpo("pedido de código <strong>" + pedido.getCodigo() + "</strong> foi confirmado")
				.variavel("pedido", pedido)
				.destinatario(pedido.getCliente().getEmail())
				.build();
		
		envioMail.enviar(mensagem);

	}
}

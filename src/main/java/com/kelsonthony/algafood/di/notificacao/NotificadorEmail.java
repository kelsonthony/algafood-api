package com.kelsonthony.algafood.di.notificacao;

import com.kelsonthony.algafood.di.modelo.Cliente;

//@Component
public class NotificadorEmail implements Notificador {
	
	private boolean caixaAlta;
	private String hostServidorSmtp;
	
	public NotificadorEmail(String hostServidorSmtp) {
		this.hostServidorSmtp = hostServidorSmtp;
		System.out.println("NotificadorEmail");
	}
	
	@Override
	public void notificar(Cliente cliente, String mensagem) {
		if( this.caixaAlta ) {
			mensagem = mensagem.toUpperCase();
		}
		
		System.out.printf("Notificação %s através do e-mail %s: usando SMTP %s %s\n",
				cliente.getNome(), cliente.getEmail(), this.hostServidorSmtp, mensagem);
	}

	public boolean isCaixaAlta() {
		return caixaAlta;
	}

	public void setCaixaAlta(boolean caixaAlta) {
		this.caixaAlta = caixaAlta;
	}
	
	
}

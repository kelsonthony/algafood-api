package com.kelsonthony.algafood.di.notificacao;

import com.kelsonthony.algafood.di.modelo.Cliente;

public interface Notificador {

	
	void notificar(Cliente cliente, String mensagem);

}
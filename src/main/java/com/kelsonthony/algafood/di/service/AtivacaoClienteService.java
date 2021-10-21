package com.kelsonthony.algafood.di.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.kelsonthony.algafood.di.modelo.Cliente;
import com.kelsonthony.algafood.di.notificacao.Notificador;

@Component
public class AtivacaoClienteService {

	//@Autowired(required = false)
	//@Autowired
	//private Notificador notificadores;
	//1 forma dentro de uma lista
	//private List<Notificador> notificadores;
	//segunda forma
	//@Qualifier("email")
	@Qualifier("urgente")
	@Autowired
	private Notificador notificador;
	/*
	 * @Autowired public AtivacaoClienteService(Notificador notificador) {
	 * this.notificador = notificador;
	 * 
	 * }
	 */

	/*
	 * public AtivacaoClienteService(String qualquer) {
	 * 
	 * }
	 */

	public void ativar(Cliente cliente) {
		cliente.ativar();

		notificador.notificar(cliente, "Seu cadastro está ativo");
		/*
		 * for (Notificador notificador : notificadores) {
		 * notificador.notificar(cliente, "Seu cadastro está ativo"); }
		 */
		/*
		 * if (notificador != null) { notificador.notificar(cliente,
		 * "Seu cadastro está ativo"); } else {
		 * System.out.println("Nâo existe notificador"); }
		 */

	}

	/*
	 * @Autowired public void setNotificador(Notificador notificador) {
	 * this.notificador = notificador; }
	 */

}

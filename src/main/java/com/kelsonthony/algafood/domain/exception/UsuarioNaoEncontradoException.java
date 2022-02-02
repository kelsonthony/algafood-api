package com.kelsonthony.algafood.domain.exception;

public class UsuarioNaoEncontradoException extends EntidadeNaoEncontradaException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UsuarioNaoEncontradoException(String mensagem) {
		super(mensagem);
		// TODO Auto-generated constructor stub
	}

	public UsuarioNaoEncontradoException(Long usuarioId) {
		this(String.format("Nâo existe um usuario de cozinha com o código %d", usuarioId));
	}

}

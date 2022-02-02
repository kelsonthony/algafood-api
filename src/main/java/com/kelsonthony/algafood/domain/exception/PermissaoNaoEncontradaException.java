package com.kelsonthony.algafood.domain.exception;

public class PermissaoNaoEncontradaException extends EntidadeNaoEncontradaException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PermissaoNaoEncontradaException(String mensagem) {
		super(mensagem);
		// TODO Auto-generated constructor stub
	}
	
	public PermissaoNaoEncontradaException(Long permissaoId) {
		this(String.format("Nâo existe um cadastro de permissão com o código %d", permissaoId));
	}

}

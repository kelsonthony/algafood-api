package com.kelsonthony.algafood.domain.exception;

public class FotoProdutoNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;
	
	public FotoProdutoNaoEncontradaException(String mensagem) {
		super(mensagem);
		// TODO Auto-generated constructor stub
	}

	public FotoProdutoNaoEncontradaException(Long restauranteId, Long produtoId) {
		this(String.format("Nâo existe um cadastro de foto do produto com o código %d para o restaurante de código %d", 
				restauranteId, produtoId));
	}

}

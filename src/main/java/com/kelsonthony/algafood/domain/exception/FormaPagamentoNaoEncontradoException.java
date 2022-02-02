package com.kelsonthony.algafood.domain.exception;

public class FormaPagamentoNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public FormaPagamentoNaoEncontradoException(String mensagem) {
		super(mensagem);
		// TODO Auto-generated constructor stub
	}

	public FormaPagamentoNaoEncontradoException(Long pagamentoId) {
		this(String.format("Não existe uma forma de pagamento com o código %d", pagamentoId));
	}

}

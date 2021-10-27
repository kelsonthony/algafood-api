package com.kelsonthony.algafood.domain.repository;

import java.util.List;

import com.kelsonthony.algafood.domain.model.FormaPagamento;



public interface FormaPagamentoRepository {

	List<FormaPagamento> todos();
	FormaPagamento buscar(Long id);
	FormaPagamento salvar(FormaPagamento formaPagamento);
	void remover(FormaPagamento formaPagamento);
}

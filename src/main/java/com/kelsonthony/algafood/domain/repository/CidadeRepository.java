package com.kelsonthony.algafood.domain.repository;

import java.util.List;

import com.kelsonthony.algafood.domain.model.Cidade;

public interface CidadeRepository {

	List<Cidade> todos();
	Cidade buscar(Long id);
	Cidade salvar(Cidade cidade);
	void remover(Cidade cidade);
}

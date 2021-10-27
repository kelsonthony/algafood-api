package com.kelsonthony.algafood.domain.repository;

import java.util.List;

import com.kelsonthony.algafood.domain.model.Cozinha;

public interface CozinhaRepository {

	List<Cozinha> todas();
	Cozinha buscar(Long id);
	Cozinha salvar(Cozinha cozinha);
	void remover(Cozinha cozinha);
}

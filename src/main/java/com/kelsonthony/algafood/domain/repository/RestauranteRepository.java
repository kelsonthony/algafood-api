package com.kelsonthony.algafood.domain.repository;

import java.util.List;

import com.kelsonthony.algafood.domain.model.Restaurante;

public interface RestauranteRepository {

	List<Restaurante> todos();
	Restaurante buscar(Long Id);
	Restaurante salvar(Restaurante restaurante);
	void remover(Long Id);
}

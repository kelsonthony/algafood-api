package com.kelsonthony.algafood.domain.repository;

import java.util.List;

import com.kelsonthony.algafood.domain.model.Estado;

public interface EstadoRepository {

	List<Estado> todos();
	Estado buscar(Long id);
	Estado salvar(Estado estado);
	void remover(Estado estado);
}

package com.kelsonthony.algafood.domain.repository;

import java.util.List;

import com.kelsonthony.algafood.domain.model.Permissao;

public interface PermissaoRepository {

	List<Permissao> todas();
	Permissao buscar(Long id);
	Permissao salvar(Permissao permissao);
	void remover(Permissao permissao);
}

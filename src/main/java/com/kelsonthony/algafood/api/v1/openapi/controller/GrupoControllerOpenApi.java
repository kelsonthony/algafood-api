package com.kelsonthony.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.kelsonthony.algafood.api.v1.model.GrupoModel;
import com.kelsonthony.algafood.api.v1.model.input.GrupoInput;

public interface GrupoControllerOpenApi {

	CollectionModel<GrupoModel> listar();

	GrupoModel buscar(

			Long grupoId);

	GrupoModel adicionar(

			GrupoInput grupoInput);

	GrupoModel atualizar(

			Long grupoId,

			GrupoInput grupoInput);

	void remover(

			Long grupoId);
}

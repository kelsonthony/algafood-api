package com.kelsonthony.algafood.api.v2.openapi.controller;

import javax.validation.Valid;

import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.kelsonthony.algafood.api.v2.model.CidadeModelV2;
import com.kelsonthony.algafood.api.v2.model.input.CidadeInputV2;


public interface CidadeControllerV2OpenApi {

	public CollectionModel<CidadeModelV2> listar();

	public CidadeModelV2 buscar(@PathVariable Long cidadeId);

	public CidadeModelV2 adicionar(@RequestBody @Valid CidadeInputV2 cidadeInput);

	public CidadeModelV2 atualizar(@PathVariable Long cidadeId,
			@RequestBody @Valid CidadeInputV2 cidadeInput);

	public void remover(@PathVariable Long cidadeId);
}

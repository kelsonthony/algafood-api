package com.kelsonthony.algafood.api.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.kelsonthony.algafood.api.model.PermissaoModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Permissões")
public interface PermissaoControllerOpenApi {

	@ApiOperation("Lista as Permissões")
	CollectionModel<PermissaoModel> listar();
}

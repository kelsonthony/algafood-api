package com.kelsonthony.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.kelsonthony.algafood.api.v1.model.PermissaoModel;


public interface PermissaoControllerOpenApi {


	CollectionModel<PermissaoModel> listar();
}

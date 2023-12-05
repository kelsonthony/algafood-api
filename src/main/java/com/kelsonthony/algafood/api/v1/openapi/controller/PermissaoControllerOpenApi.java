package com.kelsonthony.algafood.api.v1.openapi.controller;

import com.kelsonthony.algafood.api.v1.model.PermissaoModel;
import org.springframework.hateoas.CollectionModel;

//@Api(tags = "Permissões")
public interface PermissaoControllerOpenApi {

	//@ApiOperation("Lista as Permissões")
	CollectionModel<PermissaoModel> listar();
}

package com.kelsonthony.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.kelsonthony.algafood.api.v1.model.UsuarioModel;

public interface RestauranteUsuarioResponsavelControllerOpenApi {

	CollectionModel<UsuarioModel> listar(

			Long restauranteId);

	ResponseEntity<Void> associar(

			Long restauranteId,

			Long usuarioId);

	ResponseEntity<Void> desassociar(

			Long restauranteId,

			Long usuarioId);
}

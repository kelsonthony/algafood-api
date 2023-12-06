package com.kelsonthony.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.kelsonthony.algafood.api.v1.model.EstadoModel;
import com.kelsonthony.algafood.api.v1.model.input.EstadoInput;

public interface EstadoControllerOpenApi {

	CollectionModel<EstadoModel> listar();

	EstadoModel buscar(

			Long estadoId);

	EstadoModel adicionar(

			EstadoInput estadoInput);

	EstadoModel atualizar(

			Long estadoId,

			EstadoInput estadoInput);

	void remover(

			Long estadoId);
}

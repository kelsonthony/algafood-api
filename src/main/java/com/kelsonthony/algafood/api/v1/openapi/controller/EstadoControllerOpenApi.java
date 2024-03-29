package com.kelsonthony.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.kelsonthony.algafood.api.exceptionhandler.Problem;
import com.kelsonthony.algafood.api.v1.model.EstadoModel;
import com.kelsonthony.algafood.api.v1.model.input.EstadoInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Estados")
public interface EstadoControllerOpenApi {

	@ApiOperation(value = "Lista os estados")
	CollectionModel<EstadoModel> listar();

	@ApiOperation(value = "Busca um estado por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID do estado inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Estado não encontrado", response = Problem.class)
	})
	EstadoModel buscar(
			@ApiParam(value = "ID do estado", example = "1", required = true)
			Long estadoId);

	@ApiOperation(value = "Adiciona um estado")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Estado Cadastrado"),
	})
	EstadoModel adicionar(
			@ApiParam(name = "corpo", value = "Representação de um novo estado", required = true)
			EstadoInput estadoInput);

	@ApiOperation(value = "Atualiza um estado por ID")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Estado Atualizado"),
		@ApiResponse(code = 400, message = "ID do estado inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Estado não encontrado", response = Problem.class)
	})
	EstadoModel atualizar(
			@ApiParam(value = "ID do estado", example = "1", required = true)
			Long estadoId, 
			@ApiParam(name = "corpo", value = "Representação de um estado atualizado", required = true)
			EstadoInput estadoInput);
	
	@ApiOperation(value = "Deleta um estado por ID")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Estado excluído"),
		@ApiResponse(code = 404, message = "Estado não encontrado", response = Problem.class)
	})
	void remover(
			@ApiParam(value = "ID do estado", example = "1", required = true)
			Long estadoId);
}

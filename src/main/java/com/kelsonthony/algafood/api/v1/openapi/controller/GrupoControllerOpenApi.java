package com.kelsonthony.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.kelsonthony.algafood.api.exceptionhandler.Problem;
import com.kelsonthony.algafood.api.v1.model.GrupoModel;
import com.kelsonthony.algafood.api.v1.model.input.GrupoInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Grupos")
public interface GrupoControllerOpenApi {
	
	@ApiOperation(value = "Lista os grupos cadastrados")
	CollectionModel<GrupoModel> listar();
	
	@ApiOperation(value = "Busca uma grupo por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message ="ID do grupo inválido", response = Problem.class),
		@ApiResponse(code = 404, message ="Grupo não encontrado", response = Problem.class)
	})
	GrupoModel buscar(
			@ApiParam(value = "ID de um grupo", example = "1", required = true)
			Long grupoId);
	
	@ApiOperation(value = "Cadastrar um novo grupo")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Grupo Cadastrado")
	})
	GrupoModel adicionar(
			@ApiParam(name = "corpo", value = "Representação de um grupo", required = true)
			GrupoInput grupoInput);
	
	
	@ApiOperation(value = "Atualiza um grupo por ID")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Grupo Atualizado"),
		@ApiResponse(code = 404, message = "Grupo não encontrado", response = Problem.class)
	})
	GrupoModel atualizar(
			@ApiParam(value = "ID de um grupo", example = "1", required = true)
			Long grupoId, 
			
			@ApiParam(name = "corpo", value = "Representação de um grupo com novos dados", required = true)
			GrupoInput grupoInput);
	
	@ApiOperation(value = "Deleta um grupo por ID")
	@ApiResponses({
		@ApiResponse(code = 204, message ="Grupo Excluído"),
		@ApiResponse(code = 404, message ="Grupo não encontrado", response = Problem.class)
	})
	void remover(
			@ApiParam(value = "ID de um grupo", example = "1", required = true) 
			Long grupoId);
}


















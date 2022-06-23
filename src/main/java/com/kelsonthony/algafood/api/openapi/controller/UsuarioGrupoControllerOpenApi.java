package com.kelsonthony.algafood.api.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.kelsonthony.algafood.api.exceptionhandler.Problem;
import com.kelsonthony.algafood.api.model.GrupoModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Usuários")
public interface UsuarioGrupoControllerOpenApi {

	@ApiOperation(value = "Lista os grupos associados a um usuário")
	@ApiResponses({
		@ApiResponse(code = 400, message ="ID do usuário inválido", response = Problem.class),
		@ApiResponse(code = 404, message ="Usuário não encontrado", response = Problem.class)
	})
	CollectionModel<GrupoModel> listar(
			@ApiParam(value = "ID de um usuário", example = "1", required = true)
			Long usuarioId);

	@ApiOperation(value = "Desassocia um usuário de um grupo")
	@ApiResponses({
		@ApiResponse(code = 204, message ="Desassociação realizada com sucesso", response = Problem.class),
		@ApiResponse(code = 400, message ="ID do usuário inválido", response = Problem.class),
		@ApiResponse(code = 404, message ="Usuário não encontrado", response = Problem.class)
	})
	ResponseEntity<Void> desassociar(
			@ApiParam(value = "ID de um usuário", example = "1", required = true)
			Long usuarioId,
			@ApiParam(value = "ID de um grupo", example = "1", required = true)
			Long grupoId);

	@ApiOperation(value = "Associa um usuário de um grupo")
	@ApiResponses({
		@ApiResponse(code = 204, message ="Associação realizada com sucesso", response = Problem.class),
		@ApiResponse(code = 400, message ="ID do usuário inválido", response = Problem.class),
		@ApiResponse(code = 404, message ="Usuário não encontrado", response = Problem.class)
	})
	ResponseEntity<Void> associar(
			@ApiParam(value = "ID de um usuário", example = "1", required = true)
			Long usuarioId,
			@ApiParam(value = "ID de um grupo", example = "1", required = true)
			Long grupoId);
}

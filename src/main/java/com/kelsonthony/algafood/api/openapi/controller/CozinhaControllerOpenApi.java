package com.kelsonthony.algafood.api.openapi.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

import com.kelsonthony.algafood.api.exceptionhandler.Problem;
import com.kelsonthony.algafood.api.model.CozinhaModel;
import com.kelsonthony.algafood.api.model.input.CozinhaInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Cozinhas")
public interface CozinhaControllerOpenApi {

	@ApiOperation(value = "Lista as cozinhas")
	PagedModel<CozinhaModel> listar(Pageable pageable); 

	@ApiOperation(value = "Busca uma cozinha por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message ="ID da cozinha inválido", response = Problem.class),
		@ApiResponse(code = 404, message ="Cozinha não encontrada", response = Problem.class)
	})
	CozinhaModel buscar(
			@ApiParam(value = "ID de uma cozinha", example = "1", required = true)
			Long cozinhaId);

	@ApiOperation(value = "Cadastra uma nova cozinha")
	@ApiResponses({
		@ApiResponse(code = 201, message ="Cozinha Cadatrada")
	})
	CozinhaModel adicionar(
			@ApiParam(name = "corpo", value = "Representação de uma nova cozinha", required = true)
			CozinhaInput cozinhaInput);
	
	@ApiOperation(value = "Atualiza uma cozinha por ID")
	@ApiResponses({
		@ApiResponse(code = 200, message ="Cozinha atualizada"),
		@ApiResponse(code = 404, message ="Cozinha não encontrada", response = Problem.class)
	})
	CozinhaModel atualizar(
			@ApiParam(value = "ID de uma cozinha", example = "1", required = true)
			Long cozinhaId, 
			
			@ApiParam(name = "corpo", value = "Representação de uma cozinha com novos dados")
			CozinhaInput cozinhaInput);
	
	@ApiOperation(value = "Deleta uma cozinha por ID")
	@ApiResponses({
		@ApiResponse(code = 204, message ="Cozinha excluída"),
		@ApiResponse(code = 404, message ="Cozinha não encontrada", response = Problem.class)
	})
	void remover(
			@ApiParam(value = "ID de uma cozinha", example = "1", required = true)
			Long cozinhaId);
}

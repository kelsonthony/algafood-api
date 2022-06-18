package com.kelsonthony.algafood.api.openapi.controller;

import java.util.List;

import com.kelsonthony.algafood.api.exceptionhandler.Problem;
import com.kelsonthony.algafood.api.model.ProdutoModel;
import com.kelsonthony.algafood.api.model.input.ProdutoInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Produtos")
public interface RestauranteProdutoControllerOpenApi {

	@ApiOperation(value = "Lista os produtos de um restaurante")
	@ApiResponses({
			@ApiResponse(code = 404, message = "Produto não encontrado", response = Problem.class) })
	List<ProdutoModel> listar(
			@ApiParam(value = "ID de um restaurante", example = "1", required = true) Long restauranteId,

			@ApiParam(value = "Indica se deve ou não incluir produtos inativos no resultado da listagem", example = "false", defaultValue = "false") boolean incluirInativos);

	@ApiOperation("Busca um produto de um restaurante")
	@ApiResponses({
			@ApiResponse(code = 400, message = "ID do restaurante ou produto inválido", response = Problem.class),
			@ApiResponse(code = 404, message = "Produto de restaurante não encontrado", response = Problem.class) })
	ProdutoModel buscar(
			@ApiParam(value = "ID do restaurante", example = "1", required = true) Long restauranteId,

			@ApiParam(value = "ID do produto", example = "1", required = true) Long produtoId);

	@ApiOperation("Cadastra um produto de um restaurante")
	@ApiResponses({ @ApiResponse(code = 201, message = "Produto cadastrado"),
			@ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class) })
	ProdutoModel adicionar(
			@ApiParam(value = "ID do restaurante", example = "1", required = true) Long restauranteId,
			@ApiParam(value = "ID do produto", example = "1", required = true) ProdutoInput produtoInput);

	@ApiOperation("Atualiza um produto de um restaurante")
	@ApiResponses({ 
		@ApiResponse(code = 200, message = "Produto atualizado"),
		@ApiResponse(code = 404, message = "Produto de restaurante não encontrado", response = Problem.class) })
	ProdutoModel atualizar(
			@ApiParam(value = "ID do restaurante", example = "1", required = true)
			Long restauranteId,
			
			 @ApiParam(value = "ID do produto", example = "1", required = true)
			Long produtoId,
			
			 @ApiParam(name = "corpo", value = "Representação de um produto com os novos dados", 
             required = true)
			ProdutoInput produtoInput);
}
package com.kelsonthony.algafood.api.openapi.controller;

import java.util.List;

import com.kelsonthony.algafood.api.controller.RestauranteBasicoOpenApi;
import com.kelsonthony.algafood.api.exceptionhandler.Problem;
import com.kelsonthony.algafood.api.model.RestauranteModel;
import com.kelsonthony.algafood.api.model.input.RestauranteInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Restaurantes")
public interface RestauranteControllerOpenApi {

	@ApiOperation(value = "Lista restaurantes", response = RestauranteBasicoOpenApi.class)
	@ApiImplicitParams({
		@ApiImplicitParam(value = "Nome da projeção de pedidos", 
				allowableValues = "apenas-nome", name = "projecao", 
				paramType = "query", type = "string")
	})
	List<RestauranteModel> listar();

	@ApiOperation(value = "Lista restaurantes", hidden = true)
	List<RestauranteModel> listarApenasNomes();

	@ApiOperation(value = "Busca um restaurante por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message ="ID do restaurante inválido", response = Problem.class),
		@ApiResponse(code = 404, message ="Restaurante não encontrado", response = Problem.class)
	})
	RestauranteModel buscar(
			@ApiParam(value = "ID de um restaurante", example = "1", required = true)
			Long restauranteId);
	
	@ApiOperation(value = "Cadastra um restaurante")
	@ApiResponses({
		@ApiResponse(code = 201, message ="Restaurante Cadastrado")
	})
	RestauranteModel adicionar(
			@ApiParam(name = "corpo", value = "Representação de um novo restaurante", required = true)
			RestauranteInput restauranteInput);
	
	@ApiOperation(value = "Atualiza um restaurante por ID")
	@ApiResponses({
		@ApiResponse(code = 200, message ="Restaurante Atualizado"),
		@ApiResponse(code = 404, message ="Restaurante não encontrado", response = Problem.class)
	})
	RestauranteModel atualizar(
			@ApiParam(value = "ID de um restaurante", example = "1", required = true)
			Long restauranteId,
			
			@ApiParam(name = "corpo", value = "Representação de um restaurante com novos dados", required = true)
			RestauranteInput restauranteInput);
	
	@ApiOperation(value = "Ativa um restaurante por ID")
	@ApiResponses({
		@ApiResponse(code = 204, message ="Restaurante Ativado"),
		@ApiResponse(code = 404, message ="Restaurante não encontrado", response = Problem.class)
	})
	void ativar(
			@ApiParam(value = "ID de um restaurante", example = "1", required = true)
			Long restauranteId);

	@ApiOperation(value = "Inativar um restaurante por ID")
	@ApiResponses({
		@ApiResponse(code = 204, message ="Restaurante Inativado"),
		@ApiResponse(code = 404, message ="Restaurante não encontrado", response = Problem.class)
	})
	void inativar(
			@ApiParam(value = "ID de um restaurante", example = "1", required = true)
			Long restauranteId);

	@ApiOperation(value = "Deleta um restaurante por ID")
	@ApiResponses({
		@ApiResponse(code = 204, message ="Restaurante Excluído"),
		@ApiResponse(code = 404, message ="Restaurante não encontrado", response = Problem.class)
	})
	void remover(
			@ApiParam(value = "ID de um restaurante", example = "1", required = true)
			Long restauranteId);
	
	@ApiOperation(value = "Abre um restaurante por ID")
	@ApiResponses({
		@ApiResponse(code = 204, message ="Restaurante Aberto"),
		@ApiResponse(code = 404, message ="Restaurante não encontrado", response = Problem.class)
	})
	void abrir(
			@ApiParam(value = "ID de um restaurante", example = "1", required = true)
			Long restauranteId);
	
	@ApiOperation(value = "Fecha um restaurante por ID")
	@ApiResponses({
		@ApiResponse(code = 204, message ="Restaurante Fechado"),
		@ApiResponse(code = 404, message ="Restaurante não encontrado", response = Problem.class)
	})
	void fechar(
			@ApiParam(value = "ID de um restaurante", example = "1", required = true)
			Long restauranteId);

	@ApiOperation(value = "Ativa múltiplos restaurante por IDs")
	@ApiResponses({
		@ApiResponse(code = 204, message ="Restaurantes ativados"),
		@ApiResponse(code = 404, message ="Restaurante não encontrado", response = Problem.class)
	})
	void ativarMultiplos(
			@ApiParam(value = "ID de um restaurante", example = "[1,2,3]", required = true)
			List<Long> restauranteIds);

	@ApiOperation(value = "Inativa múltiplos restaurante por IDs")
	@ApiResponses({
		@ApiResponse(code = 204, message ="Restaurantes inativados"),
		@ApiResponse(code = 404, message ="Restaurante não encontrado", response = Problem.class)
	})
	void inativarMultiplos(
			@ApiParam(value = "ID de um restaurante", example = "[1,2,3]", required = true)
			List<Long> restauranteIds);
}

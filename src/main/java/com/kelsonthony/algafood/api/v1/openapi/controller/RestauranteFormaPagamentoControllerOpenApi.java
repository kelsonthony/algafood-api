package com.kelsonthony.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.kelsonthony.algafood.api.exceptionhandler.Problem;
import com.kelsonthony.algafood.api.v1.model.FormaPagamentoModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Restaurantes")
public interface RestauranteFormaPagamentoControllerOpenApi {

	@ApiOperation(value = "Lista formas de pagamentos associadas")
	@ApiResponses({
		@ApiResponse(code = 400, message ="ID do restaurante inválido"),
		@ApiResponse(code = 404, message ="Restaurante não encontrado", response = Problem.class)
	})
	CollectionModel<FormaPagamentoModel> listar(
			@ApiParam(value = "ID de um restaurante", example = "1", required = true)
			Long restauranteId);
	
	@ApiOperation(value = "Remove forma de pagamento do restaurante")
	@ApiResponses({
		@ApiResponse(code = 400, message ="ID do restaurante inválido"),
		@ApiResponse(code = 404, message ="Restaurante não encontrado", response = Problem.class)
	})
	ResponseEntity<Void> desassociar(
			@ApiParam(value = "ID de um restaurante", example = "1", required = true)
			Long restauranteId,
			@ApiParam(value = "ID da forma de pagamento", example = "1", required = true)
			Long formaPagamentoId);
	
	@ApiOperation(value = "Adiciona forma de pagamento do restaurante")
	@ApiResponses({
		@ApiResponse(code = 400, message ="ID do restaurante inválido"),
		@ApiResponse(code = 404, message ="Restaurante não encontrado", response = Problem.class)
	})
	ResponseEntity<Void> associar(
			@ApiParam(value = "ID de um restaurante", example = "1", required = true)
			Long restauranteId,
			@ApiParam(value = "ID da forma de pagamento", example = "1", required = true)
			Long formaPagamentoId);
}

package com.kelsonthony.algafood.api.openapi.controller;

import java.util.List;

import com.kelsonthony.algafood.api.exceptionhandler.Problem;
import com.kelsonthony.algafood.api.model.UsuarioModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Restaurantes")
public interface RestauranteUsuarioResponsavelControllerOpenApi {

	@ApiOperation(value = "Lista os responsáveis pelo restaurante")
	@ApiResponses({
		@ApiResponse(code = 400, message ="ID do restaurante inválido"),
		@ApiResponse(code = 404, message ="Restaurante não encontrado", response = Problem.class)
	})
	List<UsuarioModel> listar(
			@ApiParam(value = "ID de um restaurante", example = "1", required = true)
			Long restauranteId);
	
	@ApiOperation("Associação de restaurante com usuário responsável")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Associação realizada com sucesso"),
        @ApiResponse(code = 404, message = "Restaurante ou usuário não encontrado", 
            response = Problem.class)
    })
	void associar(
			@ApiParam(value = "ID de um restaurante", example = "1", required = true)
			Long restauranteId,
			@ApiParam(value = "ID de um usuário", example = "1", required = true)
			Long usuarioId);
	
	 @ApiOperation("Desassociação de restaurante com usuário responsável")
	    @ApiResponses({
	        @ApiResponse(code = 204, message = "Desassociação realizada com sucesso"),
	        @ApiResponse(code = 404, message = "Restaurante ou usuário não encontrado", 
	            response = Problem.class)
	    })
	void deassociar(
			@ApiParam(value = "ID de um restaurante", example = "1", required = true)
			Long restauranteId,
			@ApiParam(value = "ID de um usuário", example = "1", required = true)
			Long usuarioId);
}

package com.kelsonthony.algafood.api.v1.openapi.controller;

import com.kelsonthony.algafood.api.v1.model.FotoProdutoModel;
import com.kelsonthony.algafood.api.v1.model.input.FotoProdutoInput;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

//@Api(tags = "Produtos")
public interface RestauranteProdutoFotoControllerOpenApi {

	//@ApiOperation(value = "Busca a foto do produto de um restaurante", produces = "application/json, image/jpeg, image/png")
	//@ApiResponses({
			//@ApiResponse(code = 400, message = "ID do restaurante ou produto inválido", response = Problem.class),
			//@ApiResponse(code = 404, message = "Foto do Produto não encontrado", response = Problem.class)
	//})
	FotoProdutoModel buscar(
			//@ApiParam(value = "ID do restaurante", example = "1", required = true)
			Long restauranteId, 
			//@ApiParam(value = "ID do produto", example = "1", required = true)
			Long produtoId);
	
	//@ApiOperation(value = "Busca a foto do produto de um restaurante", hidden = true)
	ResponseEntity<?> servirFoto(Long restauranteId, 
			Long produtoId, String acceptHeader) 
					throws HttpMediaTypeNotAcceptableException ;
	
	//@ApiOperation("Atualiza a foto do produto de um restaurante")
	//@ApiResponses({
			//@ApiResponse(code = 200, message = "Foto do produto atualizada"),
			//@ApiResponse(code = 404, message = "Foto do Produto não encontrado", response = Problem.class)
	//})
	FotoProdutoModel atualizarFoto(
			//@ApiParam(value = "ID do restaurante", example = "1", required = true)
			Long restauranteId, 
			//@ApiParam(value = "ID do produto", example = "1", required = true)
			Long produtoId,
			FotoProdutoInput fotoProdutoInput,
			
			//@ApiParam(value = "Arquivo da foto do produto (máximo 500KB, apenas JPG e PNG)", required = true)
			MultipartFile arquivo) throws IOException;
	
	//@ApiOperation("Exclui a foto do produto de um restaurante")
	//@ApiResponses({
			//@ApiResponse(code = 204, message = "Foto do produto excluída com sucesso"),
			//@ApiResponse(code = 400, message = "ID do restaurante ou produto inválido", response = Problem.class),
			//@ApiResponse(code = 404, message = "Foto do Produto não encontrado", response = Problem.class)
	//})
	void remover(
			//@ApiParam(value = "ID do restaurante", example = "1", required = true)
			Long restauranteId, 
			//@ApiParam(value = "ID do produto", example = "1", required = true)
			Long produtoId);
	}

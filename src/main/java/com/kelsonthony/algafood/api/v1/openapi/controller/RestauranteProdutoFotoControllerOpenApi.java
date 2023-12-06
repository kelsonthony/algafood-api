package com.kelsonthony.algafood.api.v1.openapi.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.multipart.MultipartFile;

import com.kelsonthony.algafood.api.v1.model.FotoProdutoModel;
import com.kelsonthony.algafood.api.v1.model.input.FotoProdutoInput;

public interface RestauranteProdutoFotoControllerOpenApi {

	FotoProdutoModel buscar(

			Long restauranteId,

			Long produtoId);

	ResponseEntity<?> servirFoto(Long restauranteId, Long produtoId, String acceptHeader)
			throws HttpMediaTypeNotAcceptableException;

	FotoProdutoModel atualizarFoto(

			Long restauranteId,

			Long produtoId, FotoProdutoInput fotoProdutoInput,

			MultipartFile arquivo) throws IOException;

	void remover(

			Long restauranteId,

			Long produtoId);
}

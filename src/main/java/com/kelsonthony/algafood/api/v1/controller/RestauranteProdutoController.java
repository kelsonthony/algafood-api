package com.kelsonthony.algafood.api.v1.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.kelsonthony.algafood.api.v1.assembler.ProdutoInputDisassembler;
import com.kelsonthony.algafood.api.v1.assembler.ProdutoModelAssembler;
import com.kelsonthony.algafood.api.v1.links.AlgaLinks;
import com.kelsonthony.algafood.api.v1.model.ProdutoModel;
import com.kelsonthony.algafood.api.v1.model.input.ProdutoInput;
import com.kelsonthony.algafood.api.v1.openapi.controller.RestauranteProdutoControllerOpenApi;
import com.kelsonthony.algafood.domain.model.Produto;
import com.kelsonthony.algafood.domain.model.Restaurante;
import com.kelsonthony.algafood.domain.repository.ProdutoRepository;
import com.kelsonthony.algafood.domain.service.CadastroProdutoService;
import com.kelsonthony.algafood.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping(path = "/v1/restaurantes/{restauranteId}/produtos", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteProdutoController implements RestauranteProdutoControllerOpenApi {

	@Autowired
	private CadastroProdutoService cadastroProdutoService;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CadastroRestauranteService cadastroRestauranteService;

	@Autowired
	private ProdutoModelAssembler produtoModelAssembler;

	@Autowired
	private ProdutoInputDisassembler produtoInputDisassembler;
	
	@Autowired
	private AlgaLinks algaLinks;

	@GetMapping
	public CollectionModel<ProdutoModel> listar(@PathVariable Long restauranteId,
			@RequestParam(required = false) Boolean incluirInativos) {

		Restaurante restaurante = cadastroRestauranteService.buscarOuFalhar(restauranteId);

		List<Produto> todosProdutos = null;

		if (incluirInativos == null) {
			todosProdutos = produtoRepository.findTodosByRestaurante(restaurante);

		} else {

			todosProdutos = produtoRepository.findAtivoByRestaurante(restaurante);

		}

		return produtoModelAssembler.toCollectionModel(todosProdutos)
				.add(algaLinks.linkToProdutos(restauranteId));
	}

	@GetMapping("/{produtoId}")
	public ProdutoModel buscar(Long restauranteId, @PathVariable Long produtoId) {
		Produto produto = cadastroProdutoService.buscarOuFalhar(restauranteId, produtoId);

		return produtoModelAssembler.toModel(produto);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProdutoModel adicionar(

			@PathVariable Long restauranteId,

			@RequestBody @Valid ProdutoInput produtoInput) {

		Restaurante restaurante = cadastroRestauranteService.buscarOuFalhar(restauranteId);

		Produto produto = produtoInputDisassembler.toDomainObject(produtoInput);
		produto.setRestaurante(restaurante);

		produto = cadastroProdutoService.salvar(produto);

		return produtoModelAssembler.toModel(produto);
	}

	@PutMapping("/{produtoId}")
	public ProdutoModel atualizar(@PathVariable Long restauranteId, @PathVariable Long produtoId,
			@RequestBody @Valid ProdutoInput produtoInput) {

		Produto produtoAtual = cadastroProdutoService.buscarOuFalhar(restauranteId, produtoId);

		produtoInputDisassembler.copyToDomainObject(produtoInput, produtoAtual);

		produtoAtual = cadastroProdutoService.salvar(produtoAtual);

		return produtoModelAssembler.toModel(produtoAtual);
	}


}

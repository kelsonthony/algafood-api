package com.kelsonthony.algafood.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.kelsonthony.algafood.api.assembler.UsuarioModelAssembler;
import com.kelsonthony.algafood.api.model.UsuarioModel;
import com.kelsonthony.algafood.api.openapi.controller.RestauranteUsuarioResponsavelControllerOpenApi;
import com.kelsonthony.algafood.domain.model.Restaurante;
import com.kelsonthony.algafood.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping(value = "/restaurantes/{restauranteId}/responsaveis", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteUsuarioResponsavelController implements RestauranteUsuarioResponsavelControllerOpenApi {
	
	@Autowired
	private CadastroRestauranteService cadastroRestauranteService;
	
	@Autowired
	private UsuarioModelAssembler usuarioModelAssembler;
	
	@GetMapping
	public CollectionModel<UsuarioModel> listar(@PathVariable Long restauranteId){
		Restaurante restaurante = cadastroRestauranteService.buscarOuFalhar(restauranteId);
		
		return usuarioModelAssembler.toCollectionModel(restaurante.getResponsaveis())
				.removeLinks()
				.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(
						RestauranteUsuarioResponsavelController.class)
						.listar(restauranteId))
				.withSelfRel());
	}
	
	@PutMapping("{usuarioId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void associar(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
		cadastroRestauranteService.associarReponsavel(restauranteId, usuarioId);
	}
	
	@DeleteMapping("{usuarioId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deassociar(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
		cadastroRestauranteService.desassociarReponsavel(restauranteId, usuarioId);
	}

}

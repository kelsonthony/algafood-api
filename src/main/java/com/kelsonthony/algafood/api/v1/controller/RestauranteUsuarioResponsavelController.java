package com.kelsonthony.algafood.api.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.kelsonthony.algafood.api.v1.assembler.UsuarioModelAssembler;
import com.kelsonthony.algafood.api.v1.links.AlgaLinks;
import com.kelsonthony.algafood.api.v1.model.UsuarioModel;
import com.kelsonthony.algafood.api.v1.openapi.controller.RestauranteUsuarioResponsavelControllerOpenApi;
import com.kelsonthony.algafood.core.security.AlgaSecurity;
import com.kelsonthony.algafood.core.security.CheckSecurity;
import com.kelsonthony.algafood.domain.model.Restaurante;
import com.kelsonthony.algafood.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping(value = "/v1/restaurantes/{restauranteId}/responsaveis", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteUsuarioResponsavelController
		implements RestauranteUsuarioResponsavelControllerOpenApi {

	@Autowired
	private CadastroRestauranteService cadastroRestauranteService;

	@Autowired
	private UsuarioModelAssembler usuarioModelAssembler;

	@Autowired
	private AlgaLinks algaLinks;

	@Autowired
	private AlgaSecurity algaSecurity;

	@CheckSecurity.Restaurantes.PodeGerenciarCadastro
	@GetMapping
	public CollectionModel<UsuarioModel> listar(@PathVariable Long restauranteId) {
		Restaurante restaurante = cadastroRestauranteService.buscarOuFalhar(restauranteId);

		CollectionModel<UsuarioModel> usuariosModel = usuarioModelAssembler
				.toCollectionModel(restaurante.getResponsaveis()).removeLinks();

		usuariosModel.add(algaLinks.linkToResponsaveisRestaurante(restauranteId));

		if (algaSecurity.podeGerenciarCadastroRestaurantes()) {
			usuariosModel.add(
					algaLinks.linkToRestauranteResponsavelAssociacao(restauranteId, "associar"));

			usuariosModel.getContent().stream().forEach(usuarioModel -> {
				usuarioModel.add(algaLinks.linkToRestauranteResponsavelDesassociacao(restauranteId,
						usuarioModel.getId(), "desassociar"));
			});
		}

		return usuariosModel;
	}

	@CheckSecurity.Restaurantes.PodeGerenciarCadastro
	@PutMapping("{usuarioId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> associar(@PathVariable Long restauranteId,
			@PathVariable Long usuarioId) {
		cadastroRestauranteService.associarReponsavel(restauranteId, usuarioId);

		return ResponseEntity.noContent().build();
	}

	@CheckSecurity.Restaurantes.PodeGerenciarCadastro
	@DeleteMapping("{usuarioId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> desassociar(@PathVariable Long restauranteId,
			@PathVariable Long usuarioId) {
		cadastroRestauranteService.desassociarReponsavel(restauranteId, usuarioId);

		return ResponseEntity.noContent().build();
	}

}

package com.kelsonthony.algafood.api.v1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kelsonthony.algafood.api.v1.assembler.PermissaoModelAssembler;
import com.kelsonthony.algafood.api.v1.model.PermissaoModel;
import com.kelsonthony.algafood.api.v1.openapi.controller.PermissaoControllerOpenApi;
import com.kelsonthony.algafood.core.security.CheckSecurity;
import com.kelsonthony.algafood.domain.model.Permissao;
import com.kelsonthony.algafood.domain.repository.PermissaoRepository;

@RestController
@RequestMapping(path = "/v1/permissoes", produces = MediaType.APPLICATION_JSON_VALUE)
public class PermissaoController implements PermissaoControllerOpenApi {

	@Autowired
	private PermissaoRepository permissaoRepository;
	
	@Autowired
	private PermissaoModelAssembler permissaoModelAssembler;
	
	@CheckSecurity.UsuariosGruposPermissoes.PodeConsultar
	@GetMapping
	public CollectionModel<PermissaoModel> listar() {
		
		List<Permissao> todasPermissoes = permissaoRepository.findAll();
		
		return permissaoModelAssembler.toCollectionModel(todasPermissoes);
	}
}

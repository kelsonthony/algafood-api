package com.kelsonthony.algafood.api.v1.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.kelsonthony.algafood.api.v1.assembler.GrupoInputDisassembler;
import com.kelsonthony.algafood.api.v1.assembler.GrupoModelAssembler;
import com.kelsonthony.algafood.api.v1.model.GrupoModel;
import com.kelsonthony.algafood.api.v1.model.input.GrupoInput;
import com.kelsonthony.algafood.api.v1.openapi.controller.GrupoControllerOpenApi;
import com.kelsonthony.algafood.domain.exception.GrupoNaoEncontradoException;
import com.kelsonthony.algafood.domain.exception.NegocioException;
import com.kelsonthony.algafood.domain.model.Grupo;
import com.kelsonthony.algafood.domain.repository.GrupoRepository;
import com.kelsonthony.algafood.domain.service.CadastroGrupoService;

@RestController
@RequestMapping(path = "/v1/grupos", produces = MediaType.APPLICATION_JSON_VALUE)
public class GrupoController implements GrupoControllerOpenApi {

	@Autowired
	private GrupoRepository grupoRepository;
	
	@Autowired
	private CadastroGrupoService cadastroGrupoService;
	
	@Autowired
	private GrupoModelAssembler grupoModelAssembler;
	
	@Autowired
	private GrupoInputDisassembler grupoInputDisassembler;
	
	@GetMapping
	public CollectionModel<GrupoModel> listar() {
		return grupoModelAssembler.toCollectionModel(grupoRepository.findAll());
	}
	
	@GetMapping("/{grupoId}")
	public GrupoModel buscar(@PathVariable Long grupoId) {
		Grupo grupo = cadastroGrupoService.buscarOuFalhar(grupoId);
		
		return grupoModelAssembler.toModel(grupo);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public GrupoModel adicionar(@RequestBody @Valid GrupoInput grupoInput) {
		try {
			Grupo grupo = grupoInputDisassembler.toDomainObject(grupoInput);
			
			grupo = cadastroGrupoService.salvar(grupo);
			
			return grupoModelAssembler.toModel(grupo);
		} catch (GrupoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage());
		}
	}
	
	@PutMapping("/{grupoId}")
	public GrupoModel atualizar(@PathVariable Long grupoId,
			@RequestBody @Valid GrupoInput grupoInput) {
		try {
			Grupo grupoAtual = cadastroGrupoService.buscarOuFalhar(grupoId);
			
			grupoInputDisassembler.copyToDomainObject(grupoInput, grupoAtual);
			
			grupoAtual = cadastroGrupoService.salvar(grupoAtual);
			
			return grupoModelAssembler.toModel(grupoAtual);
		} catch (GrupoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage());
		}
	}
	
	@DeleteMapping("/{grupoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long grupoId) {
		cadastroGrupoService.excluir(grupoId);
	}
}


















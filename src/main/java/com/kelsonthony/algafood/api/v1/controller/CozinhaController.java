package com.kelsonthony.algafood.api.v1.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
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

import com.kelsonthony.algafood.api.v1.assembler.CozinhaInputDisassembler;
import com.kelsonthony.algafood.api.v1.assembler.CozinhaModelAssembler;
import com.kelsonthony.algafood.api.v1.model.CozinhaModel;
import com.kelsonthony.algafood.api.v1.model.input.CozinhaInput;
import com.kelsonthony.algafood.api.v1.openapi.controller.CozinhaControllerOpenApi;
import com.kelsonthony.algafood.core.security.CheckSecurity;
import com.kelsonthony.algafood.domain.model.Cozinha;
import com.kelsonthony.algafood.domain.repository.CozinhaRepository;
import com.kelsonthony.algafood.domain.service.CadastroCozinhaService;

//@Slf4j
@RestController
@RequestMapping(path = "/v1/cozinhas", produces = MediaType.APPLICATION_JSON_VALUE)
public class CozinhaController implements CozinhaControllerOpenApi {
	
	@Autowired
	private CozinhaRepository cozinhaRepository;

	@Autowired
	private CadastroCozinhaService cadastroCozinhaService;
	
	@Autowired
	private CozinhaModelAssembler cozinhaModelAssembler;
	
	@Autowired
	private CozinhaInputDisassembler cozinhaInputDisassembler;
	
	@Autowired
	private PagedResourcesAssembler<Cozinha> pagedResourcesAssembler;

	@CheckSecurity.Cozinhas.PodeConsultar
	@GetMapping
	public PagedModel<CozinhaModel> listar(@PageableDefault(size= 10) Pageable pageable) {
		
		//log.info("Consultando cozinhas com páginas de {} registros...", pageable.getPageSize());
		
		//System.out.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
		
		/*
		 * if (true) { throw new RuntimeException("Teste de exception"); }
		 */
		
		Page<Cozinha> cozinhasPage = cozinhaRepository.findAll(pageable);
		
		PagedModel<CozinhaModel> cozinhasPagedModel = pagedResourcesAssembler
				.toModel(cozinhasPage, cozinhaModelAssembler);
		
		return cozinhasPagedModel;
	}

	@CheckSecurity.Cozinhas.PodeConsultar
	@GetMapping("/{cozinhaId}")
	public CozinhaModel buscar(@PathVariable Long cozinhaId) {
		Cozinha cozinha = cadastroCozinhaService.buscarOuFalhar(cozinhaId);
		
		return cozinhaModelAssembler.toModel(cozinha);
	}

	@CheckSecurity.Cozinhas.PodeEditar
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CozinhaModel adicionar(@RequestBody @Valid CozinhaInput cozinhaInput) {
		
		Cozinha cozinha = cozinhaInputDisassembler.toDomainObject(cozinhaInput);
		
		cozinha = cadastroCozinhaService.salvar(cozinha);
		
		return cozinhaModelAssembler.toModel(cozinha);
	}

	@CheckSecurity.Cozinhas.PodeEditar
	@PutMapping("/{cozinhaId}")
	public CozinhaModel atualizar(@PathVariable Long cozinhaId, @RequestBody @Valid CozinhaInput cozinhaInput) {
		
		Cozinha cozinhaAtual = cadastroCozinhaService.buscarOuFalhar(cozinhaId);

		cozinhaInputDisassembler.copyToDomainObject(cozinhaInput, cozinhaAtual);
		
		cozinhaAtual = cadastroCozinhaService.salvar(cozinhaAtual);
		
		return cozinhaModelAssembler.toModel(cozinhaAtual);

	}

	//@PreAuthorize("hasAuthority('EDITAR_COZINHAS')")
	@CheckSecurity.Cozinhas.PodeEditar
	@DeleteMapping("/{cozinhaId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long cozinhaId) {
		cadastroCozinhaService.excluir(cozinhaId);
	}
}

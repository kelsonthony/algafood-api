package com.kelsonthony.algafood.api.v1.controller;

import java.util.List;

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

import com.kelsonthony.algafood.api.ResourceUriHelper;
import com.kelsonthony.algafood.api.v1.assembler.CidadeInputDisassembler;
import com.kelsonthony.algafood.api.v1.assembler.CidadeModelAssembler;
import com.kelsonthony.algafood.api.v1.model.CidadeModel;
import com.kelsonthony.algafood.api.v1.model.input.CidadeInput;
import com.kelsonthony.algafood.api.v1.openapi.controller.CidadeControllerOpenApi;
import com.kelsonthony.algafood.core.security.CheckSecurity;
import com.kelsonthony.algafood.domain.exception.EstadoNaoEncontradoException;
import com.kelsonthony.algafood.domain.exception.NegocioException;
import com.kelsonthony.algafood.domain.model.Cidade;
import com.kelsonthony.algafood.domain.repository.CidadeRepository;
import com.kelsonthony.algafood.domain.service.CadastroCidadeService;


@RestController
@RequestMapping(path = "/v1/cidades", produces = MediaType.APPLICATION_JSON_VALUE)
public class CidadeController implements CidadeControllerOpenApi {

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private CadastroCidadeService cadastroCidadeService;
	
	@Autowired
	private CidadeModelAssembler cidadeModelAssembler;
	
	@Autowired
	private CidadeInputDisassembler cidadeInputDisassembler;

	//@Deprecated
	@CheckSecurity.Cidades.PodeConsultar
	@GetMapping
	public CollectionModel<CidadeModel> listar() {
		List<Cidade> todasCidades = cidadeRepository.findAll();
		
		return cidadeModelAssembler.toCollectionModel(todasCidades);
		
	}

	@CheckSecurity.Cidades.PodeConsultar
	@GetMapping("/{cidadeId}")
	public CidadeModel buscar(@PathVariable Long cidadeId) {
		
		Cidade cidade =  cadastroCidadeService.buscarOuFalhar(cidadeId);
		
		CidadeModel cidadeModel = cidadeModelAssembler.toModel(cidade);
		
		return cidadeModel;
	}

	@CheckSecurity.Cidades.PodeEditar
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CidadeModel adicionar(@RequestBody @Valid CidadeInput cidadeInput) {
		try {
			Cidade cidade = cidadeInputDisassembler.toDomainObject(cidadeInput);
			
			cidade = cadastroCidadeService.salvar(cidade);
			
			CidadeModel cidadeModel = cidadeModelAssembler.toModel(cidade);
			
			ResourceUriHelper.addUriInResponseHeader(cidadeModel.getId());
			
			return cidadeModel;
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage());
		}

	}

	@CheckSecurity.Cidades.PodeEditar
	@PutMapping("/{cidadeId}")
	public CidadeModel atualizar(@PathVariable Long cidadeId, 
			@RequestBody @Valid CidadeInput cidadeInput) {
		try {
			Cidade cidadeAtual = cadastroCidadeService.buscarOuFalhar(cidadeId);

			cidadeInputDisassembler.copyToDomainObject(cidadeInput, cidadeAtual);
			
			cidadeAtual = cadastroCidadeService.salvar(cidadeAtual);

			return cidadeModelAssembler.toModel(cidadeAtual);
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}

	}

	@CheckSecurity.Cidades.PodeEditar
	@DeleteMapping("/{cidadeId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long cidadeId) {
		cadastroCidadeService.excluir(cidadeId);
	}

	

}

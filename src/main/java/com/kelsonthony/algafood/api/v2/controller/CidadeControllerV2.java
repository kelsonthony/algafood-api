package com.kelsonthony.algafood.api.v2.controller;

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
import com.kelsonthony.algafood.api.v2.assembler.CidadeInputDisassemblerV2;
import com.kelsonthony.algafood.api.v2.assembler.CidadeModelAssemblerV2;
import com.kelsonthony.algafood.api.v2.model.CidadeModelV2;
import com.kelsonthony.algafood.api.v2.model.input.CidadeInputV2;
import com.kelsonthony.algafood.domain.exception.EstadoNaoEncontradoException;
import com.kelsonthony.algafood.domain.exception.NegocioException;
import com.kelsonthony.algafood.domain.model.Cidade;
import com.kelsonthony.algafood.domain.repository.CidadeRepository;
import com.kelsonthony.algafood.domain.service.CadastroCidadeService;


@RestController
@RequestMapping(path = "/v2/cidades", produces = MediaType.APPLICATION_JSON_VALUE)
public class CidadeControllerV2 {

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private CadastroCidadeService cadastroCidadeService;
	
	@Autowired
	private CidadeModelAssemblerV2 cidadeModelAssembler;
	
	@Autowired
	private CidadeInputDisassemblerV2 cidadeInputDisassembler;

	@GetMapping
	public CollectionModel<CidadeModelV2> listar() {
		List<Cidade> todasCidades = cidadeRepository.findAll();
		
		return cidadeModelAssembler.toCollectionModel(todasCidades);
		
	}

	@GetMapping("/{cidadeId}")
	public CidadeModelV2 buscar(@PathVariable Long cidadeId) {
		
		Cidade cidade =  cadastroCidadeService.buscarOuFalhar(cidadeId);
		
		CidadeModelV2 cidadeModel = cidadeModelAssembler.toModel(cidade);
		
		return cidadeModel;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CidadeModelV2 adicionar(@RequestBody @Valid CidadeInputV2 cidadeInput) {
		try {
			Cidade cidade = cidadeInputDisassembler.toDomainObject(cidadeInput);
			
			cidade = cadastroCidadeService.salvar(cidade);
			
			CidadeModelV2 cidadeModel = cidadeModelAssembler.toModel(cidade);
			
			ResourceUriHelper.addUriInResponseHeader(cidadeModel.getIdCidade());
			
			return cidadeModel;
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage());
		}

	}

	@PutMapping("/{cidadeId}")
	public CidadeModelV2 atualizar(@PathVariable Long cidadeId, 
			@RequestBody @Valid CidadeInputV2 cidadeInput) {
		try {
			Cidade cidadeAtual = cadastroCidadeService.buscarOuFalhar(cidadeId);

			cidadeInputDisassembler.copyToDomainObject(cidadeInput, cidadeAtual);
			
			cidadeAtual = cadastroCidadeService.salvar(cidadeAtual);

			return cidadeModelAssembler.toModel(cidadeAtual);
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}

	}

	@DeleteMapping("/{cidadeId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long cidadeId) {
		cadastroCidadeService.excluir(cidadeId);
	}

	

}

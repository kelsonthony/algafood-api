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

import com.kelsonthony.algafood.api.v1.assembler.EstadoInputDisassembler;
import com.kelsonthony.algafood.api.v1.assembler.EstadoModelAssembler;
import com.kelsonthony.algafood.api.v1.model.EstadoModel;
import com.kelsonthony.algafood.api.v1.model.input.EstadoInput;
import com.kelsonthony.algafood.api.v1.openapi.controller.EstadoControllerOpenApi;
import com.kelsonthony.algafood.core.security.CheckSecurity;
import com.kelsonthony.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.kelsonthony.algafood.domain.exception.NegocioException;
import com.kelsonthony.algafood.domain.model.Estado;
import com.kelsonthony.algafood.domain.repository.EstadoRepository;
import com.kelsonthony.algafood.domain.service.CadastroEstadoService;

@RestController
@RequestMapping(value = "/v1/estados", produces = MediaType.APPLICATION_JSON_VALUE)
public class EstadoController implements EstadoControllerOpenApi {

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private CadastroEstadoService cadastroEstadoService;
	
	@Autowired
	private EstadoModelAssembler estadoModelAssembler;
	
	@Autowired
	private EstadoInputDisassembler estadoInputDisassembler;

	@CheckSecurity.Cidades.PodeConsultar
	@GetMapping
	public CollectionModel<EstadoModel> listar() {
		
		List<Estado> todosEstados = estadoRepository.findAll();
		
		return estadoModelAssembler.toCollectionModel(todosEstados);
	}

	@CheckSecurity.Cidades.PodeConsultar
	@GetMapping("/{estadoId}")
	public EstadoModel buscar(@PathVariable Long estadoId) {
		Estado estado = cadastroEstadoService.buscarOuFalhar(estadoId);
		
		return estadoModelAssembler.toModel(estado);
	}

	@CheckSecurity.Cidades.PodeEditar
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EstadoModel adicionar(@RequestBody @Valid EstadoInput estadoInput) {
		try {
			Estado estado = estadoInputDisassembler.toDomainObject(estadoInput);
			
			estado = cadastroEstadoService.salvar(estado);
			
			return estadoModelAssembler.toModel(estado);
		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}

	@CheckSecurity.Cidades.PodeEditar
	@PutMapping("/{estadoId}")
	public EstadoModel atualizar(@PathVariable Long estadoId, @RequestBody @Valid EstadoInput estadoInput) {

		try {
			Estado estadoAtual = cadastroEstadoService.buscarOuFalhar(estadoId);

			estadoInputDisassembler.copyToDomainObject(estadoInput, estadoAtual);
			
			estadoAtual = cadastroEstadoService.salvar(estadoAtual);
			
			return estadoModelAssembler.toModel(estadoAtual);
		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}

	}

	@CheckSecurity.Cidades.PodeEditar
	@DeleteMapping("/{estadoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long estadoId) {
			cadastroEstadoService.excluir(estadoId);

	}
}

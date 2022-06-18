package com.kelsonthony.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
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
import com.kelsonthony.algafood.api.assembler.CidadeInputDisassembler;
import com.kelsonthony.algafood.api.assembler.CidadeModelAssembler;
import com.kelsonthony.algafood.api.model.CidadeModel;
import com.kelsonthony.algafood.api.model.input.CidadeInput;
import com.kelsonthony.algafood.api.openapi.controller.CidadeControllerOpenApi;
import com.kelsonthony.algafood.domain.exception.EstadoNaoEncontradoException;
import com.kelsonthony.algafood.domain.exception.NegocioException;
import com.kelsonthony.algafood.domain.model.Cidade;
import com.kelsonthony.algafood.domain.repository.CidadeRepository;
import com.kelsonthony.algafood.domain.service.CadastroCidadeService;


@RestController
@RequestMapping(path = "/cidades", produces = MediaType.APPLICATION_JSON_VALUE)
public class CidadeController implements CidadeControllerOpenApi {

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private CadastroCidadeService cadastroCidadeService;
	
	@Autowired
	private CidadeModelAssembler cidadeModelAssembler;
	
	@Autowired
	private CidadeInputDisassembler cidadeInputDisassembler;

	@GetMapping
	public CollectionModel<CidadeModel> listar() {
		List<Cidade> todasCidades = cidadeRepository.findAll();
		
		List<CidadeModel> cidadesModel = cidadeModelAssembler.toCollectioModel(todasCidades);
		
		cidadesModel.forEach(cidadeModel -> {
			cidadeModel.add(WebMvcLinkBuilder.linkTo(
					WebMvcLinkBuilder.methodOn(CidadeController.class)
					.buscar(cidadeModel.getId())).withSelfRel());
			
			cidadeModel.add(WebMvcLinkBuilder.linkTo(
					WebMvcLinkBuilder.methodOn(CidadeController.class)
					.listar()).withRel("cidades"));
			
			cidadeModel.getEstado().add(WebMvcLinkBuilder.linkTo(
					WebMvcLinkBuilder.methodOn(EstadoController.class)
					.buscar(cidadeModel.getEstado().getId())).withSelfRel());
		});
		
		//return new CollectonModel<>(cidadesModel);
		CollectionModel<CidadeModel> cidadesCollectionModel = CollectionModel.of(cidadesModel);
		
		cidadesCollectionModel.add(WebMvcLinkBuilder.linkTo(CidadeController.class).withSelfRel());
		
		return cidadesCollectionModel;
	}

	@GetMapping("/{cidadeId}")
	public CidadeModel buscar(@PathVariable Long cidadeId) {
		
		Cidade cidade =  cadastroCidadeService.buscarOuFalhar(cidadeId);
		
		CidadeModel cidadeModel = cidadeModelAssembler.toModel(cidade);
		
		Link link = WebMvcLinkBuilder.linkTo(
				WebMvcLinkBuilder.methodOn(CidadeController.class)
				.buscar(cidadeModel.getId())).withSelfRel();
		
		cidadeModel.add(link);
		
		//cidadeModel.add(WebMvcLinkBuilder.linkTo(CidadeController.class)
			//	.slash(cidadeModel.getId()).withSelfRel());
		
		//cidadeModel.add(new Link("http://localhost:8080/cidades/1", IanaLinkRelations.SELF));
		//cidadeModel.add(new Link("http://localhost:8080/cidades/1"));
		
		//cidadeModel.add(new Link("http://localhost:8080/cidades", "cidades"));
		//cidadeModel.add(new Link("http://localhost:8080/cidades", IanaLinkRelations.COLLECTION));
		cidadeModel.add(WebMvcLinkBuilder.linkTo(
				WebMvcLinkBuilder.methodOn(CidadeController.class)
				.listar()).withRel("cidades"));
		
		
		//cidadeModel.getEstado().add(new Link("http://localhost:8080/estados/1"));
		//cidadeModel.getEstado().add(WebMvcLinkBuilder.linkTo(EstadoController.class)
			//	.slash(cidadeModel.getEstado().getId()).withSelfRel());
		cidadeModel.getEstado().add(WebMvcLinkBuilder.linkTo(
				WebMvcLinkBuilder.methodOn(EstadoController.class)
				.buscar(cidadeModel.getEstado().getId())).withSelfRel());
		
		return cidadeModel;
	}

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

	@DeleteMapping("/{cidadeId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long cidadeId) {
		cadastroCidadeService.excluir(cidadeId);
	}

	

}

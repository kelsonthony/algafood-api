package com.kelsonthony.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.kelsonthony.algafood.api.controller.CidadeController;
import com.kelsonthony.algafood.api.controller.EstadoController;
import com.kelsonthony.algafood.api.model.CidadeModel;
import com.kelsonthony.algafood.domain.model.Cidade;

@Component
public class CidadeModelAssembler extends 
	RepresentationModelAssemblerSupport<Cidade, CidadeModel> {

	@Autowired
	private ModelMapper modelMapper;
	
	public CidadeModelAssembler() {
		super(CidadeController.class, CidadeModel.class);
	}

	
	@Override
	public CidadeModel toModel(Cidade cidade) {
		
		CidadeModel cidadeModel = createModelWithId(cidade.getId(), cidade);
		
		modelMapper.map(cidade, cidadeModel);
				
		cidadeModel.add(WebMvcLinkBuilder.linkTo(
				WebMvcLinkBuilder.methodOn(CidadeController.class)
				.listar()).withRel("cidades"));
		
		cidadeModel.getEstado().add(WebMvcLinkBuilder.linkTo(
				WebMvcLinkBuilder.methodOn(EstadoController.class)
				.buscar(cidadeModel.getEstado().getId())).withSelfRel());
		
		return cidadeModel;
	}
	
	@Override
	public CollectionModel<CidadeModel> toCollectionModel(Iterable<? extends 
			Cidade> entities) {
		// TODO Auto-generated method stub
		return super.toCollectionModel(entities)
				.add(WebMvcLinkBuilder.linkTo(CidadeController.class)
						.withSelfRel());
	}
	
}

package com.kelsonthony.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.kelsonthony.algafood.api.controller.EstadoController;
import com.kelsonthony.algafood.api.model.EstadoModel;
import com.kelsonthony.algafood.domain.model.Estado;

@Component
public class EstadoModelAssembler extends 
	RepresentationModelAssemblerSupport<Estado, EstadoModel> {

	@Autowired
	private ModelMapper modelMapper;
	
	public EstadoModelAssembler() {
		super(EstadoController.class, EstadoModel.class);
	}
	
	@Override
	public EstadoModel toModel(Estado estado) {
		
		EstadoModel estadoModel = createModelWithId(estado.getId(), estado);
		
		modelMapper.map(estado, estadoModel);
		
		estadoModel.add(WebMvcLinkBuilder.linkTo(
				WebMvcLinkBuilder.methodOn(EstadoController.class)
				.listar()).withRel("estados"));
		
		
		
		return estadoModel;
	}
	
	@Override
	public CollectionModel<EstadoModel> toCollectionModel(Iterable<? 
			extends Estado> entities) {
		// TODO Auto-generated method stub
		return super.toCollectionModel(entities)
				.add(WebMvcLinkBuilder.linkTo(EstadoController.class).withSelfRel());
	}
	
}

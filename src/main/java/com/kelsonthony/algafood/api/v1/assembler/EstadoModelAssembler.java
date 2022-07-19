package com.kelsonthony.algafood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.kelsonthony.algafood.api.v1.controller.EstadoController;
import com.kelsonthony.algafood.api.v1.links.AlgaLinks;
import com.kelsonthony.algafood.api.v1.model.EstadoModel;
import com.kelsonthony.algafood.core.security.AlgaSecurity;
import com.kelsonthony.algafood.domain.model.Estado;

@Component
public class EstadoModelAssembler extends 
	RepresentationModelAssemblerSupport<Estado, EstadoModel> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AlgaLinks algaLinks;
	
	@Autowired
	private AlgaSecurity algaSecurity;
	
	public EstadoModelAssembler() {
		super(EstadoController.class, EstadoModel.class);
	}
	
	@Override
	public EstadoModel toModel(Estado estado) {
		
		EstadoModel estadoModel = createModelWithId(estado.getId(), estado);
		
		modelMapper.map(estado, estadoModel);
		
		if (algaSecurity.podeConsultarEstados()) {
			estadoModel.add(algaLinks.linkToEstados("estados"));
		}
		
		
		
		return estadoModel;
	}
	
	@Override
	public CollectionModel<EstadoModel> toCollectionModel(Iterable<? 
			extends Estado> entities) {
		CollectionModel<EstadoModel> collectionModel = super.toCollectionModel(entities);
		
		if (algaSecurity.podeConsultarEstados()) {
			collectionModel.add(algaLinks.linkToEstados());
		}
				
		return collectionModel;
	}
	
	/*
	 * @Override public CollectionModel<EstadoModel> toCollectionModel(Iterable<?
	 * extends Estado> entities) { // TODO Auto-generated method stub return
	 * super.toCollectionModel(entities)
	 * .add(WebMvcLinkBuilder.linkTo(EstadoController.class).withSelfRel()); }
	 */
	
}

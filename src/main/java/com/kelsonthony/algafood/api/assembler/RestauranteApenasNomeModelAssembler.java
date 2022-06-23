package com.kelsonthony.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.kelsonthony.algafood.api.controller.RestauranteController;
import com.kelsonthony.algafood.api.links.AlgaLinks;
import com.kelsonthony.algafood.api.model.RestauranteApenasNomeModel;
import com.kelsonthony.algafood.domain.model.Restaurante;

@Component
public class RestauranteApenasNomeModelAssembler 
	extends RepresentationModelAssemblerSupport<Restaurante, RestauranteApenasNomeModel>{
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AlgaLinks algaLinks;
	
	public RestauranteApenasNomeModelAssembler() {
		super(RestauranteController.class, RestauranteApenasNomeModel.class);
	}

	@Override
	public RestauranteApenasNomeModel toModel(Restaurante restaurante) {
		
		RestauranteApenasNomeModel restauranteApenasNomeModel = createModelWithId(restaurante.getId(), restaurante);
		
		modelMapper.map(restaurante, restauranteApenasNomeModel);
		
		restauranteApenasNomeModel.add(algaLinks.linkToRestaurantes("restaurantes"));
		
		return restauranteApenasNomeModel;
	}

	@Override
    public CollectionModel<RestauranteApenasNomeModel> toCollectionModel(
    		Iterable<? extends Restaurante> entities) {
        
		return super.toCollectionModel(entities)
                .add(algaLinks.linkToRestaurantes());
    } 
}

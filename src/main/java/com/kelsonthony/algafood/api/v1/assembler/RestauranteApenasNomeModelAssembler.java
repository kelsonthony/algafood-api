package com.kelsonthony.algafood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.kelsonthony.algafood.api.v1.controller.RestauranteController;
import com.kelsonthony.algafood.api.v1.links.AlgaLinks;
import com.kelsonthony.algafood.api.v1.model.RestauranteApenasNomeModel;
import com.kelsonthony.algafood.core.security.AlgaSecurity;
import com.kelsonthony.algafood.domain.model.Restaurante;

@Component
public class RestauranteApenasNomeModelAssembler 
	extends RepresentationModelAssemblerSupport<Restaurante, RestauranteApenasNomeModel>{
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AlgaLinks algaLinks;
	
	@Autowired
	private AlgaSecurity algaSecurity;
	
	public RestauranteApenasNomeModelAssembler() {
		super(RestauranteController.class, RestauranteApenasNomeModel.class);
	}

	@Override
	public RestauranteApenasNomeModel toModel(Restaurante restaurante) {
		
		RestauranteApenasNomeModel restauranteApenasNomeModel = createModelWithId(restaurante.getId(), restaurante);
		
		modelMapper.map(restaurante, restauranteApenasNomeModel);
		
		if (algaSecurity.podeConsultarRestaurantes()) {
			restauranteApenasNomeModel.add(algaLinks.linkToRestaurantes("restaurantes"));
		}
		
		return restauranteApenasNomeModel;
	}

	@Override
    public CollectionModel<RestauranteApenasNomeModel> toCollectionModel(
    		Iterable<? extends Restaurante> entities) {
        
		CollectionModel<RestauranteApenasNomeModel> collectionModel = super.toCollectionModel(entities);
		
		if (algaSecurity.podeConsultarRestaurantes()) {
			collectionModel.add(algaLinks.linkToRestaurantes());
		}
		
		return collectionModel;
            
    } 
}

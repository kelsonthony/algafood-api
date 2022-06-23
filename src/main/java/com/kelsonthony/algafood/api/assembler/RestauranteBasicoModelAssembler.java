package com.kelsonthony.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.kelsonthony.algafood.api.controller.RestauranteController;
import com.kelsonthony.algafood.api.links.AlgaLinks;
import com.kelsonthony.algafood.api.model.RestauranteBasicoModel;
import com.kelsonthony.algafood.domain.model.Restaurante;

@Component
public class RestauranteBasicoModelAssembler 
	extends RepresentationModelAssemblerSupport<Restaurante, RestauranteBasicoModel>{
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AlgaLinks algaLinks;
	
	public RestauranteBasicoModelAssembler() {
		super(RestauranteController.class, RestauranteBasicoModel.class);
	}

	@Override
	public RestauranteBasicoModel toModel(Restaurante restaurante) {
		
		RestauranteBasicoModel restauranteBasicoModel = createModelWithId(restaurante.getId(), restaurante);
		
		modelMapper.map(restaurante, restauranteBasicoModel);
		
		restauranteBasicoModel.add(algaLinks.linkToRestaurantes("restaurantes"));
		
		restauranteBasicoModel.getCozinha().add(
				algaLinks.linkToCozinha(restaurante.getCozinha().getId()));
		
		return restauranteBasicoModel;
	}

	@Override
    public CollectionModel<RestauranteBasicoModel> toCollectionModel(
    		Iterable<? extends Restaurante> entities) {
        
		return super.toCollectionModel(entities)
                .add(algaLinks.linkToRestaurantes());
    } 
}

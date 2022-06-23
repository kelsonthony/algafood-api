package com.kelsonthony.algafood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.kelsonthony.algafood.api.v1.controller.RestauranteController;
import com.kelsonthony.algafood.api.v1.links.AlgaLinks;
import com.kelsonthony.algafood.api.v1.model.RestauranteModel;
import com.kelsonthony.algafood.domain.model.Restaurante;

@Component
public class RestauranteModelAssembler 
	extends RepresentationModelAssemblerSupport<Restaurante, RestauranteModel>{
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AlgaLinks algaLinks;
	
	public RestauranteModelAssembler() {
		super(RestauranteController.class, RestauranteModel.class);
	}

	@Override
	public RestauranteModel toModel(Restaurante restaurante) {
		
		RestauranteModel restauranteModel = createModelWithId(restaurante.getId(), restaurante);
		
		modelMapper.map(restaurante, restauranteModel);
		
		restauranteModel.add(algaLinks.linkToRestaurantes("restaurantes"));
		
		if (restaurante.ativacaoPermitida()) {
			restauranteModel.add(algaLinks.linkToAtivarRestaurante(restaurante.getId(), "ativar"));
		}
		
		if (restaurante.InativacaoPermitida()) {
			restauranteModel.add(algaLinks.linkToInativarRestaurante(restaurante.getId(), "inativar"));
		}
		
		if (restaurante.aberturaPermitida()) {
			restauranteModel.add(algaLinks.linkToAbrirRestaurante(restaurante.getId(), "abrir"));
		}
		
		if (restaurante.fechamentoPermitido()) {
			restauranteModel.add(algaLinks.linkToFecharRestaurante(restaurante.getId(), "fechar"));
		}
		
		restauranteModel.getCozinha().add(algaLinks.linkToCozinha(
				restaurante.getCozinha().getId()));
		
		restauranteModel.getEndereco().getCidade().add(algaLinks.linkToCidade(
				restaurante.getEndereco().getCidade().getId()));
		
		if (restauranteModel.getEndereco() != null
				&& restauranteModel.getEndereco().getCep() != null) {
			restauranteModel.getEndereco().getCidade().add(
					algaLinks.linkToCidade(restaurante.getEndereco().getCidade().getId()));
		}
		
		restauranteModel.add(algaLinks.linkToRestauranteFormasPagamento(restaurante.getId(), "formas-pagamentos"));
		
		restauranteModel.add(algaLinks.linkToRestauranteResponsaveis(restaurante.getId(), "responsaveis"));
		
		return restauranteModel;
	}

	@Override
	public CollectionModel<RestauranteModel> toCollectionModel(
			Iterable<? extends Restaurante> entities) {
		return super.toCollectionModel(entities)
				.add(algaLinks.linkToRestaurantes());
	}
	
}

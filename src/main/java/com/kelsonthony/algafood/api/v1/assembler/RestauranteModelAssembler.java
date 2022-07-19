package com.kelsonthony.algafood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.kelsonthony.algafood.api.v1.controller.RestauranteController;
import com.kelsonthony.algafood.api.v1.links.AlgaLinks;
import com.kelsonthony.algafood.api.v1.model.RestauranteModel;
import com.kelsonthony.algafood.core.security.AlgaSecurity;
import com.kelsonthony.algafood.domain.model.Restaurante;

@Component
public class RestauranteModelAssembler 
	extends RepresentationModelAssemblerSupport<Restaurante, RestauranteModel>{
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AlgaLinks algaLinks;
	
	@Autowired
	private AlgaSecurity algaSecurity;
	
	public RestauranteModelAssembler() {
		super(RestauranteController.class, RestauranteModel.class);
	}

	@Override
	public RestauranteModel toModel(Restaurante restaurante) {
		
		RestauranteModel restauranteModel = createModelWithId(restaurante.getId(), restaurante);
		
		modelMapper.map(restaurante, restauranteModel);
		
		if (algaSecurity.podeConsultarRestaurantes()) {
			restauranteModel.add(algaLinks.linkToRestaurantes("restaurantes"));
		}
		
		if (algaSecurity.podeGerenciarCadastroRestaurantes()) {
			if (restaurante.ativacaoPermitida()) {
				restauranteModel.add(algaLinks.linkToAtivarRestaurante(restaurante.getId(), "ativar"));
			}
			
			if (restaurante.InativacaoPermitida()) {
				restauranteModel.add(algaLinks.linkToInativarRestaurante(restaurante.getId(), "inativar"));
			}
		}
		
		if (algaSecurity.podeGerenciarFuncionamentoRestaurantes(restaurante.getId())) {
			if (restaurante.aberturaPermitida()) {
				restauranteModel.add(algaLinks.linkToAbrirRestaurante(restaurante.getId(), "abrir"));
			}
			
			if (restaurante.fechamentoPermitido()) {
				restauranteModel.add(algaLinks.linkToFecharRestaurante(restaurante.getId(), "fechar"));
			}
		}
		
		if (algaSecurity.podeConsultarRestaurantes()) {
			restauranteModel.add(algaLinks.linkToProdutos(restaurante.getId(), "produtos"));
		}
		
		if (algaSecurity.podeConsultarCozinhas()) {
			restauranteModel.getCozinha().add(algaLinks.linkToCozinha(
					restaurante.getCozinha().getId()));
		}
		
		
		if (algaSecurity.podeConsultarCidades()) {
			if (restauranteModel.getEndereco() != null
					&& restauranteModel.getEndereco().getCep() != null) {
				restauranteModel.getEndereco().getCidade().add(
						algaLinks.linkToCidade(restaurante.getEndereco().getCidade().getId()));
			}
		}
		
		if (algaSecurity.podeConsultarRestaurantes()) {
			restauranteModel.add(algaLinks.linkToRestauranteFormasPagamento(
					restaurante.getId(), "formas-pagamentos"));
		}
		
		if (algaSecurity.podeGerenciarCadastroRestaurantes()) {
			restauranteModel.add(algaLinks.linkToRestauranteResponsaveis(
					restaurante.getId(), "responsaveis"));
		}
		
		return restauranteModel;
	}

	@Override
	public CollectionModel<RestauranteModel> toCollectionModel(
			Iterable<? extends Restaurante> entities) {
		
		CollectionModel<RestauranteModel> collectionModel = super.toCollectionModel(entities);
		
		if (algaSecurity.podeConsultarRestaurantes()) {
			collectionModel.add(algaLinks.linkToRestaurantes());
		}
		
		return collectionModel;
				
	}
	
}

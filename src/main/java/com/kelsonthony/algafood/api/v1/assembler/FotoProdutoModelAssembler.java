package com.kelsonthony.algafood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.kelsonthony.algafood.api.v1.controller.RestauranteProdutoFotoController;
import com.kelsonthony.algafood.api.v1.links.AlgaLinks;
import com.kelsonthony.algafood.api.v1.model.FotoProdutoModel;
import com.kelsonthony.algafood.core.security.AlgaSecurity;
import com.kelsonthony.algafood.domain.model.FotoProduto;

@Component
public class FotoProdutoModelAssembler
		extends RepresentationModelAssemblerSupport<FotoProduto, FotoProdutoModel> {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AlgaLinks algaLinks;
	
	@Autowired
	private AlgaSecurity algaSecurity;    

	public FotoProdutoModelAssembler() {
		super(RestauranteProdutoFotoController.class, FotoProdutoModel.class);
	}

	public FotoProdutoModel toModel(FotoProduto foto) {

		FotoProdutoModel fotoProdutoModel = modelMapper.map(foto, FotoProdutoModel.class);

		if (algaSecurity.podeConsultarRestaurantes()) {
			fotoProdutoModel.add(algaLinks.linkToFotoProduto(foto.getRestauranteId(),
					foto.getProduto().getId()));
			
			fotoProdutoModel.add(algaLinks.linkToFotoProduto(foto.getRestauranteId(),
					foto.getProduto().getId(), "produto"));
		}

		return fotoProdutoModel;
	}

}

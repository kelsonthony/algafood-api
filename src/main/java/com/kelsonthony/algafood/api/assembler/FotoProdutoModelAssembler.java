package com.kelsonthony.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.kelsonthony.algafood.api.controller.RestauranteProdutoFotoController;
import com.kelsonthony.algafood.api.links.AlgaLinks;
import com.kelsonthony.algafood.api.model.FotoProdutoModel;
import com.kelsonthony.algafood.domain.model.FotoProduto;

@Component
public class FotoProdutoModelAssembler
		extends RepresentationModelAssemblerSupport<FotoProduto, FotoProdutoModel> {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AlgaLinks algaLinks;

	public FotoProdutoModelAssembler() {
		super(RestauranteProdutoFotoController.class, FotoProdutoModel.class);
	}

	public FotoProdutoModel toModel(FotoProduto foto) {

		FotoProdutoModel fotoProdutoModel = modelMapper.map(foto, FotoProdutoModel.class);

		fotoProdutoModel.add(algaLinks.linkToProduto(foto.getRestauranteId(),
				foto.getProduto().getId(), "produto"));

		return fotoProdutoModel;
	}

}

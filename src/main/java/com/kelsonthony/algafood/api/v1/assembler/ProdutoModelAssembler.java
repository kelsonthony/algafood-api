package com.kelsonthony.algafood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.kelsonthony.algafood.api.v1.controller.RestauranteProdutoController;
import com.kelsonthony.algafood.api.v1.links.AlgaLinks;
import com.kelsonthony.algafood.api.v1.model.ProdutoModel;
import com.kelsonthony.algafood.core.security.AlgaSecurity;
import com.kelsonthony.algafood.domain.model.Produto;

@Component
public class ProdutoModelAssembler 
	extends RepresentationModelAssemblerSupport<Produto, ProdutoModel>{

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AlgaLinks algaLinks;
	
	@Autowired
	private AlgaSecurity algaSecurity;
	
	public ProdutoModelAssembler() {
		super(RestauranteProdutoController.class, ProdutoModel.class);
	}

	@Override
	public ProdutoModel toModel(Produto produto) {
		
		ProdutoModel produtoModel = createModelWithId(produto.getId(), 
				produto, produto.getRestaurante().getId());
		
		modelMapper.map(produto, produtoModel);
		
		
		if (algaSecurity.podeConsultarRestaurantes()) {
			produtoModel.add(algaLinks.linkToProduto(
					produto.getRestaurante().getId(), produto.getId(),"produtos"));
			
			produtoModel.add(algaLinks.linkToFotoProduto(
					produto.getRestaurante().getId(), produto.getId(), "foto"));
		}
		
		return produtoModel;
	}
	
	/*
	 * public List<ProdutoModel> toCollectionModel(List<Produto> produtos) { return
	 * produtos.stream().map(produto ->
	 * toModel(produto)).collect(Collectors.toList()); }
	 */
}

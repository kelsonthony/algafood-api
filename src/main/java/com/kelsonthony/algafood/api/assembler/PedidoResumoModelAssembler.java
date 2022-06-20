package com.kelsonthony.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.kelsonthony.algafood.api.controller.PedidoController;
import com.kelsonthony.algafood.api.controller.RestauranteController;
import com.kelsonthony.algafood.api.controller.UsuarioController;
import com.kelsonthony.algafood.api.model.PedidoResumoModel;
import com.kelsonthony.algafood.domain.model.Pedido;

@Component
public class PedidoResumoModelAssembler 
	extends RepresentationModelAssemblerSupport<Pedido, PedidoResumoModel>{

	@Autowired
	private ModelMapper modelMapper;
	
	public PedidoResumoModelAssembler() {
		super(PedidoController.class, PedidoResumoModel.class);
	}
	
	public PedidoResumoModel toModel(Pedido pedido) {
		
		PedidoResumoModel pedidoResumoModel = createModelWithId(pedido.getCodigo(), pedido);
		
		modelMapper.map(pedido, pedidoResumoModel);
		
		pedidoResumoModel.add(WebMvcLinkBuilder.linkTo(PedidoController.class).withRel("pedidos"));
		
		/*
		 * pedidoResumoModel.getRestaurante().add(WebMvcLinkBuilder
		 * .linkTo(RestauranteController.class).withRel("restaurantes"));
		 */
		pedidoResumoModel.getRestaurante().add(WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(RestauranteController.class)
				.buscar(pedido.getRestaurante().getId()))
				.withSelfRel());
		
		pedidoResumoModel.getCliente().add(WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class)
				.buscar(pedido.getCliente().getId()))
				.withSelfRel());
		
		return pedidoResumoModel;
	}
	
	
}

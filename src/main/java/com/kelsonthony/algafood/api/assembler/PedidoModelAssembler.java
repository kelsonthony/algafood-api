package com.kelsonthony.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.kelsonthony.algafood.api.controller.CidadeController;
import com.kelsonthony.algafood.api.controller.FormaPagamentoController;
import com.kelsonthony.algafood.api.controller.PedidoController;
import com.kelsonthony.algafood.api.controller.RestauranteController;
import com.kelsonthony.algafood.api.controller.RestauranteProdutoController;
import com.kelsonthony.algafood.api.controller.UsuarioController;
import com.kelsonthony.algafood.api.model.PedidoModel;
import com.kelsonthony.algafood.domain.model.Pedido;

@Component
public class PedidoModelAssembler
	extends RepresentationModelAssemblerSupport<Pedido, PedidoModel>{

	@Autowired
	private ModelMapper modelMapper;
	
	public PedidoModelAssembler() {
		super(PedidoController.class, PedidoModel.class);
	}

	@Override
	public PedidoModel toModel(Pedido pedido) {
		
		PedidoModel pedidoModel = createModelWithId(pedido.getCodigo(), pedido);
		
		modelMapper.map(pedido, pedidoModel);
		
		pedidoModel.add(WebMvcLinkBuilder.linkTo(PedidoController.class).withRel("pedidos"));
		
		pedidoModel.getRestaurante().add(WebMvcLinkBuilder.linkTo(
				WebMvcLinkBuilder.methodOn(RestauranteController.class)
				.buscar(pedido.getRestaurante().getId()))
				.withSelfRel());
		
		pedidoModel.getCliente().add(WebMvcLinkBuilder.linkTo(
				WebMvcLinkBuilder.methodOn(UsuarioController.class)
				.buscar(pedido.getCliente().getId()))
				.withSelfRel());
		
		pedidoModel.getFormaPagamento().add(WebMvcLinkBuilder.linkTo(
				WebMvcLinkBuilder.methodOn(FormaPagamentoController.class)
				.buscar(pedido.getFormaPagamento().getId(), null))
				.withSelfRel());
		
		pedidoModel.getEndereco().getCidade().add(WebMvcLinkBuilder.linkTo(
				WebMvcLinkBuilder.methodOn(CidadeController.class)
				.buscar(pedido.getEndereco().getCidade().getId()))
				.withSelfRel());
		
		pedidoModel.getItens().forEach(item -> {
			item.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(
					RestauranteProdutoController.class)
					.buscar(pedido.getRestaurante().getId(), item.getProdutoId()))
					.withRel("produto"));
		});
		
		return pedidoModel;
	}
	
}

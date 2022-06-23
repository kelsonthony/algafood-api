package com.kelsonthony.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.kelsonthony.algafood.api.controller.PedidoController;
import com.kelsonthony.algafood.api.links.AlgaLinks;
import com.kelsonthony.algafood.api.model.PedidoResumoModel;
import com.kelsonthony.algafood.domain.model.Pedido;

@Component
public class PedidoResumoModelAssembler 
	extends RepresentationModelAssemblerSupport<Pedido, PedidoResumoModel>{

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AlgaLinks algaLinks;
	
	public PedidoResumoModelAssembler() {
		super(PedidoController.class, PedidoResumoModel.class);
	}
	
	public PedidoResumoModel toModel(Pedido pedido) {
		
		PedidoResumoModel pedidoResumoModel = createModelWithId(pedido.getCodigo(), pedido);
		
		modelMapper.map(pedido, pedidoResumoModel);
		
		pedidoResumoModel.add(algaLinks.linkToPedidos("pedidos"));
		
		pedidoResumoModel.getRestaurante().add(algaLinks.linkToRestaurante(
				pedido.getRestaurante().getId()));
		
		pedidoResumoModel.getCliente().add(algaLinks.linkToUsuario(
				pedido.getCliente().getId()));
		
		return pedidoResumoModel;
	}
	
	
}

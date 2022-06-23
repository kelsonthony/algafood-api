package com.kelsonthony.algafood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.kelsonthony.algafood.api.v1.controller.PedidoController;
import com.kelsonthony.algafood.api.v1.links.AlgaLinks;
import com.kelsonthony.algafood.api.v1.model.PedidoModel;
import com.kelsonthony.algafood.domain.model.Pedido;

@Component
public class PedidoModelAssembler
	extends RepresentationModelAssemblerSupport<Pedido, PedidoModel>{

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AlgaLinks algaLinks;
	
	public PedidoModelAssembler() {
		super(PedidoController.class, PedidoModel.class);
	}

	@Override
	public PedidoModel toModel(Pedido pedido) {
		
		PedidoModel pedidoModel = createModelWithId(pedido.getCodigo(), pedido);
		
		modelMapper.map(pedido, pedidoModel);
		
		
		pedidoModel.add(algaLinks.linkToPedidos("pedidos"));
		
		if (pedido.podeSerConfirmado()) {
			pedidoModel.add(algaLinks.linkToConfirmacaoPedido(pedido.getCodigo(), "confirmar"));
		}

		if (pedido.podeSerCancelado()) {
			pedidoModel.add(algaLinks.linkToCancelamentoPedido(pedido.getCodigo(), "cancelar"));
		}
		
		if (pedido.podeSerEntregue()) {
			pedidoModel.add(algaLinks.linkToEntregaPedido(pedido.getCodigo(), "entregar"));
		}
		
		pedidoModel.getRestaurante().add(algaLinks.linkToRestaurante(
				pedido.getRestaurante().getId()));
		
		pedidoModel.getCliente().add(algaLinks.linkToUsuario(
				pedido.getCliente().getId()));
		
		pedidoModel.getFormaPagamento().add(algaLinks.linkToFormaPagamento(
				pedido.getFormaPagamento().getId()));
		
		pedidoModel.getEndereco().getCidade().add(algaLinks.linkToCidade(
				pedido.getEndereco().getCidade().getId()));
		
		pedidoModel.getItens().forEach(item -> {
			item.add(algaLinks.linkToProduto(
					pedidoModel.getRestaurante().getId(), item.getProdutoId(), "produto"));
		});
		
		return pedidoModel;
	}
	
}

package com.kelsonthony.algafood.api.v1.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "pedidos")
@Getter
@Setter
public class PedidoModel extends RepresentationModel<PedidoModel> {

	private String codigo;

	private BigDecimal subtotal;

	private BigDecimal taxafrete;

	private BigDecimal valorTotal;

	private String status;

	private OffsetDateTime dataCriacao;

	private OffsetDateTime dataConfirmacao;

	private OffsetDateTime dataEntrega;

	private OffsetDateTime dataCancelamento;

	// private RestauranteResumoModel restaurante;
	private RestauranteApenasNomeModel restaurante;
	private UsuarioModel cliente;
	private FormaPagamentoModel formaPagamento;
	private EnderecoModel endereco;
	private List<ItemPedidoModel> itens;
}

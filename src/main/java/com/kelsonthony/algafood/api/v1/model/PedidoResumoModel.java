package com.kelsonthony.algafood.api.v1.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import lombok.Getter;
import lombok.Setter;

//@JsonFilter("pedidoFilter")
@Relation(collectionRelation = "pedidos")
@Getter
@Setter
public class PedidoResumoModel extends RepresentationModel<PedidoResumoModel> {

	private String codigo;

	private BigDecimal subtotal;

	private BigDecimal taxafrete;

	private BigDecimal valorTotal;

	private String status;

	private OffsetDateTime dataCriacao;

	// @ApiModelProperty(value = "Data de confirmação do pedido", example =
	// "2022-06-07T23:00:53Z")
	// private RestauranteResumoModel restaurante;
	private RestauranteApenasNomeModel restaurante;

	private UsuarioModel cliente;
	// private String nomeCliente;
}

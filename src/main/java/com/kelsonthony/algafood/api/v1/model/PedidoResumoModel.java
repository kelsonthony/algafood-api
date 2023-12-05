package com.kelsonthony.algafood.api.v1.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

//@JsonFilter("pedidoFilter")
@Relation(collectionRelation = "pedidos")
@Getter
@Setter
public class PedidoResumoModel extends RepresentationModel<PedidoResumoModel> {

	//@ApiModelProperty(value = "Código do pedido", example = "8d774bcf-b238-42f3-aef1-5fb388754d63")
	private String codigo;
	
	//@ApiModelProperty(value = "Subtotal do pedido", example = "289.90")
	private BigDecimal subtotal;
	
	//@ApiModelProperty(value = "Taxa frete do pedido", example = "10.00")
	private BigDecimal taxafrete;
	
	//@ApiModelProperty(value = "Valor total do pedido", example = "308.90")
	private BigDecimal valorTotal;
	
	//@ApiModelProperty(value = "Status do pedido", example = "CRIADO")
	private String status;
	
	//@ApiModelProperty(value = "Data de criação do pedido", example = "2022-06-07T23:00:53Z")
	private OffsetDateTime dataCriacao;
	
	////@ApiModelProperty(value = "Data de confirmação do pedido", example = "2022-06-07T23:00:53Z")
	//private RestauranteResumoModel restaurante;
	private RestauranteApenasNomeModel restaurante;
	
	private UsuarioModel cliente;
	//private String nomeCliente;
}

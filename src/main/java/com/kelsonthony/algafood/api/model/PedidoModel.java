package com.kelsonthony.algafood.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "pedidos")
@Getter
@Setter
public class PedidoModel extends RepresentationModel<PedidoModel> {

	@ApiModelProperty(value = "ID do pedido", example = "f9981ca4-5a5e-4da3-af04-933861df3e55")
	private String codigo;
	
	@ApiModelProperty(value = "Subtotal do pedido", example = "289.90")
	private BigDecimal subtotal;
	
	@ApiModelProperty(value = "Taxa frete do pedido", example = "10.00")
	private BigDecimal taxafrete;
	
	@ApiModelProperty(value = "Valor total do pedido", example = "308.90")
	private BigDecimal valorTotal;
	
	@ApiModelProperty(value = "Status do pedido", example = "CRIADO")
	private String status;
	
	@ApiModelProperty(value = "Data de criação do pedido", example = "2022-06-07T23:00:53Z")
	private OffsetDateTime dataCriacao;
	
	@ApiModelProperty(value = "Data de confirmação do pedido", example = "2022-06-07T23:00:53Z")
	private OffsetDateTime dataConfirmacao;
	
	@ApiModelProperty(value = "Data de entrega do pedido", example = "2022-06-07T23:00:53Z")
	private OffsetDateTime dataEntrega;
	
	@ApiModelProperty(value = "Data de cancelamento do pedido", example = "2022-06-07T23:00:53Z")
	private OffsetDateTime dataCancelamento;
	
	//private RestauranteResumoModel restaurante;
	private RestauranteApenasNomeModel restaurante;
	private UsuarioModel cliente;
	private FormaPagamentoModel formaPagamento;
	private EnderecoModel endereco;
	private List<ItemPedidoModel> itens;
}

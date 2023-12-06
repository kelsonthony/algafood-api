package com.kelsonthony.algafood.api.v1.model;

import java.math.BigDecimal;

import org.springframework.hateoas.RepresentationModel;

import lombok.Getter;
import lombok.Setter;

//@Relation(collectionRelation = "itemPedido")
@Getter
@Setter
public class ItemPedidoModel extends RepresentationModel<ItemPedidoModel> {

	
	private Long produtoId;
	
	
	private String produtoNome;
	
	
	private Integer quantidade;
	
	
	private BigDecimal precoUnitario;
	
	
	private BigDecimal precoTotal;
	
	
	private String observacao;
	
}

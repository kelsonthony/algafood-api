package com.kelsonthony.algafood.api.v1.model;

import java.math.BigDecimal;

import org.springframework.hateoas.RepresentationModel;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

//@Relation(collectionRelation = "itemPedido")
@Getter
@Setter
public class ItemPedidoModel extends RepresentationModel<ItemPedidoModel> {

	@ApiModelProperty(value = "ID da Produto", example = "1", position = 1)
	private Long produtoId;
	
	@ApiModelProperty(value = "Nome do produto", example = "Feijoada", position = 10)
	private String produtoNome;
	
	@ApiModelProperty(value = "Quantidade do produto", example = "4", position = 15)
	private Integer quantidade;
	
	@ApiModelProperty(value = "Valor unitário do produto", example = "10.56", position = 20)
	private BigDecimal precoUnitario;
	
	@ApiModelProperty(value = "Valor total", example = "10.56", position = 25)
	private BigDecimal precoTotal;
	
	@ApiModelProperty(value = "Alguma observação", example = "Com pouco sal", position = 30)
	private String observacao;
	
}

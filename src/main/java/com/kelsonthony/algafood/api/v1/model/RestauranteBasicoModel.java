package com.kelsonthony.algafood.api.v1.model;

import java.math.BigDecimal;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "restaurantes")
@Setter
@Getter
public class RestauranteBasicoModel extends RepresentationModel<RestauranteBasicoModel>{

	
	private Long id;
	
	
	private String nome;
	
	
	private BigDecimal taxaFrete;
	
	//@ApiModelProperty(position = 25)
	private CozinhaModel cozinha;
	
	
	
}

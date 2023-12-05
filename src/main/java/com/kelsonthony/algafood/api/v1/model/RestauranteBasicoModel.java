package com.kelsonthony.algafood.api.v1.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;

@Relation(collectionRelation = "restaurantes")
@Setter
@Getter
public class RestauranteBasicoModel extends RepresentationModel<RestauranteBasicoModel>{

	//@ApiModelProperty(value = "ID do restaurante", example = "1", position = 1)
	private Long id;
	
	//@ApiModelProperty(value = "Nome do restaurante", example = "Cozinha Mineira", position = 10)
	private String nome;
	
	//@ApiModelProperty(value = "Taxa Frete", example = "12.00", position = 20)
	private BigDecimal taxaFrete;
	
	//@ApiModelProperty(position = 25)
	private CozinhaModel cozinha;
	
	
	
}

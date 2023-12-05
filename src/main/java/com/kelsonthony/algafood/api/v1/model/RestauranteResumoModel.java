package com.kelsonthony.algafood.api.v1.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "restaurantes")
@Getter
@Setter
public class RestauranteResumoModel extends RepresentationModel<RestauranteResumoModel>{

	//@ApiModelProperty(value = "ID do restaurante", example = "1")
	private Long id;
	
	//@ApiModelProperty(value = "Nome do restaurante", example = "Comida Goiana")
	private String nome;
}

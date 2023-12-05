package com.kelsonthony.algafood.api.v1.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "cozinhas")
@Setter
@Getter
public class CozinhaModel extends RepresentationModel<CozinhaModel> {

	//@ApiModelProperty(value = "ID da cozinha", example = "1")
	private Long id;
	
	//@ApiModelProperty(example = "Cozinha Mexicana")
	private String nome;
	//private String cozinhaNome;
}

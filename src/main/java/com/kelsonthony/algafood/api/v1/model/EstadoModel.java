package com.kelsonthony.algafood.api.v1.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "estados")
@Getter
@Setter
public class EstadoModel extends RepresentationModel<EstadoModel>  {

	//@ApiModelProperty(example = "1", required = true, position = 1)
	private Long id;
	
	//@ApiModelProperty(example = "Distrito Federal", required = true, position = 10)
	private String nome;
}

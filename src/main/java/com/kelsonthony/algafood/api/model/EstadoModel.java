package com.kelsonthony.algafood.api.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "estados")
@Getter
@Setter
public class EstadoModel extends RepresentationModel<EstadoModel>  {

	@ApiModelProperty(example = "1", required = true, position = 1)
	private Long id;
	
	@ApiModelProperty(example = "Distrito Federal", required = true, position = 10)
	private String nome;
}

package com.kelsonthony.algafood.api.v1.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "cidades")
@Getter
@Setter
public class CidadeModel extends RepresentationModel<CidadeModel> {

	//@ApiModelProperty(value = "ID da cidade", example = "1")
	private Long id;
	
	//@ApiModelProperty(example = "Águas Claras")
	private String nome;
	
	private EstadoModel estado;
}

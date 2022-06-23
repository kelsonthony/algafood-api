package com.kelsonthony.algafood.api.v1.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "cidade")
@Getter
@Setter
public class CidadeResumoModel extends RepresentationModel<CidadeResumoModel> {

	@ApiModelProperty(value = "ID da cidade", example = "1")
	private Long id;
	
	@ApiModelProperty(value = "Nome da cidade", example = "Brasília")
	private String nome;
	
	@ApiModelProperty(value = "Nome do Estado", example = "Minas Gerais")
	private String estado;
}

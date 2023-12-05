package com.kelsonthony.algafood.api.v1.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "permissoes")
@Getter
@Setter
public class PermissaoModel extends RepresentationModel<PermissaoModel> {

	//@ApiModelProperty(value = "ID da permissão", example = "1", required = true)
	private Long id;
	
	//@ApiModelProperty(value = "Nome da permissão", example = "1", required = true)
	private String nome;
	
	//@ApiModelProperty(example = "Permite consultar cozinhas", required = true)
	private String descricao;
}

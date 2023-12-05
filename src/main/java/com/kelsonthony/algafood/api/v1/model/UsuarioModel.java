package com.kelsonthony.algafood.api.v1.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "usuarios")
@Getter
@Setter
public class UsuarioModel extends RepresentationModel<UsuarioModel> {
	
	//@ApiModelProperty(value = "ID do cliente", example = "1", position = 1)
	private Long id;

	//@ApiModelProperty(value = "Nome do Cliente", example = "João Silva", position = 10)
	private String nome;
	
	//@ApiModelProperty(value = "E-mail do cliente", example = "conta@mail.com", position = 15)
	private String email;

	
}

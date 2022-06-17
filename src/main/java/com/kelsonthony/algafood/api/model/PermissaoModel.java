package com.kelsonthony.algafood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PermissaoModel {

	@ApiModelProperty(value = "ID da permissão", example = "1", required = true)
	private Long id;
	
	@ApiModelProperty(value = "Nome da permissão", example = "1", required = true)
	private String nome;
	
	@ApiModelProperty(example = "Permite consultar cozinhas", required = true)
	private String descricao;
}

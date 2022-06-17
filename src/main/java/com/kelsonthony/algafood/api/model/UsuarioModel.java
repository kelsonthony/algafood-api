package com.kelsonthony.algafood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioModel {
	
	@ApiModelProperty(value = "ID do cliente", example = "1", position = 1)
	private Long id;

	@ApiModelProperty(value = "Nome do Cliente", example = "João Silva", position = 10)
	private String nome;
	
	@ApiModelProperty(value = "E-mail do cliente", example = "conta@mail.com", position = 15)
	private String email;

	
}

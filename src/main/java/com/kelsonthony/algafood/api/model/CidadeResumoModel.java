package com.kelsonthony.algafood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeResumoModel {

	@ApiModelProperty(value = "ID da cidade", example = "1")
	private Long id;
	
	@ApiModelProperty(value = "Nome da cidade", example = "Brasília")
	private String nome;
	
	@ApiModelProperty(value = "Nome do Estado", example = "Minas Gerais")
	private String estado;
}

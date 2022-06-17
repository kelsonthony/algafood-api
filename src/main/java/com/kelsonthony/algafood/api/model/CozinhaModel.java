package com.kelsonthony.algafood.api.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.kelsonthony.algafood.api.model.view.RestauranteView;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CozinhaModel {

	@ApiModelProperty(value = "ID da cozinha", example = "1")
	@JsonView(RestauranteView.Resumo.class)
	private Long id;
	
	@ApiModelProperty(example = "Cozinha Mexicana")
	@JsonView(RestauranteView.Resumo.class)
	private String nome;
	//private String cozinhaNome;
}

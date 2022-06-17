package com.kelsonthony.algafood.api.controller;

import java.math.BigDecimal;

import com.kelsonthony.algafood.api.model.CozinhaModel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel("RestauranteBasicoModel")
@Getter
@Setter
public class RestauranteBasicoOpenApi {

	@ApiModelProperty(example = "1", position = 1)
	private Long id;
	
	@ApiModelProperty(example = "Restaurante Mineiro", position = 10)
	private String nome;
	
	@ApiModelProperty(example = "12.00", position = 15)
	private BigDecimal taxaFrete;
	
	@ApiModelProperty(position = 20)
	private CozinhaModel cozinha;
}

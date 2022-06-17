package com.kelsonthony.algafood.api.model.input;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RestauranteInput {

	@ApiModelProperty(example = "Restaurante Mineiro", position = 1, required = true)
	@NotBlank
	private String nome;

	@ApiModelProperty(example = "12.00", position = 10, required = true)
	@NotNull
	@PositiveOrZero
	private BigDecimal taxaFrete;

	@ApiModelProperty(position = 15, required = true)
	@Valid
	@NotNull
	private CozinhaIdInput cozinha;
	
	@ApiModelProperty(example = "ativo", position = 20, required = true)
	private Boolean ativo;
	
	@ApiModelProperty(example = "aberto", position = 25, required = true)
	private Boolean aberto;

	@ApiModelProperty(position = 30, required = true)
	@Valid
	@NotNull
	private EnderecoInput endereco;
	
}

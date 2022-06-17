package com.kelsonthony.algafood.api.model.input;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CozinhaInput {

	@ApiModelProperty(example = "Cozinha Australiana", required = true)
	@NotBlank
	private String nome;
}

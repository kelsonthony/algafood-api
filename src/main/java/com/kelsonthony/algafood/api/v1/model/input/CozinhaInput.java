package com.kelsonthony.algafood.api.v1.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CozinhaInput {

	//@ApiModelProperty(example = "Cozinha Australiana", required = true)
	@NotBlank
	private String nome;
}

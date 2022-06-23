package com.kelsonthony.algafood.api.v1.model.input;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GrupoInput {

	private Long id;

	@ApiModelProperty(example = "Gerente", required = true)
	@NotBlank
	private String nome;


}

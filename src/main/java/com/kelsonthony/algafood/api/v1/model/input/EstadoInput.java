package com.kelsonthony.algafood.api.v1.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class EstadoInput {

	//@ApiModelProperty(example = "Distrito Federal", required = true)
	@NotBlank
	private String nome;
}

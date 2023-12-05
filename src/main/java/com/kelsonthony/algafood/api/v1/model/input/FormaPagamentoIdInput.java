package com.kelsonthony.algafood.api.v1.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class FormaPagamentoIdInput {

	//@ApiModelProperty(example = "2", required = true)
	@NotNull
	private Long id;
}

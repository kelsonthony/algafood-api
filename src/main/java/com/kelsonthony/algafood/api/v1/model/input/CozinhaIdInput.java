package com.kelsonthony.algafood.api.v1.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CozinhaIdInput {

	//@ApiModelProperty(example = "1", required = true)
	@NotNull
	private Long id;
}

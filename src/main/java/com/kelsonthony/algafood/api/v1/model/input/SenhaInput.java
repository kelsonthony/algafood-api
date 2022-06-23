package com.kelsonthony.algafood.api.v1.model.input;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SenhaInput {

	@ApiModelProperty(value = "Senha atual do usuário", example = "123345", required = true, position = 5)
	@NotBlank
	private String senhaAtual;
	
	@ApiModelProperty(value = "Senha nova do usuário", example = "123345", required = true, position = 10)
	@NotBlank
	private String novaSenha;
}

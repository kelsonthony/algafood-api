package com.kelsonthony.algafood.api.v1.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UsuarioComSenhaInput extends UsuarioInput {

	//@ApiModelProperty(value = "Senha do usuário", example = "123456", required = true)
	@NotBlank
	private String senha;


}

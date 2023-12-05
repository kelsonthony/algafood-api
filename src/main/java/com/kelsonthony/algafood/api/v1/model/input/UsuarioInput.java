package com.kelsonthony.algafood.api.v1.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UsuarioInput {

	//@ApiModelProperty(value = "Nome do usuário", example = "João Silva", required = true)
	@NotBlank
	private String nome;

	//@ApiModelProperty(value = "E-mail do usuário", example = "meuemail@mail.com", required = true)
	@NotBlank
	@Email
	private String email;
	
	//@JsonIgnore
	//private OffsetDateTime dataCadastro;
	
	//@JsonIgnore
	//private List<Grupo> grupos = new ArrayList<>();

}

package com.kelsonthony.algafood.api.model.input;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kelsonthony.algafood.domain.model.Grupo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioInput {

	@NotBlank
	private String nome;

	@NotBlank
	@Email
	private String email;
	
	//@JsonIgnore
	//private OffsetDateTime dataCadastro;
	
	//@JsonIgnore
	//private List<Grupo> grupos = new ArrayList<>();

}

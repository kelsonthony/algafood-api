package com.kelsonthony.algafood.api.v1.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.kelsonthony.algafood.api.v1.model.UsuarioModel;

import lombok.Data;


@Data
public class UsuariosModelOpenApi {

	private UsuariosModelEmbeddedModelOpenApi _embedded;
	private Links _links;
	

	@Data
	public class UsuariosModelEmbeddedModelOpenApi {
		
		private List<UsuarioModel> usuarios;
	}
}

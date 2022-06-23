package com.kelsonthony.algafood.api.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.kelsonthony.algafood.api.model.UsuarioModel;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("UsuariosModel")
@Data
public class UsuariosModelOpenApi {

	private UsuariosModelEmbeddedModelOpenApi _embedded;
	private Links _links;
	
	@ApiModel("UsuariosModelEmbeddedModel")
	@Data
	public class UsuariosModelEmbeddedModelOpenApi {
		
		private List<UsuarioModel> usuarios;
	}
}

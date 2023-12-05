package com.kelsonthony.algafood.api.v1.openapi.model;

import com.kelsonthony.algafood.api.v1.model.UsuarioModel;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

//@ApiModel("UsuariosModel")
@Data
public class UsuariosModelOpenApi {

	private UsuariosModelEmbeddedModelOpenApi _embedded;
	private Links _links;
	
	//@ApiModel("UsuariosModelEmbeddedModel")
	@Data
	public class UsuariosModelEmbeddedModelOpenApi {
		
		private List<UsuarioModel> usuarios;
	}
}

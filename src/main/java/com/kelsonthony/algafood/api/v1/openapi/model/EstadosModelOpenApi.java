package com.kelsonthony.algafood.api.v1.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.kelsonthony.algafood.api.v1.model.EstadoModel;

import lombok.Data;


@Data
public class EstadosModelOpenApi {

	private EstadoEmbeddedModelOpenApi _embedded;
	private Links _links;
	

	@Data
	public class EstadoEmbeddedModelOpenApi {
		
		private List<EstadoModel> estados;
	}
}

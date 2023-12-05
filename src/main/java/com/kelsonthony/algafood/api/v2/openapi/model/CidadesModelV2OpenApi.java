package com.kelsonthony.algafood.api.v2.openapi.model;

import com.kelsonthony.algafood.api.v2.model.CidadeModelV2;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

//@ApiModel("CidadesModel")
@Data
public class CidadesModelV2OpenApi {

	private CidadeEmbeddedModelOpenApi _embedded;
	private Links _links;
	
	//@ApiModel("CidadesEmbeddedModel")
	@Data
	public class CidadeEmbeddedModelOpenApi {
		
		private List<CidadeModelV2> cidades;
	}
}

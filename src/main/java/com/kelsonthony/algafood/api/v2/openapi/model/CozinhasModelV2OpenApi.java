package com.kelsonthony.algafood.api.v2.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.kelsonthony.algafood.api.v2.model.CozinhaModelV2;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("CozinhasModel")
@Data
public class CozinhasModelV2OpenApi {

	private CozinhasEmbeddedModelOpenApi _embedded;
	private Links _links;
	private PageModelV2OpenApi page;
	
	@ApiModel("CozinhasEmbeddedModel")
	@Data
	public class CozinhasEmbeddedModelOpenApi {
		
		private List<CozinhaModelV2> cozinhas;
	}
}

package com.kelsonthony.algafood.api.v2.openapi.model;

import com.kelsonthony.algafood.api.v2.model.CozinhaModelV2;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

//@ApiModel("CozinhasModel")
@Data
public class CozinhasModelV2OpenApi {

	private CozinhasEmbeddedModelOpenApi _embedded;
	private Links _links;
	private PageModelV2OpenApi page;
	
	//@ApiModel("CozinhasEmbeddedModel")
	@Data
	public class CozinhasEmbeddedModelOpenApi {
		
		private List<CozinhaModelV2> cozinhas;
	}
}

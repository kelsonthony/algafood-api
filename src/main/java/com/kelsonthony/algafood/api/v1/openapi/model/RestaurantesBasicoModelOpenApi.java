package com.kelsonthony.algafood.api.v1.openapi.model;

import com.kelsonthony.algafood.api.v1.model.RestauranteBasicoModel;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

//@ApiModel("RestaurantesBasicoModel")
@Data
public class RestaurantesBasicoModelOpenApi {
	
	private RestaurantesBasicoModelEmbeddedModelOpenApi _embedded;
	private Links _links;
	
	//@ApiModel("RestaurantesBasicoModelEmbeddedModel")
	@Data
	public class RestaurantesBasicoModelEmbeddedModelOpenApi {
		
		private List<RestauranteBasicoModel> restaurantes;
	}
}

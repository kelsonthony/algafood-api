package com.kelsonthony.algafood.api.v1.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.kelsonthony.algafood.api.v1.model.RestauranteBasicoModel;

import lombok.Data;


@Data
public class RestaurantesBasicoModelOpenApi {
	
	private RestaurantesBasicoModelEmbeddedModelOpenApi _embedded;
	private Links _links;
	

	@Data
	public class RestaurantesBasicoModelEmbeddedModelOpenApi {
		
		private List<RestauranteBasicoModel> restaurantes;
	}
}

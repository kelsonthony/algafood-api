package com.kelsonthony.algafood.api.model.mixin;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kelsonthony.algafood.domain.model.Restaurante;

public abstract class CozinhaMixin {

	@JsonIgnore
	private List<Restaurante> restaurantes;
	
}

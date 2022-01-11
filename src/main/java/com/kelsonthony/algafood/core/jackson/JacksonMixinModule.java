package com.kelsonthony.algafood.core.jackson;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.kelsonthony.algafood.api.model.mixin.CidadeMixin;
import com.kelsonthony.algafood.api.model.mixin.CozinhaMixin;
import com.kelsonthony.algafood.api.model.mixin.RestauranteMixin;
import com.kelsonthony.algafood.domain.model.Cidade;
import com.kelsonthony.algafood.domain.model.Cozinha;
import com.kelsonthony.algafood.domain.model.Restaurante;

@Component
public class JacksonMixinModule extends SimpleModule {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public JacksonMixinModule() {
		setMixInAnnotation(Restaurante.class, RestauranteMixin.class);
		setMixInAnnotation(Cozinha.class, CozinhaMixin.class);
		setMixInAnnotation(Cidade.class, CidadeMixin.class);
	}

}

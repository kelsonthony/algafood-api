package com.kelsonthony.algafood.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.kelsonthony.algafood.AlgafoodApiApplication;
import com.kelsonthony.algafood.domain.model.Restaurante;
import com.kelsonthony.algafood.domain.repository.RestauranteRepository;

public class BuscaRestauranteMain {

	public static void main(String[] args) {
		
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		RestauranteRepository restauranteRepository = applicationContext.getBean(RestauranteRepository.class);
		
		Restaurante restaurante = restauranteRepository.buscar(1L);
		
		System.out.println("Buscar Restaurante pelo ID " + restaurante.getNome());
	}

}

package com.kelsonthony.algafood.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.kelsonthony.algafood.AlgafoodApiApplication;
import com.kelsonthony.algafood.domain.model.Restaurante;
import com.kelsonthony.algafood.domain.repository.RestauranteRepository;

public class ConsultaRestauranteMain {

	public static void main(String[] args) {
		
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		RestauranteRepository restaurantes = applicationContext.getBean(RestauranteRepository.class);
		
		List<Restaurante> todosRestaurantes = restaurantes.todos();
		
		for ( Restaurante restaurante : todosRestaurantes ) {
			System.out.printf("%s - %f - -%s\n", restaurante.getNome(),
					restaurante.getTaxaFrete(), restaurante.getCozinha().getNome());
		}
	}

}

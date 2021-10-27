package com.kelsonthony.algafood.jpa;

import java.math.BigDecimal;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.kelsonthony.algafood.AlgafoodApiApplication;
import com.kelsonthony.algafood.domain.model.Cozinha;
import com.kelsonthony.algafood.domain.model.Restaurante;
import com.kelsonthony.algafood.domain.repository.RestauranteRepository;

public class InclusaoRestauranteMain {

	public static void main(String[] args) {
		
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		RestauranteRepository restauranteRepository = applicationContext.getBean(RestauranteRepository.class);
		
		
	 
		
		Restaurante restaurante1 = new Restaurante();
		restaurante1.setNome("Carne de Sol Novo aqui");
		restaurante1.setTaxaFrete(new BigDecimal(33.94));
		//restaurante1.setCozinha(new Cozinha());
		//restaurante1.getCozinha().setNome("Teste");
		
			
		 restaurante1 = restauranteRepository.salvar(restaurante1); 
		
		 		
		System.out.printf("%d - %s\n", restaurante1.getId(), restaurante1.getNome());
		
		
	}

}

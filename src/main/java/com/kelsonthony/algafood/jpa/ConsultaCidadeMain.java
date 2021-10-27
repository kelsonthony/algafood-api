package com.kelsonthony.algafood.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.kelsonthony.algafood.AlgafoodApiApplication;
import com.kelsonthony.algafood.domain.model.Cidade;
import com.kelsonthony.algafood.domain.repository.CidadeRepository;

public class ConsultaCidadeMain {

	public static void main(String[] args) {
		
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		CidadeRepository cidades = applicationContext.getBean(CidadeRepository.class);
		
		List<Cidade> todasCidades = cidades.todos();
		
		for ( Cidade cidade : todasCidades ) {
			System.out.println(cidade.getNome());
		}
	}

}

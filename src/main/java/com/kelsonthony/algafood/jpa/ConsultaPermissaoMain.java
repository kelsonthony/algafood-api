package com.kelsonthony.algafood.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.kelsonthony.algafood.AlgafoodApiApplication;
import com.kelsonthony.algafood.domain.model.Permissao;
import com.kelsonthony.algafood.domain.repository.PermissaoRepository;

public class ConsultaPermissaoMain {

	public static void main(String[] args) {
		
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		PermissaoRepository permissoes = applicationContext.getBean(PermissaoRepository.class);
		
		List<Permissao> todasPermissoes = permissoes.todas();
		
		for ( Permissao permissao : todasPermissoes ) {
			System.out.println(permissao.getNome());
		}
	}

}

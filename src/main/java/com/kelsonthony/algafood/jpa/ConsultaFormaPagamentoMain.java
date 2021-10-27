package com.kelsonthony.algafood.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.kelsonthony.algafood.AlgafoodApiApplication;
import com.kelsonthony.algafood.domain.model.FormaPagamento;
import com.kelsonthony.algafood.domain.repository.FormaPagamentoRepository;

public class ConsultaFormaPagamentoMain {

	public static void main(String[] args) {
		
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		FormaPagamentoRepository pagamentos = applicationContext.getBean(FormaPagamentoRepository.class);
		
		List<FormaPagamento> todosPagamentos = pagamentos.todos();
		
		for ( FormaPagamento formaPagamento : todosPagamentos ) {
			System.out.println(formaPagamento.getDescricao());
		}
	}

}

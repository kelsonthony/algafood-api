package com.kelsonthony.algafood;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kelsonthony.algafood.di.modelo.Cliente;
import com.kelsonthony.algafood.di.service.AtivacaoClienteService;

@Controller
public class MeuController {
	
	private AtivacaoClienteService ativacaoClienteService;
	
	public MeuController(AtivacaoClienteService ativacaoClienteService) {
		this.ativacaoClienteService = ativacaoClienteService;
		
		System.out.println("Meu primeiro controler: " + ativacaoClienteService);
	}

	@GetMapping("/hello")
	@ResponseBody
	public String hello() {
		
		Cliente joao = new Cliente("João", "joao@abc.com", "11111");
		
		ativacaoClienteService.ativar(joao);
		
		return "Hello World!";
	}
}

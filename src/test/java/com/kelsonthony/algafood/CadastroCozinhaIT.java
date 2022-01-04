package com.kelsonthony.algafood;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.kelsonthony.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.kelsonthony.algafood.domain.exception.EntidadeEmUsoException;
import com.kelsonthony.algafood.domain.model.Cozinha;
import com.kelsonthony.algafood.domain.service.CadastroCozinhaService;

@SpringBootTest
@TestPropertySource({"/application-test.properties"})
public class CadastroCozinhaIT {
	
	@Autowired
	private CadastroCozinhaService cadastroCozinhaService;

	@Test
	public void deveCadastroCozinhaComSucesso_QuandoCadastrarCozinhaComDadosCorretos() {
		
		//cenario
		Cozinha novaCozinha = new Cozinha();
		novaCozinha.setNome("Chinesa");
		
		//ação
		novaCozinha = cadastroCozinhaService.salvar(novaCozinha);
		
		//validação
		assertThat(novaCozinha).isNotNull();
		assertThat(novaCozinha.getId()).isNotNull();
	}
	
	@Test
	public void deveFalhar_QuandoCadastrarSemNome() {

		assertThrows(ConstraintViolationException.class, () -> {
			
			Cozinha novaCozinha = new Cozinha();
			novaCozinha.setNome(null);
			novaCozinha = cadastroCozinhaService.salvar(novaCozinha);
		});
		
	}
	
	@Test
	public void deveFalhar_QuandoExcluirCozinhaEmUso() {
		
		assertThrows(EntidadeEmUsoException.class, () -> {
			cadastroCozinhaService.excluir(1);
		});
	}
	
	@Test
	public void deveFalhar_QuandoExcluirCozinhaInexistente() {
		
		assertThrows(CozinhaNaoEncontradaException.class, () -> {
			cadastroCozinhaService.excluir(100L);
		});
	}
	
}

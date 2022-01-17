package com.kelsonthony.algafood.api.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RestauranteModel {

	private Long id;
	private String nome;
	private BigDecimal taxaFrete;
	private CozinhaModel cozinha;
	
	//private String nomeCozinha;
	//private String cozinhaNome;
	//private Long idCozinha;
	

}
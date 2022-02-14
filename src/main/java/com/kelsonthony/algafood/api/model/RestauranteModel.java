package com.kelsonthony.algafood.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.kelsonthony.algafood.api.model.view.RestauranteView;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RestauranteModel {

	@JsonView({RestauranteView.Resumo.class, RestauranteView.ApenasNome.class})
	private Long id;
	
	@JsonView({RestauranteView.Resumo.class, RestauranteView.ApenasNome.class})
	private String nome;
	
	@JsonView(RestauranteView.Resumo.class)
	private BigDecimal taxaFrete;
	
	@JsonView(RestauranteView.Resumo.class)
	private CozinhaModel cozinha;
	
	private Boolean ativo;
	private Boolean aberto;
	private EnderecoModel endereco;
	//@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	//@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss'Z'")
	@JsonIgnore
	private OffsetDateTime dataCadastro;
	
}

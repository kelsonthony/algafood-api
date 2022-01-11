package com.kelsonthony.algafood.api.model.mixin;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kelsonthony.algafood.domain.model.Cozinha;
import com.kelsonthony.algafood.domain.model.Endereco;
import com.kelsonthony.algafood.domain.model.FormaPagamento;
import com.kelsonthony.algafood.domain.model.Produto;

public abstract class RestauranteMixin {

	@JsonIgnoreProperties(value = "nome", allowGetters = true)
	private Cozinha cozinha;
	
	@JsonIgnore
	private Endereco endereco;
	
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	//@JsonIgnore
	private OffsetDateTime dataCadastro;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	//@JsonIgnore
	private OffsetDateTime dataAtualizacao;
	
	@JsonIgnore
	private List<FormaPagamento> formasPagamento;
	
	@JsonIgnore
	private List<Produto> produtos;
}

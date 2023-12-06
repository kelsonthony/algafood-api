package com.kelsonthony.algafood.api.v1.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "restaurantes")
@Setter
@Getter
public class RestauranteModel extends RepresentationModel<RestauranteModel> {

	
	//@JsonView({RestauranteView.Resumo.class, RestauranteView.ApenasNome.class})
	private Long id;
	
	
	//@JsonView({RestauranteView.Resumo.class, RestauranteView.ApenasNome.class})
	private String nome;
	
	
	//@JsonView(RestauranteView.Resumo.class)
	private BigDecimal taxaFrete;
	
	
	//@JsonView(RestauranteView.Resumo.class)
	private CozinhaModel cozinha;
	
	
	private Boolean ativo;
	
	
	private Boolean aberto;
	
	
	private EnderecoModel endereco;
	//@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	//@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss'Z'")
	@JsonIgnore
	private OffsetDateTime dataCadastro;
	
}

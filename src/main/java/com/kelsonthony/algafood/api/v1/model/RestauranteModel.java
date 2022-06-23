package com.kelsonthony.algafood.api.v1.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "restaurantes")
@Setter
@Getter
public class RestauranteModel extends RepresentationModel<RestauranteModel> {

	@ApiModelProperty(value = "ID do restaurante", example = "1", position = 1)
	//@JsonView({RestauranteView.Resumo.class, RestauranteView.ApenasNome.class})
	private Long id;
	
	@ApiModelProperty(value = "Nome do restaurante", example = "Cozinha Mineira", position = 10)
	//@JsonView({RestauranteView.Resumo.class, RestauranteView.ApenasNome.class})
	private String nome;
	
	@ApiModelProperty(value = "Taxa Frete", example = "12.00", position = 20)
	//@JsonView(RestauranteView.Resumo.class)
	private BigDecimal taxaFrete;
	
	@ApiModelProperty(position = 25)
	//@JsonView(RestauranteView.Resumo.class)
	private CozinhaModel cozinha;
	
	@ApiModelProperty(example = "ativo", position = 30)
	private Boolean ativo;
	
	@ApiModelProperty(example = "aberto", position = 35)
	private Boolean aberto;
	
	@ApiModelProperty(position = 40)
	private EnderecoModel endereco;
	//@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	//@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss'Z'")
	@JsonIgnore
	private OffsetDateTime dataCadastro;
	
}

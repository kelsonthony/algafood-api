package com.kelsonthony.algafood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoModel {

	@ApiModelProperty(value = "CEP da entrega", example = "71925-360")
	private String cep;	
	
	@ApiModelProperty(value = "Logradouro", example = "Rua FLoriano Peixoto")
	private String logradouro;
	
	@ApiModelProperty(value = "Número da rua", example = "10")
	private String numero;
	
	@ApiModelProperty(value = "Complemento do enderço", example = "Em frente ao Shopping")
	private String complemento;
	
	@ApiModelProperty(value = "Bairro", example = "Águas Claras")
	private String bairro;
	
	private CidadeResumoModel cidade;
}

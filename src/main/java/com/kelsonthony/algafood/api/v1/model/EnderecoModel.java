package com.kelsonthony.algafood.api.v1.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "endereco")
@Getter
@Setter
public class EnderecoModel extends RepresentationModel<EnderecoModel> {

	//@ApiModelProperty(value = "CEP da entrega", example = "71925-360")
	private String cep;	
	
	//@ApiModelProperty(value = "Logradouro", example = "Rua FLoriano Peixoto")
	private String logradouro;
	
	//@ApiModelProperty(value = "Número da rua", example = "10")
	private String numero;
	
	//@ApiModelProperty(value = "Complemento do enderço", example = "Em frente ao Shopping")
	private String complemento;
	
	//@ApiModelProperty(value = "Bairro", example = "Águas Claras")
	private String bairro;
	
	private CidadeResumoModel cidade;
}

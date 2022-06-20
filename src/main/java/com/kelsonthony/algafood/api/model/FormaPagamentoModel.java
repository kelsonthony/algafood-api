package com.kelsonthony.algafood.api.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "formasPagamento")
@Getter
@Setter
public class FormaPagamentoModel extends RepresentationModel<FormaPagamentoModel> {
	
	@ApiModelProperty(value = "ID do pagamento", example = "1")
	private Long id;
	
	@ApiModelProperty(example = "Cartão de Crédito")
	private String descricao;

}

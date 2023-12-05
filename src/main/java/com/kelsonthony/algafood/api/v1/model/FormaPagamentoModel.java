package com.kelsonthony.algafood.api.v1.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "formasPagamento")
@Getter
@Setter
public class FormaPagamentoModel extends RepresentationModel<FormaPagamentoModel> {
	
	//@ApiModelProperty(value = "ID do pagamento", example = "1")
	private Long id;
	
	//@ApiModelProperty(example = "Cartão de Crédito")
	private String descricao;

}

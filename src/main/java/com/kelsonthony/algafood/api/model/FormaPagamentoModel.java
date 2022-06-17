package com.kelsonthony.algafood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormaPagamentoModel {
	
	@ApiModelProperty(value = "ID do pagamento", example = "1")
	private Long id;
	
	@ApiModelProperty(example = "Cartão de Crédito")
	private String descricao;

}

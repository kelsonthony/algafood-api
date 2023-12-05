package com.kelsonthony.algafood.api.v1.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;


@Getter
@Setter
public class ItemPedidoInput {

	//@ApiModelProperty(example = "2", required = true)
	@Valid
	@NotNull
	private Long produtoId;

	//@ApiModelProperty(example = "2", required = true)
	@NotNull
	@PositiveOrZero
	private Integer quantidade;

	//@ApiModelProperty(example = "Com pouca pimenta", required = false)
	private String observacao;

}

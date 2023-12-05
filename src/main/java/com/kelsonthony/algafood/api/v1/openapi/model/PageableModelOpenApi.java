package com.kelsonthony.algafood.api.v1.openapi.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

//@ApiModel("Pageable")
@Getter
@Setter
public class PageableModelOpenApi {

	//@ApiModelProperty(example = "0", value = "Número da página começa em 0")
	private int page;
	
	//@ApiModelProperty(example = "10", value = "Quantidade de elementos por página")
	private int size;
	
	//@ApiModelProperty(example = "nome,desc", value = "Nome da propriedade para ordenação")
	private List<String> sort;
}

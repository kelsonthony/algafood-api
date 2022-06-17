package com.kelsonthony.algafood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FotoProdutoModel {

	@ApiModelProperty(example = "e81e3f23-9d43-429e-b82a-020a20f94544_prime-rib.jpg")
	private String nomeArquivo;
	
	@ApiModelProperty(example = "Prime Rib ao Ponto")
	private String descricao;
	
	@ApiModelProperty(example = "image/jpeg")
	private String contentType;
	
	@ApiModelProperty(example = "26613")
	private Long tamanho;
}

package com.kelsonthony.algafood.api.v1.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "fotos")
@Getter
@Setter
public class FotoProdutoModel extends RepresentationModel<FotoProdutoModel> {

	//@ApiModelProperty(example = "e81e3f23-9d43-429e-b82a-020a20f94544_prime-rib.jpg")
	private String nomeArquivo;
	
	//@ApiModelProperty(example = "Prime Rib ao Ponto")
	private String descricao;
	
	//@ApiModelProperty(example = "image/jpeg")
	private String contentType;
	
	//@ApiModelProperty(example = "26613")
	private Long tamanho;
}

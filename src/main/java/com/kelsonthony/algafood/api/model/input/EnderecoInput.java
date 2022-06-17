package com.kelsonthony.algafood.api.model.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoInput {

	@ApiModelProperty(example = "71953-606", required = true)
	@NotBlank
	private String cep;	
	
	@ApiModelProperty(example = "Avenida Paulista ", required = true)
	@NotBlank
	private String logradouro;
	
	@ApiModelProperty(example = "7", required = true)
	@NotBlank
	private String numero;
	
	@ApiModelProperty(example = "Casa 1")
	private String complemento;
	
	@ApiModelProperty(example = "Águas Claras", required = true)
	@NotBlank
	private String bairro;
	
	@Valid
	@NotNull
	private CidadeIdInput cidade;
}

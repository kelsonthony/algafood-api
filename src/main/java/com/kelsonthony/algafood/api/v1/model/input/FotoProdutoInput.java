package com.kelsonthony.algafood.api.v1.model.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import com.kelsonthony.algafood.core.validation.FileContentType;
import com.kelsonthony.algafood.core.validation.FileSize;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FotoProdutoInput {

	@ApiParam(hidden = true)
	@NotNull
	@FileSize(max = "500KB")
	@FileContentType(allowed = { MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE })
	private MultipartFile arquivo;
	
	@ApiModelProperty(value = "Descrição da foto do produto", required = true)
	@NotBlank
	private String descricao;
	
}

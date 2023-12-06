package com.kelsonthony.algafood.domain.filter;

import java.time.OffsetDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PedidoFilter {

	
	private Long clienteId;
	
	
	private Long restauranteId;
	
//	@ApiModelProperty(example = "2019-10-30T00:00:00Z",
//	        value = "Data/hora de criação inicial para filtro da pesquisa")
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private OffsetDateTime dataCriacaoInicio;
	
//	@ApiModelProperty(example = "2019-11-01T10:00:00Z",
//	        value = "Data/hora de criação final para filtro da pesquisa")
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private OffsetDateTime dataCriacaoFim;
}

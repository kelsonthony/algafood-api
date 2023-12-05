package com.kelsonthony.algafood.api.v1.openapi.controller;

import com.kelsonthony.algafood.api.v1.model.EstatisticasModel;
import com.kelsonthony.algafood.domain.filter.VendaDiariaFilter;
import com.kelsonthony.algafood.domain.model.dto.VendaDiaria;
import org.springframework.http.ResponseEntity;

import java.util.List;

//@Api(tags = "Estatísticas")
public interface EstatisticasControllerOpenApi {

	//@ApiOperation("Consulta estatísticas de vendas diárias")
	//@ApiImplicitParams({
		//@ApiImplicitParam(name = "restauranteId", value = "ID do restaurante",
				//example = "1", dataType = "int"),
		//@ApiImplicitParam(name = "dataCriacaoInicio", value = "Data/hora inicial da criação do pedido",
		//example = "2019-12-01T00:00:00Z", dataType = "date-time"),
		//@ApiImplicitParam(name = "dataCriacaoFim", value = "Data/hora final da criação do pedido",
		//example = "2019-12-01T00:00:00Z", dataType = "date-time")
	//})
	List<VendaDiaria> consultarVendasDiarias(
			VendaDiariaFilter filtro,
			//@ApiParam(value = "Deslocamento de horário a ser considerado na consulta em relação ao UTC",
			//defaultValue = "+00:00")
			String timeOffset);
	
	ResponseEntity<byte[]> consultarVendasDiariasPDF(VendaDiariaFilter filtro,
			String timeOffset);
	
	//@ApiOperation(value = "Estatísticas", hidden = true)
	EstatisticasModel estatisticas();
}

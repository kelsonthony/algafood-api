package com.kelsonthony.algafood.api.v1.openapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.kelsonthony.algafood.api.v1.model.EstatisticasModel;
import com.kelsonthony.algafood.domain.filter.VendaDiariaFilter;
import com.kelsonthony.algafood.domain.model.dto.VendaDiaria;

public interface EstatisticasControllerOpenApi {

	List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro,

			String timeOffset);

	ResponseEntity<byte[]> consultarVendasDiariasPDF(VendaDiariaFilter filtro, String timeOffset);

	EstatisticasModel estatisticas();
}

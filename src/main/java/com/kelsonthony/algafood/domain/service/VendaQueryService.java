package com.kelsonthony.algafood.domain.service;

import java.util.List;

import com.kelsonthony.algafood.domain.filter.VendaDiariaFilter;
import com.kelsonthony.algafood.domain.model.dto.VendaDiaria;

public interface VendaQueryService {

	List<VendaDiaria> consultarVendaDiarias(VendaDiariaFilter filtro, String timeOffset);
}

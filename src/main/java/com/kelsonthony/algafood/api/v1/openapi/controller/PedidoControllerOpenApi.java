package com.kelsonthony.algafood.api.v1.openapi.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

import com.kelsonthony.algafood.api.v1.model.PedidoModel;
import com.kelsonthony.algafood.api.v1.model.PedidoResumoModel;
import com.kelsonthony.algafood.api.v1.model.input.PedidoInput;
import com.kelsonthony.algafood.domain.filter.PedidoFilter;

public interface PedidoControllerOpenApi {

	PagedModel<PedidoResumoModel> pesquisar(PedidoFilter filtro, Pageable pageable);

	PedidoModel buscar(

			String codigoPedido);

	PedidoModel adicionar(

			PedidoInput pedidoInput);

}

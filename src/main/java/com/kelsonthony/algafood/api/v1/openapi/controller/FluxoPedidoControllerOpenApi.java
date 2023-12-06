package com.kelsonthony.algafood.api.v1.openapi.controller;

import org.springframework.http.ResponseEntity;

public interface FluxoPedidoControllerOpenApi {

	ResponseEntity<Void> confirmar(

			String codigoPedido);

	ResponseEntity<Void> cancelamento(

			String codigoPedido);

	ResponseEntity<Void> entregar(

			String codigoPedido);
}

package com.kelsonthony.algafood.api.v1.openapi.controller;

import org.springframework.http.ResponseEntity;

//@Api(tags = "Pedidos")
public interface FluxoPedidoControllerOpenApi {
	
	//@ApiOperation(value = "Confima um Pedido")
	//@ApiResponses({
		//@ApiResponse(code = 200, message ="Pedido Confirmado"),
		//@ApiResponse(code = 404, message ="Pedido não encontrado", response = Problem.class)
	//})
	ResponseEntity<Void> confirmar(
			//@ApiParam(value = "UUID de um pedido", example = "f9981ca4-5a5e-4da3-af04-933861df3e55", required = true)
			String codigoPedido);
	
	//@ApiOperation(value = "Cancela um Pedido")
	//@ApiResponses({
		//@ApiResponse(code = 200, message ="Pedido Cancelado"),
		//@ApiResponse(code = 404, message ="Pedido não encontrado", response = Problem.class)
	//})
	ResponseEntity<Void> cancelamento(
			//@ApiParam(value = "UUID de um pedido", example = "f9981ca4-5a5e-4da3-af04-933861df3e55", required = true)
			String codigoPedido);
	
	//@ApiOperation(value = "Entrega um Pedido")
	//@ApiResponses({
		//@ApiResponse(code = 200, message ="Pedido Entregue"),
		//@ApiResponse(code = 404, message ="Pedido não encontrado", response = Problem.class)
	//})
	ResponseEntity<Void> entregar(
			//@ApiParam(value = "UUID de um pedido", example = "f9981ca4-5a5e-4da3-af04-933861df3e55", required = true)
			String codigoPedido);
}

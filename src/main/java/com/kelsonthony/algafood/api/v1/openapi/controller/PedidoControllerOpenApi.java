package com.kelsonthony.algafood.api.v1.openapi.controller;

import com.kelsonthony.algafood.api.v1.model.PedidoModel;
import com.kelsonthony.algafood.api.v1.model.PedidoResumoModel;
import com.kelsonthony.algafood.api.v1.model.input.PedidoInput;
import com.kelsonthony.algafood.domain.filter.PedidoFilter;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

//@Api(tags = "Pedidos")
public interface PedidoControllerOpenApi {

	//@ApiOperation(value = "Pesquisa os pedidos")
	//@ApiImplicitParams({
		//@ApiImplicitParam(value = "Nomes das propriedades para filtar na resposta, seperados por vírgula",
				//name = "campos", paramType = "query", type = "string")
	//})
	PagedModel<PedidoResumoModel> pesquisar(PedidoFilter filtro, 
			Pageable pageable);
	
	//@ApiImplicitParams({
		//@ApiImplicitParam(value = "Nomes das propriedades para filtar na resposta, seperados por vírgula",
				//name = "campos", paramType = "query", type = "string")
	//})
	
	//@ApiOperation(value = "Busca um pedido por ID")
	//@ApiResponses({
		//@ApiResponse(code = 400, message ="ID do pedido inválido", response = Problem.class),
		//@ApiResponse(code = 404, message ="Pedido não encontrado", response = Problem.class)
	//})
	PedidoModel buscar(
			//@ApiParam(name = "ID de um pedido", example = "603cff87-51e0-4a8d-acc2-b042bafddebb", required = true)
			String codigoPedido);
	
	//@ApiOperation(value = "Adiciona um pedido Novo")
	//@ApiResponses({
		//@ApiResponse(code = 201, message ="Pedido Cadastrado")
	//})
	PedidoModel adicionar(
			//@ApiParam(name = "corpo", value = "Representação de um novo pedido", required = true)
			PedidoInput pedidoInput);	
	
}

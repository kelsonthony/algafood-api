package com.kelsonthony.algafood.api.v1.openapi.controller;

import com.kelsonthony.algafood.api.v1.model.FormaPagamentoModel;
import com.kelsonthony.algafood.api.v1.model.input.FormaPagamentoInput;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.ServletWebRequest;

//@Api(tags = "FormasPagamentos")
public interface FormaPagamentoControllerOpenApi {

	//@ApiOperation(value = "Lista as formas de pagamentos", response = FormasPagamentoModelOpenApi.class)
	ResponseEntity<CollectionModel<FormaPagamentoModel>> listar(ServletWebRequest request);
	
	//@ApiOperation(value = "Busca Forma de pagamento por ID")
	//@ApiResponses({
		//@ApiResponse(code = 400, message = "ID do pagamento inválido", response = Problem.class),
		//@ApiResponse(code = 404, message = "Forma de pagamento não encontrada", response = Problem.class)
	//})
	ResponseEntity<FormaPagamentoModel> buscar(
			//@ApiParam(value = "ID da forma de pagamento", example = "1", required = true)
			Long formaPagamentoId, 
			ServletWebRequest request);
	
	//@ApiOperation(value = "Cadastra uma nova forma de pagamento")
	//@ApiResponses({
		//@ApiResponse(code = 201, message ="Forma de Pagamento Cadastrado")
	//})
	FormaPagamentoModel adicionar(
			//@ApiParam(name = "corpo", value = "Representação de uma nova forma de pagamento", required = true)
			FormaPagamentoInput formaPagamentoInput);
	
	//@ApiOperation(value = "Atualiza uma forma de pagamento por ID")
	//@ApiResponses({
		//@ApiResponse(code = 200, message ="Forma de pagamento atualizad"),
		//@ApiResponse(code = 404, message ="Forma de pagamento não encontrado", response = Problem.class)
	//})
	FormaPagamentoModel atualizar(
			//@ApiParam(value = "ID de uma forma de pagamento", example = "1", required = true)
			Long pagamentoId,
			//@ApiParam(name = "corpo", value = "Representação de uma forma de pagamento com novos dados", required = true)
			FormaPagamentoInput formaPagamentoInput);
	
	//@ApiOperation(value = "Deleta uma forma de pagamento por ID")
	//@ApiResponses({
		//@ApiResponse(code = 204, message ="Forma de pagamento excluída"),
		//@ApiResponse(code = 404, message ="Forma de Pagemento não encontrada", response = Problem.class)
	//})
	void remover(
			//@ApiParam(value = "ID de uma forma de pagamento", example = "1", required = true)
			Long pagamentoId);
}

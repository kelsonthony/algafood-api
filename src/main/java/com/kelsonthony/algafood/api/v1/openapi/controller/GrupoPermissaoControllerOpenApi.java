package com.kelsonthony.algafood.api.v1.openapi.controller;

import com.kelsonthony.algafood.api.v1.model.PermissaoModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

//@Api(tags = "Grupos")
public interface GrupoPermissaoControllerOpenApi {

	//@ApiOperation(value = "Lista os permissões cadastradas")
	//@ApiResponses({
		//@ApiResponse(code = 400, message ="ID do grupo inválido", response = Problem.class),
		//@ApiResponse(code = 404, message ="Grupo não encontrado", response = Problem.class)
	//})
	CollectionModel<PermissaoModel> listar(
			//@ApiParam(value = "ID de um grupo", example = "1", required = true)
			Long grupoId);
	
	//@ApiOperation(value = "Desassocia uma permissão")
	//@ApiResponses({
		//@ApiResponse(code = 204, message = "Permissão Desassociada"),
		//@ApiResponse(code = 404, message = "Permissão ou grupo não encontrado")
	//})
	ResponseEntity<Void> desassociar(
			//@ApiParam(value = "ID de um grupo", example = "1", required = true)
			Long grupoId,
			//@ApiParam(value = "ID de um grupo", example = "1", required = true)
			Long permissaoId);
	
	//@ApiOperation(value = "Associa uma permissão")
	//@ApiResponses({
		//@ApiResponse(code = 204, message = "Permissão Associada"),
		//@ApiResponse(code = 404, message = "Permissão ou grupo não encontrado")
	//})
	ResponseEntity<Void> associar(
			//@ApiParam(value = "ID de um grupo", example = "1", required = true)
			Long grupoId,
			//@ApiParam(value = "ID de um grupo", example = "1", required = true)
			Long permissaoId);
}

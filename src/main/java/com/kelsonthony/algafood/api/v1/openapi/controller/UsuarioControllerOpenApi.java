package com.kelsonthony.algafood.api.v1.openapi.controller;

import com.kelsonthony.algafood.api.v1.model.UsuarioModel;
import com.kelsonthony.algafood.api.v1.model.input.SenhaInput;
import com.kelsonthony.algafood.api.v1.model.input.UsuarioComSenhaInput;
import com.kelsonthony.algafood.api.v1.model.input.UsuarioInput;
import org.springframework.hateoas.CollectionModel;

//@Api(tags = "Usuários")
public interface UsuarioControllerOpenApi {

	//@ApiOperation(value = "Lista os usuários cadastrados")
	CollectionModel<UsuarioModel> listar();

	//@ApiOperation(value = "Busca uma usuário por ID")
	//@ApiResponses({
		//@ApiResponse(code = 400, message ="ID do usuário inválido", response = Problem.class),
		//@ApiResponse(code = 404, message ="Usuário não encontrado", response = Problem.class)
	//})
	UsuarioModel buscar(
			//@ApiParam(value = "ID de um usuário", example = "1", required = true)
			Long usuarioId);

	//@ApiOperation(value = "Adiciona um usuário")
	//@ApiResponses({
		//@ApiResponse(code = 201, message ="Usuário criado com sucesso", response = Problem.class),
		//@ApiResponse(code = 400, message ="ID do usuário inválido", response = Problem.class),
		//@ApiResponse(code = 404, message ="Usuário não encontrado", response = Problem.class)
	//})
	UsuarioModel adicionar(
			//@ApiParam(value = "corpo", example = "Representação de um novo usuário", required = true)
			UsuarioComSenhaInput usuarioComSenhaInput);

	//@ApiOperation(value = "Atualiza um usuário por ID")
	//@ApiResponses({
		//@ApiResponse(code = 200, message ="Usuário atualizado com sucesso", response = Problem.class),
		//@ApiResponse(code = 400, message ="ID do usuário inválido", response = Problem.class),
		//@ApiResponse(code = 404, message ="Usuário não encontrado", response = Problem.class)
	//})
	UsuarioModel atualizar(
			//@ApiParam(value = "ID de um usuário", example = "1", required = true)
			Long usuarioId, 
			//@ApiParam(value = "corpo", example = "Representação de um usuário atualizado", required = true)
			UsuarioInput usuarioInput);

	//@ApiOperation(value = "Atualiza a senha do usuário")
	//@ApiResponses({
		//@ApiResponse(code = 204, message ="Senha atualizada com sucesso"),
		//@ApiResponse(code = 400, message ="ID do usuário inválido", response = Problem.class),
		//@ApiResponse(code = 404, message ="Usuário não encontrado", response = Problem.class)
	//})
	void alterarSenha(
			//@ApiParam(value = "ID de um usuário", example = "1", required = true)
			Long usuarioId,
			//@ApiParam(value = "corpo", example = "Representação a senha do usuário atualizada", required = true)
			SenhaInput senhaInput);
}

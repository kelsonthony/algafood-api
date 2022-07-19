package com.kelsonthony.algafood.api.v1.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.kelsonthony.algafood.api.v1.assembler.UsuarioInputDisassembler;
import com.kelsonthony.algafood.api.v1.assembler.UsuarioModelAssembler;
import com.kelsonthony.algafood.api.v1.model.UsuarioModel;
import com.kelsonthony.algafood.api.v1.model.input.SenhaInput;
import com.kelsonthony.algafood.api.v1.model.input.UsuarioComSenhaInput;
import com.kelsonthony.algafood.api.v1.model.input.UsuarioInput;
import com.kelsonthony.algafood.api.v1.openapi.controller.UsuarioControllerOpenApi;
import com.kelsonthony.algafood.core.security.CheckSecurity;
import com.kelsonthony.algafood.domain.model.Usuario;
import com.kelsonthony.algafood.domain.repository.UsuarioRepository;
import com.kelsonthony.algafood.domain.service.CadastroUsuarioService;

@RestController
@RequestMapping(value = "/v1/usuarios", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioController implements UsuarioControllerOpenApi {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private CadastroUsuarioService cadastroUsuarioService;

	@Autowired
	private UsuarioModelAssembler usuarioModelAssembler;

	@Autowired
	private UsuarioInputDisassembler usuarioInputDisassembler;

	@CheckSecurity.UsuariosGruposPermissoes.PodeConsultar
	@GetMapping
	public CollectionModel<UsuarioModel> listar() {
		List<Usuario> todosUsuarios = usuarioRepository.findAll();

		return usuarioModelAssembler.toCollectionModel(todosUsuarios);
		
	}

	@CheckSecurity.UsuariosGruposPermissoes.PodeConsultar
	@GetMapping("/{usuarioId}")
	public UsuarioModel buscar(@PathVariable Long usuarioId) {

		Usuario usuario = cadastroUsuarioService.buscarOuFalhar(usuarioId);

		return usuarioModelAssembler.toModel(usuario);
	}
	
	@CheckSecurity.UsuariosGruposPermissoes.PodeEditar
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UsuarioModel adicionar(@RequestBody @Valid UsuarioComSenhaInput usuarioComSenhaInput) {
		
		Usuario usuario = usuarioInputDisassembler.toDomainObject(usuarioComSenhaInput);
		
		usuario = cadastroUsuarioService.salvar(usuario);
		
		return usuarioModelAssembler.toModel(usuario);
	}
	
	@CheckSecurity.UsuariosGruposPermissoes.PodeAlterarUsuario
	@PutMapping("/{usuarioId}")
	public UsuarioModel atualizar(@PathVariable Long usuarioId,
			@RequestBody @Valid UsuarioInput usuarioInput) {
		
		Usuario usuarioAtual = cadastroUsuarioService.buscarOuFalhar(usuarioId);
		usuarioInputDisassembler.copyToDomainObject(usuarioInput, usuarioAtual);
		
		usuarioAtual = cadastroUsuarioService.salvar(usuarioAtual);
		
		return usuarioModelAssembler.toModel(usuarioAtual);
	}
	
	@CheckSecurity.UsuariosGruposPermissoes.PodeAlterarPropriaSenha
	@PutMapping("/{usuarioId}/senha")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void alterarSenha(@PathVariable Long usuarioId, @RequestBody @Valid SenhaInput senhaInput) {
		cadastroUsuarioService.alterarSenha(usuarioId, senhaInput.getSenhaAtual(), senhaInput.getNovaSenha());
	}
	
}







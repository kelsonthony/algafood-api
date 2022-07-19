package com.kelsonthony.algafood.api.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.kelsonthony.algafood.api.v1.assembler.PermissaoModelAssembler;
import com.kelsonthony.algafood.api.v1.links.AlgaLinks;
import com.kelsonthony.algafood.api.v1.model.PermissaoModel;
import com.kelsonthony.algafood.api.v1.openapi.controller.GrupoPermissaoControllerOpenApi;
import com.kelsonthony.algafood.core.security.AlgaSecurity;
import com.kelsonthony.algafood.core.security.CheckSecurity;
import com.kelsonthony.algafood.domain.model.Grupo;
import com.kelsonthony.algafood.domain.service.CadastroGrupoService;

@RestController
@RequestMapping(value = "/v1/grupos/{grupoId}/permissoes", produces = MediaType.APPLICATION_JSON_VALUE)
public class GrupoPermissaoController implements GrupoPermissaoControllerOpenApi {

	@Autowired
	private CadastroGrupoService cadastroGrupoService;
	
	@Autowired
	private PermissaoModelAssembler permissaoModelAssembler;
	
	@Autowired
	private AlgaLinks algaLinks;
	
	@Autowired
	private AlgaSecurity algaSecurity;
	
	@CheckSecurity.UsuariosGruposPermissoes.PodeConsultar
	@GetMapping
	public CollectionModel<PermissaoModel> listar(@PathVariable Long grupoId){
		Grupo grupo = cadastroGrupoService.buscarOuFalhar(grupoId);
		
		CollectionModel<PermissaoModel> permissoesModel 
			= permissaoModelAssembler.toCollectionModel(grupo.getPermissoes())
				.removeLinks();
				
		permissoesModel.add(algaLinks.linkToGrupoPermissoes(grupoId));
		
		if (algaSecurity.podeEditarUsuarioGrupoPermissoes()) {
			permissoesModel.add(algaLinks.linkToGrupoPermissaoAssociacao(grupoId, "associar"));
			
			permissoesModel.getContent().forEach(permissaomodel -> {
				permissaomodel.add(algaLinks.linkToGrupoPermissaoDesassociacao(
						grupoId, permissaomodel.getId(), "desassociar"));
			});
		}
		
		return permissoesModel;
	}
	
	@CheckSecurity.UsuariosGruposPermissoes.PodeEditar
	@DeleteMapping("/{permissaoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> desassociar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
		cadastroGrupoService.desassociarPermissao(grupoId, permissaoId);
		
		return ResponseEntity.noContent().build();
	}
	
	@CheckSecurity.UsuariosGruposPermissoes.PodeEditar
	@PutMapping("/{permissaoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> associar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
		cadastroGrupoService.associarPermissao(grupoId, permissaoId);
		
		return ResponseEntity.noContent().build();
	}
}












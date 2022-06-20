package com.kelsonthony.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.kelsonthony.algafood.api.controller.UsuarioController;
import com.kelsonthony.algafood.api.controller.UsuarioGrupoController;
import com.kelsonthony.algafood.api.model.UsuarioModel;
import com.kelsonthony.algafood.domain.model.Usuario;

@Component
public class UsuarioModelAssembler extends 
	RepresentationModelAssemblerSupport<Usuario, UsuarioModel>{

	@Autowired
	private ModelMapper modelMapper;
	
	public UsuarioModelAssembler() {
		super(UsuarioController.class, UsuarioModel.class);
	}

	public UsuarioModel toModel(Usuario usuario) {
		
		UsuarioModel usuarioModel = createModelWithId(usuario.getId(), usuario);
		
		modelMapper.map(usuario, usuarioModel);
		
		usuarioModel.add(WebMvcLinkBuilder.linkTo(
				(UsuarioController.class)).withRel("usuarios"));
		
		usuarioModel.add(WebMvcLinkBuilder.linkTo(
				WebMvcLinkBuilder.methodOn(UsuarioGrupoController.class)
				.listar(usuario.getId())).withRel("grupos-usuario"));
		
		/*
		 * usuarioModel.add(WebMvcLinkBuilder.linkTo(
		 * WebMvcLinkBuilder.methodOn(UsuarioController.class)
		 * .listar()).withRel("usuarios"));
		 * 
		 * usuarioModel.add(WebMvcLinkBuilder.linkTo(
		 * WebMvcLinkBuilder.methodOn(UsuarioGrupoController.class)
		 * .listar(usuario.getId())).withRel("grupos-usuario"));
		 */
		
		
		return usuarioModel;
	}

	@Override
	public CollectionModel<UsuarioModel> toCollectionModel(Iterable<? extends 
			Usuario> entities) {
		// TODO Auto-generated method stub
		return super.toCollectionModel(entities)
				.add(WebMvcLinkBuilder.linkTo(UsuarioController.class)
						.withSelfRel());
	}

} 

package com.kelsonthony.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.kelsonthony.algafood.api.controller.CozinhaController;
import com.kelsonthony.algafood.api.model.CozinhaModel;
import com.kelsonthony.algafood.domain.model.Cozinha;

@Component
public class CozinhaModelAssembler 
	extends RepresentationModelAssemblerSupport<Cozinha, CozinhaModel>{

	@Autowired
	private ModelMapper modelMapper;
	
	public CozinhaModelAssembler() {
		super(CozinhaController.class, CozinhaModel.class);
	}
	
	@Override
	public CozinhaModel toModel(Cozinha cozinha) {
		
		CozinhaModel cozinhaModel = createModelWithId(cozinha.getId(), cozinha);
		
		modelMapper.map(cozinha, cozinhaModel);
		
		cozinhaModel.add(WebMvcLinkBuilder.linkTo(CozinhaController.class).withRel("cozinhas"));
		
		return cozinhaModel;
	}
	
	
}

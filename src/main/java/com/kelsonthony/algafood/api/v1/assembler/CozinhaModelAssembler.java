package com.kelsonthony.algafood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.kelsonthony.algafood.api.v1.controller.CozinhaController;
import com.kelsonthony.algafood.api.v1.links.AlgaLinks;
import com.kelsonthony.algafood.api.v1.model.CozinhaModel;
import com.kelsonthony.algafood.core.security.AlgaSecurity;
import com.kelsonthony.algafood.domain.model.Cozinha;

@Component
public class CozinhaModelAssembler 
	extends RepresentationModelAssemblerSupport<Cozinha, CozinhaModel>{

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AlgaLinks algaLinks;
	
	@Autowired
	private AlgaSecurity algaSecurity;
	
	public CozinhaModelAssembler() {
		super(CozinhaController.class, CozinhaModel.class);
	}
	
	@Override
	public CozinhaModel toModel(Cozinha cozinha) {
		
		CozinhaModel cozinhaModel = createModelWithId(cozinha.getId(), cozinha);
		
		modelMapper.map(cozinha, cozinhaModel);
		
		if (algaSecurity.podeConsultarCozinhas()) {
			cozinhaModel.add(algaLinks.linkToCozinhas("cozinhas"));
		}
		
		return cozinhaModel;
	}
	
	
}

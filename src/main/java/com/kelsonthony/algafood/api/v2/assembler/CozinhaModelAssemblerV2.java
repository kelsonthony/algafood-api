package com.kelsonthony.algafood.api.v2.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.kelsonthony.algafood.api.v2.controller.CozinhaControllerV2;
import com.kelsonthony.algafood.api.v2.links.AlgaLinksV2;
import com.kelsonthony.algafood.api.v2.model.CozinhaModelV2;
import com.kelsonthony.algafood.domain.model.Cozinha;

@Component
public class CozinhaModelAssemblerV2 
        extends RepresentationModelAssemblerSupport<Cozinha, CozinhaModelV2> {

    @Autowired
    private ModelMapper modelMapper;
    
    @Autowired
    private AlgaLinksV2 algaLinks;
    
    public CozinhaModelAssemblerV2() {
        super(CozinhaControllerV2.class, CozinhaModelV2.class);
    }
    
    @Override
    public CozinhaModelV2 toModel(Cozinha cozinha) {
        CozinhaModelV2 cozinhaModel = createModelWithId(cozinha.getId(), cozinha);
        modelMapper.map(cozinha, cozinhaModel);
        
        cozinhaModel.add(algaLinks.linkToCozinhas("cozinhas"));
        
        return cozinhaModel;
    }   
}

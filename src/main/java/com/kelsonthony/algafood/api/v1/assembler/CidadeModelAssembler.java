package com.kelsonthony.algafood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.kelsonthony.algafood.api.v1.controller.CidadeController;
import com.kelsonthony.algafood.api.v1.links.AlgaLinks;
import com.kelsonthony.algafood.api.v1.model.CidadeModel;
import com.kelsonthony.algafood.core.security.AlgaSecurity;
import com.kelsonthony.algafood.domain.model.Cidade;

@Component
public class CidadeModelAssembler extends RepresentationModelAssemblerSupport<Cidade, CidadeModel> {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AlgaLinks algaLinks;

	@Autowired
	private AlgaSecurity algaSecurity;

	public CidadeModelAssembler() {
		super(CidadeController.class, CidadeModel.class);
	}

	@Override
	public CidadeModel toModel(Cidade cidade) {

		CidadeModel cidadeModel = createModelWithId(cidade.getId(), cidade);

		modelMapper.map(cidade, cidadeModel);

		if (algaSecurity.podeConsultarCidades()) {
			cidadeModel.add(algaLinks.linkToCidades("cidades"));
		}

		if (algaSecurity.podeConsultarEstados()) {
			cidadeModel.getEstado().add(algaLinks.linkToEstado(cidadeModel.getEstado().getId()));
		}

		return cidadeModel;
	}

	@Override
	public CollectionModel<CidadeModel> toCollectionModel(Iterable<? extends Cidade> entities) {
		CollectionModel<CidadeModel> collectionModel = super.toCollectionModel(entities);
		
		if (algaSecurity.podeConsultarCidades()) {
			collectionModel.add(algaLinks.linkToCidades());
		}
		
		return collectionModel;
	}

}

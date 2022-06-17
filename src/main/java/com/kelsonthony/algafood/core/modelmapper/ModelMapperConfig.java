package com.kelsonthony.algafood.core.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.kelsonthony.algafood.api.model.EnderecoModel;
import com.kelsonthony.algafood.api.model.input.ItemPedidoInput;
import com.kelsonthony.algafood.domain.model.Endereco;
import com.kelsonthony.algafood.domain.model.ItemPedido;

@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMapper() {

		// return new ModelMapper();

		/*
		 * var modelMapper = new ModelMapper();
		 * 
		 * modelMapper.createTypeMap(Restaurante.class, RestauranteModel.class)
		 * .addMapping(Restaurante::getTaxaFrete, RestauranteModel::setPrecoFrete);
		 * 
		 * return modelMapper;
		 */

		var modelMapper = new ModelMapper();

		var enderecoToEnderecoModelTypeMap = modelMapper.createTypeMap(
				Endereco.class, EnderecoModel.class);

		enderecoToEnderecoModelTypeMap.<String>addMapping(
				enderecoSrc -> enderecoSrc.getCidade().getEstado().getNome(),
				(enderecoModelDest, value) -> enderecoModelDest.getCidade().setEstado(value));
		
		modelMapper.createTypeMap(ItemPedidoInput.class, ItemPedido.class)
			.addMappings(mapper -> mapper.skip(ItemPedido::setId));

		return modelMapper;
	}
}

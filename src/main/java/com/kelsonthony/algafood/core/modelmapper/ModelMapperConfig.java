package com.kelsonthony.algafood.core.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMapper() {
		
		return new ModelMapper();
		/*
		 * var modelMapper = new ModelMapper();
		 * 
		 * modelMapper.createTypeMap(Restaurante.class, RestauranteModel.class)
		 * .addMapping(Restaurante::getTaxaFrete, RestauranteModel::setPrecoFrete);
		 * 
		 * return modelMapper;
		 */
	}
}

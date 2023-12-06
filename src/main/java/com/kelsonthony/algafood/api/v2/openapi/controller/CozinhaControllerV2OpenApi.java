package com.kelsonthony.algafood.api.v2.openapi.controller;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.kelsonthony.algafood.api.v2.model.CozinhaModelV2;
import com.kelsonthony.algafood.api.v2.model.input.CozinhaInputV2;


public interface CozinhaControllerV2OpenApi {

	public PagedModel<CozinhaModelV2> listar(@PageableDefault(size = 10) Pageable pageable);

	public CozinhaModelV2 buscar(@PathVariable Long cozinhaId);

	public CozinhaModelV2 adicionar(@RequestBody @Valid CozinhaInputV2 cozinhaInput);

	public CozinhaModelV2 atualizar(@PathVariable Long cozinhaId,
			@RequestBody @Valid CozinhaInputV2 cozinhaInput);

	public void remover(@PathVariable Long cozinhaId);
}

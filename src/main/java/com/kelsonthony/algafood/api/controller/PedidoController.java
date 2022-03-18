package com.kelsonthony.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.ImmutableMap;
import com.kelsonthony.algafood.api.assembler.PedidoInputDisassembler;
import com.kelsonthony.algafood.api.assembler.PedidoModelAssembler;
import com.kelsonthony.algafood.api.assembler.PedidoResumoModelAssembler;
import com.kelsonthony.algafood.api.model.PedidoModel;
import com.kelsonthony.algafood.api.model.PedidoResumoModel;
import com.kelsonthony.algafood.api.model.input.PedidoInput;
import com.kelsonthony.algafood.core.data.PageableTranslator;
import com.kelsonthony.algafood.domain.exception.NegocioException;
import com.kelsonthony.algafood.domain.filter.PedidoFilter;
import com.kelsonthony.algafood.domain.model.Pedido;
import com.kelsonthony.algafood.domain.model.Usuario;
import com.kelsonthony.algafood.domain.repository.PedidoRepository;
import com.kelsonthony.algafood.domain.service.EmissaoPedidoService;
import com.kelsonthony.algafood.infrastructure.repository.spec.PedidoSpecs;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoController {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private EmissaoPedidoService emissaoPedidoService;

	@Autowired
	private PedidoModelAssembler pedidoModelAssembler;

	@Autowired
	private PedidoResumoModelAssembler pedidoResumoModelAssembler;

	@Autowired
	private PedidoInputDisassembler pedidoInputDisassembler;

	@GetMapping
	public Page<PedidoResumoModel> pesquisar(PedidoFilter filtro, 
			@PageableDefault(size = 10) Pageable pageable) {

		pageable = traduzirPageable(pageable);
		
		Page<Pedido> pedidosPage = pedidoRepository.findAll(
				PedidoSpecs.usandoFiltro(filtro), pageable);

		List<PedidoResumoModel> pedidosModel = pedidoResumoModelAssembler
				.toCollectionModel(pedidosPage.getContent());
		
		Page<PedidoResumoModel> pedidosModelPage = new PageImpl<>(pedidosModel, 
				pageable, pedidosPage.getTotalElements());
		
		return pedidosModelPage;
	}

	/*
	 * @GetMapping public MappingJacksonValue listar(@RequestParam(required = false)
	 * String campos){
	 * 
	 * List<Pedido> todosPedidos = pedidoRepository.findAll();
	 * 
	 * List<PedidoResumoModel> pedidosModel =
	 * pedidoResumoModelAssembler.toCollectionModel(todosPedidos);
	 * 
	 * MappingJacksonValue pedidosWrapper = new MappingJacksonValue(pedidosModel);
	 * 
	 * SimpleFilterProvider filtersProvider = new SimpleFilterProvider();
	 * 
	 * filtersProvider.addFilter("pedidoFilter",
	 * SimpleBeanPropertyFilter.serializeAll());
	 * 
	 * if(StringUtils.isNotBlank(campos)) {
	 * filtersProvider.addFilter("pedidoFilter",
	 * SimpleBeanPropertyFilter.filterOutAllExcept(campos.split(",")));
	 * 
	 * }
	 * 
	 * pedidosWrapper.setFilters(filtersProvider);
	 * 
	 * return pedidosWrapper; }
	 */

	@GetMapping("/{codigoPedido}")
	public PedidoModel buscar(@PathVariable String codigoPedido) {
		Pedido pedido = emissaoPedidoService.buscarOuFalhar(codigoPedido);

		return pedidoModelAssembler.toModel(pedido);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PedidoModel adicionar(@Valid @RequestBody PedidoInput pedidoInput) {
		try {
			Pedido novoPedido = pedidoInputDisassembler.toDomainObject(pedidoInput);

			novoPedido.setCliente(new Usuario());
			novoPedido.getCliente().setId(1L);

			novoPedido = emissaoPedidoService.emitir(novoPedido);

			return pedidoModelAssembler.toModel(novoPedido);
		} catch (Exception e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}
	
	private Pageable traduzirPageable(Pageable apiPageable) {
		var mapeamento = ImmutableMap.of(
				"codigo", "codigo",
				"restaurante.nome", "restaurante.nome",
				"nomeCliente", "cliente.nome",
				"valorTotal", "valorTotal"
				);
		
		return PageableTranslator.translate(apiPageable, mapeamento);
	}
}

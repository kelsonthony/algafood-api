package com.kelsonthony.algafood.api.v1.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.kelsonthony.algafood.api.v1.assembler.PedidoInputDisassembler;
import com.kelsonthony.algafood.api.v1.assembler.PedidoModelAssembler;
import com.kelsonthony.algafood.api.v1.assembler.PedidoResumoModelAssembler;
import com.kelsonthony.algafood.api.v1.model.PedidoModel;
import com.kelsonthony.algafood.api.v1.model.PedidoResumoModel;
import com.kelsonthony.algafood.api.v1.model.input.PedidoInput;
import com.kelsonthony.algafood.api.v1.openapi.controller.PedidoControllerOpenApi;
import com.kelsonthony.algafood.core.data.PageWrapper;
import com.kelsonthony.algafood.core.data.PageableTranslator;
import com.kelsonthony.algafood.core.security.AlgaSecurity;
import com.kelsonthony.algafood.core.security.CheckSecurity;
import com.kelsonthony.algafood.domain.exception.NegocioException;
import com.kelsonthony.algafood.domain.filter.PedidoFilter;
import com.kelsonthony.algafood.domain.model.Pedido;
import com.kelsonthony.algafood.domain.model.Usuario;
import com.kelsonthony.algafood.domain.repository.PedidoRepository;
import com.kelsonthony.algafood.domain.service.EmissaoPedidoService;
import com.kelsonthony.algafood.infrastructure.repository.spec.PedidoSpecs;

@RestController
@RequestMapping(path = "/v1/pedidos", produces = MediaType.APPLICATION_JSON_VALUE)
public class PedidoController implements PedidoControllerOpenApi {

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
	
	@Autowired
	private PagedResourcesAssembler<Pedido> pagedResourcesAssembler;
	
	@Autowired
	private AlgaSecurity algaSecurity;

	@CheckSecurity.Pedidos.PodePesquisar
	@GetMapping
	public PagedModel<PedidoResumoModel> pesquisar(PedidoFilter filtro, 
			@PageableDefault(size = 10) Pageable pageable) {

		Pageable pageableTraduzido = traduzirPageable(pageable);
		
		Page<Pedido> pedidosPage = pedidoRepository.findAll(
				PedidoSpecs.usandoFiltro(filtro), pageableTraduzido);
		
		pedidosPage = new PageWrapper<>(pedidosPage, pageable);
		
		PagedModel<PedidoResumoModel> pedidosPagedModel = pagedResourcesAssembler
				.toModel(pedidosPage, pedidoResumoModelAssembler);

		return pedidosPagedModel;
	}

	@CheckSecurity.Pedidos.PodeBuscar
	@GetMapping("/{codigoPedido}")
	public PedidoModel buscar(@PathVariable String codigoPedido) {
		Pedido pedido = emissaoPedidoService.buscarOuFalhar(codigoPedido);

		return pedidoModelAssembler.toModel(pedido);
	}

	@CheckSecurity.Pedidos.PodeCriar
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PedidoModel adicionar(@Valid @RequestBody PedidoInput pedidoInput) {
		try {
			Pedido novoPedido = pedidoInputDisassembler.toDomainObject(pedidoInput);
			
			//Todo pegar o usuário autenticado

			novoPedido.setCliente(new Usuario());
			novoPedido.getCliente().setId(algaSecurity.getUsuarioId());

			novoPedido = emissaoPedidoService.emitir(novoPedido);

			return pedidoModelAssembler.toModel(novoPedido);
		} catch (Exception e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}
	
	private Pageable traduzirPageable(Pageable apiPageable) {
		var mapeamento = Map.of(
				"codigo", "codigo",
				"nomerestaurante", "restaurante.nome",
				"nomeCliente", "cliente.nome",
				"valorTotal", "valorTotal"
				);
		
		return PageableTranslator.translate(apiPageable, mapeamento);
	}
}

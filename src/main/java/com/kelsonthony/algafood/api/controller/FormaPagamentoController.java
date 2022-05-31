package com.kelsonthony.algafood.api.controller;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import com.kelsonthony.algafood.api.assembler.FormaPagamentoInputDisassembler;
import com.kelsonthony.algafood.api.assembler.FormaPagamentoModelAssembler;
import com.kelsonthony.algafood.api.model.FormaPagamentoModel;
import com.kelsonthony.algafood.api.model.input.FormaPagamentoInput;
import com.kelsonthony.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.kelsonthony.algafood.domain.exception.NegocioException;
import com.kelsonthony.algafood.domain.model.FormaPagamento;
import com.kelsonthony.algafood.domain.repository.FormaPagamentoRepository;
import com.kelsonthony.algafood.domain.service.CadastroFormaPagamentoService;

//@CrossOrigin
@RestController
@RequestMapping(value = "/pagamentos")
public class FormaPagamentoController {

	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository;
	
	@Autowired
	private CadastroFormaPagamentoService cadastroFormaPagamentoService;
	
	@Autowired
	private FormaPagamentoModelAssembler formaPagamentoModelAssembler;
	
	@Autowired
	private FormaPagamentoInputDisassembler formaPagamentoInputDisassembler;
	
	
	/**
	 * 
	 * @param request
	 * @return
	 * @GetMapping
	public ResponseEntity<List<FormaPagamentoModel>> listar() {
				
		List<FormaPagamento> todasFormasPagamentos = formaPagamentoRepository.findAll();
		
		List<FormaPagamentoModel> formasPagamentoModel = formaPagamentoModelAssembler
				.toCollectionModel(todasFormasPagamentos);
		
		return ResponseEntity.ok()
				//.cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS).cachePrivate())
				.cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS).cachePublic())
				//.cacheControl(CacheControl.noCache())
				//.cacheControl(CacheControl.noStore()) // sem cache
				.body(formasPagamentoModel);
	}
	 */
	
	@GetMapping
	public ResponseEntity<List<FormaPagamentoModel>> listar(ServletWebRequest request) {
		
		ShallowEtagHeaderFilter.disableContentCaching(request.getRequest());
		
		String eTag = "0";
		
		OffsetDateTime dataUltimaAtualizacao = formaPagamentoRepository.getDataUltimaAtualizacao();
		
		if(dataUltimaAtualizacao != null) {
			eTag = String.valueOf(dataUltimaAtualizacao.toEpochSecond());
		}
		
		if(request.checkNotModified(eTag)) {
			return null;
		}
		
		List<FormaPagamento> todasFormasPagamentos = formaPagamentoRepository.findAll();
		
		List<FormaPagamentoModel> formasPagamentoModel = formaPagamentoModelAssembler
				.toCollectionModel(todasFormasPagamentos);
		
		return ResponseEntity.ok()
				.cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS).cachePublic())
				.eTag(eTag)
				.body(formasPagamentoModel);
	}
	
	@GetMapping("/{formaPagamentoId}")
	public ResponseEntity<FormaPagamentoModel> buscar(@PathVariable Long formaPagamentoId, 
			ServletWebRequest request) {
		
		ShallowEtagHeaderFilter.disableContentCaching(request.getRequest());
		
		String eTag = "0";
		
		OffsetDateTime dataUltimaAtualizacao = formaPagamentoRepository
				.getDataUltimaAtualizacaoById(formaPagamentoId);
		
		if(dataUltimaAtualizacao != null) {
			eTag = String.valueOf(dataUltimaAtualizacao.toEpochSecond());
		}
		
		if(request.checkNotModified(eTag)) {
			return null;
		}
		
		FormaPagamento formaPagamento = cadastroFormaPagamentoService.buscarOuFalhar(formaPagamentoId);
		
		FormaPagamentoModel formaPagamentoModel = formaPagamentoModelAssembler
				.toModel(formaPagamento);
		
		return ResponseEntity.ok()
				.cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
				.eTag(eTag)
				.body(formaPagamentoModel);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public FormaPagamentoModel adicionar(@RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
		try {
			FormaPagamento formaPagamento = formaPagamentoInputDisassembler.toDomainObject(formaPagamentoInput);
			
			formaPagamento = cadastroFormaPagamentoService.salvar(formaPagamento);
			return  formaPagamentoModelAssembler.toModel(formaPagamento);
		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}
	
	@PutMapping("/{pagamentoId}")
	public FormaPagamentoModel atualizar(@PathVariable Long pagamentoId,
			@RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
		
		try {
			
			FormaPagamento formaPagamentoAtual = cadastroFormaPagamentoService.buscarOuFalhar(pagamentoId);
			
			formaPagamentoInputDisassembler.copyToDomainObject(formaPagamentoInput, formaPagamentoAtual);
			
			formaPagamentoAtual = cadastroFormaPagamentoService.salvar(formaPagamentoAtual);
			
			return formaPagamentoModelAssembler.toModel(formaPagamentoAtual);
			
		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}
	
	@DeleteMapping("/{pagamentoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long pagamentoId) {
		cadastroFormaPagamentoService.excluir(pagamentoId);
	}
}



















package com.kelsonthony.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.kelsonthony.algafood.api.assembler.FormaPagamentoInputDisassembler;
import com.kelsonthony.algafood.api.assembler.FormaPagamentoModelAssembler;
import com.kelsonthony.algafood.api.model.FormaPagamentoModel;
import com.kelsonthony.algafood.api.model.input.FormaPagamentoInput;
import com.kelsonthony.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.kelsonthony.algafood.domain.exception.NegocioException;
import com.kelsonthony.algafood.domain.model.FormaPagamento;
import com.kelsonthony.algafood.domain.repository.FormaPagamentoRepository;
import com.kelsonthony.algafood.domain.service.CadastroFormaPagamentoService;

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
	
	
	@GetMapping
	public List<FormaPagamentoModel> listar() {
		
		List<FormaPagamento> todasFormasPagamentos = formaPagamentoRepository.findAll();
		
		return formaPagamentoModelAssembler.toCollectionModel(todasFormasPagamentos);
	}
	
	@GetMapping("/{pagamentoId}")
	public FormaPagamentoModel buscar(@PathVariable Long pagamentoId) {
		
		FormaPagamento formaPagamento = cadastroFormaPagamentoService.buscarOuFalhar(pagamentoId);
		
		return formaPagamentoModelAssembler.toModel(formaPagamento);
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



















package com.kelsonthony.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kelsonthony.algafood.domain.exception.EntidadeEmUsoException;
import com.kelsonthony.algafood.domain.exception.FormaPagamentoNaoEncontradoException;
import com.kelsonthony.algafood.domain.model.FormaPagamento;
import com.kelsonthony.algafood.domain.repository.FormaPagamentoRepository;

@Service
public class CadastroFormaPagamentoService {

	private static final String MSG_FORMA_PAGAMENT_EM_USO = "Forma de Pagamento de código %d não pode ser removida, pois está em uso";

	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository;

	@Transactional
	public FormaPagamento salvar(FormaPagamento formaPagamento) {
		return formaPagamentoRepository.save(formaPagamento);
	}
	
	@Transactional
	public void excluir(Long pagamentoId) {
		try {
			formaPagamentoRepository.deleteById(pagamentoId);
			formaPagamentoRepository.flush();
		
		} catch (EmptyResultDataAccessException e) {
			throw new FormaPagamentoNaoEncontradoException(pagamentoId);
		
		} catch(DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format(MSG_FORMA_PAGAMENT_EM_USO, pagamentoId));
		}
	}
	
	public FormaPagamento buscarOuFalhar(Long pagamentoId) {
		return formaPagamentoRepository.findById(pagamentoId).orElseThrow(
				() -> new FormaPagamentoNaoEncontradoException(pagamentoId));
	}
}

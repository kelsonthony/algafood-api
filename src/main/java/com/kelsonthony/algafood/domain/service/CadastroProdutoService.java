package com.kelsonthony.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kelsonthony.algafood.domain.exception.EntidadeEmUsoException;
import com.kelsonthony.algafood.domain.exception.ProdutoNaoEncontradoException;
import com.kelsonthony.algafood.domain.model.Produto;
import com.kelsonthony.algafood.domain.repository.ProdutoRepository;

@Service
public class CadastroProdutoService {

	private static final String MSG_PRODUTO_EM_USO = "Produto de código %d não pode ser removido, pois está em uso";
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Transactional
	public Produto salvar(Produto produto) {
		return produtoRepository.save(produto);
	}
	
	@Transactional
	public void excluir(Long restauranteId, Long produtoId) {
		try {
			produtoRepository.deleteById(produtoId);
			produtoRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new ProdutoNaoEncontradoException(restauranteId, produtoId);
			
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format(MSG_PRODUTO_EM_USO, produtoId));
		}
	}
	
	public Produto buscarOuFalhar(Long restauranteId, Long produtoId) {
		return produtoRepository.findById(restauranteId, produtoId).orElseThrow(
				() -> new ProdutoNaoEncontradoException(restauranteId, produtoId));
	}
}

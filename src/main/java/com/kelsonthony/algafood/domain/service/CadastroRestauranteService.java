package com.kelsonthony.algafood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kelsonthony.algafood.domain.exception.EntidadeEmUsoException;
import com.kelsonthony.algafood.domain.exception.RestauranteNaoEncontradoException;
import com.kelsonthony.algafood.domain.model.Cidade;
import com.kelsonthony.algafood.domain.model.Cozinha;
import com.kelsonthony.algafood.domain.model.FormaPagamento;
import com.kelsonthony.algafood.domain.model.Restaurante;
import com.kelsonthony.algafood.domain.model.Usuario;
import com.kelsonthony.algafood.domain.repository.RestauranteRepository;

@Service
public class CadastroRestauranteService {

	private static final String MSG_RESTAURANTE_EM_USO = "Restaurante de código %d não pode ser removida, pois está em uso";

	@Autowired
	private RestauranteRepository restauranteRepository;

	@Autowired
	private CadastroCozinhaService cadastroCozinhaService;
	
	@Autowired
	private CadastroCidadeService cadastroCidadeService;
	
	@Autowired
	private CadastroFormaPagamentoService cadastroFormaPagamentoService;
	
	@Autowired
	private CadastroUsuarioService cadastroUsuarioService;

	@Transactional
	public Restaurante salvar(Restaurante restaurante) {
		Long cozinhaId = restaurante.getCozinha().getId();
		
		Long cidadeId = restaurante.getEndereco().getCidade().getId();
		
		Cozinha cozinha = cadastroCozinhaService.buscarOuFalhar(cozinhaId);
		
		Cidade cidade = cadastroCidadeService.buscarOuFalhar(cidadeId);
		
		restaurante.setCozinha(cozinha);
		restaurante.getEndereco().setCidade(cidade);

		return restauranteRepository.save(restaurante);
	}

	@Transactional
	public void excluir(Long restauranteId) {

		try {
			restauranteRepository.deleteById(restauranteId);
			restauranteRepository.flush();

		} catch (EmptyResultDataAccessException e) {
			throw new RestauranteNaoEncontradoException(restauranteId);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_RESTAURANTE_EM_USO, restauranteId));
		}
	}

	public Restaurante buscarOuFalhar(Long restauranteId) {
		return restauranteRepository.findById(restauranteId).orElseThrow(
				() -> new RestauranteNaoEncontradoException(restauranteId));
	}
	
	@Transactional
	public void ativar(Long restauranteId) {
		Restaurante restauranteAtual = buscarOuFalhar(restauranteId);
		
		//restauranteAtual.setAtivo(true);
		restauranteAtual.ativar();
	}
	
	@Transactional
	public void inativar(Long restauranteId) {
		Restaurante restauranteAtual = buscarOuFalhar(restauranteId);
		
		//restauranteAtual.setAtivo(false);
		restauranteAtual.inativar();
	}
	
	@Transactional
	public void ativar(List<Long> restauranteIds) {
		restauranteIds.forEach(this::ativar);
	}
	
	@Transactional
	public void inativar(List<Long> restauranteIds) {
		restauranteIds.forEach(this::inativar);
	}
	
	@Transactional
	public void desassociarFormaPagamento(Long restauranteId, Long formaPagamentoId) {
		
		Restaurante restaurante = buscarOuFalhar(restauranteId);
		FormaPagamento formaPagamento = cadastroFormaPagamentoService.buscarOuFalhar(formaPagamentoId);
		
		restaurante.removerFormaPagamento(formaPagamento);
	}
	
	@Transactional
	public void associarFormaPagamento(Long restauranteId, Long formaPagamentoId) {
		
		Restaurante restaurante = buscarOuFalhar(restauranteId);
		FormaPagamento formaPagamento = cadastroFormaPagamentoService.buscarOuFalhar(formaPagamentoId);
		
		restaurante.adicionarFormaPagamento(formaPagamento);
	}
	
	@Transactional
	public void abrir(Long restauranteId) {
		Restaurante restaurante = buscarOuFalhar(restauranteId);
		
		restaurante.abrir();
	}
	
	@Transactional
	public void fechar(Long restauranteId) {
		Restaurante restaurante = buscarOuFalhar(restauranteId);
		
		restaurante.fechar();
	}
	
	@Transactional
	public void desassociarReponsavel(Long restauranteId, Long usuarioId) {
		Restaurante restaurante = buscarOuFalhar(restauranteId);
		Usuario usuario = cadastroUsuarioService.buscarOuFalhar(usuarioId);
		
		restaurante.removerResponsavel(usuario);
	}
	
	@Transactional
	public void associarReponsavel(Long restauranteId, Long usuarioId) {
		Restaurante restaurante = buscarOuFalhar(restauranteId);
		Usuario usuario = cadastroUsuarioService.buscarOuFalhar(usuarioId);
		
		restaurante.adicionarResponsavel(usuario);
	}
}





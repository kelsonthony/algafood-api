package com.kelsonthony.algafood.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kelsonthony.algafood.domain.exception.EntidadeEmUsoException;
import com.kelsonthony.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.kelsonthony.algafood.domain.model.Restaurante;
import com.kelsonthony.algafood.domain.repository.RestauranteRepository;
import com.kelsonthony.algafood.domain.service.CadastroRestauranteService;

@RestController
//@RequestMapping(value = "/cozinhas", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
@RequestMapping(value = "/restaurantes")
public class RestauranteController {

	@Autowired
	private RestauranteRepository restauranteRepository;

	@Autowired
	private CadastroRestauranteService cadastroRestauranteService;

	@GetMapping
	public List<Restaurante> listar() {
		return restauranteRepository.todos();
	}

	@GetMapping("/{restauranteId}")
	public ResponseEntity<Restaurante> buscar(@PathVariable Long restauranteId) {

		Restaurante restaurante = restauranteRepository.buscar(restauranteId);

		if (restaurante != null) {
			return ResponseEntity.ok(restaurante);
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> adicionar(@RequestBody Restaurante restaurante) {
		try {
			restaurante = cadastroRestauranteService.salvar(restaurante);

			return ResponseEntity.status(HttpStatus.CREATED).body(restaurante);
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("/{restauranteId}")
	public ResponseEntity<?> atualizar(@PathVariable Long restauranteId, @RequestBody Restaurante restaurante) {
		try {
			Restaurante restauranteAtual = restauranteRepository.buscar(restauranteId);

			if (restauranteAtual != null) {
				BeanUtils.copyProperties(restaurante, restauranteAtual, "id");

				restauranteAtual = cadastroRestauranteService.salvar(restauranteAtual);
				return ResponseEntity.ok(restauranteAtual);
			}

			return ResponseEntity.notFound().build();
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@DeleteMapping("/{restauranteId}")
	public ResponseEntity<Restaurante> remover(@PathVariable Long restauranteId) {
		try {

			cadastroRestauranteService.excluir(restauranteId);
			return ResponseEntity.noContent().build();

		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();

		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}

	@PatchMapping("/{restauranteId}")
	public ResponseEntity<?> atualizarParcial(@PathVariable Long restauranteId,
			@RequestBody Map<String, Object> campos) {

		Restaurante restauranteAtual = restauranteRepository.buscar(restauranteId);

		if (restauranteAtual == null) {
			return ResponseEntity.notFound().build();
		}
		// System.out.println(restaurante);
		merge(campos, restauranteAtual);

		return atualizar(restauranteId, restauranteAtual);
	}

	private void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino) {
		ObjectMapper objectMapper = new ObjectMapper();
		Restaurante restaraunteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);
		
		//System.out.println("restauranteOrigem " + restaraunteOrigem);
		dadosOrigem.forEach((nomePropridade, valorPropriedade) -> {
			Field field = ReflectionUtils.findField(Restaurante.class, nomePropridade);
			field.setAccessible(true);
			
			Object novoValor = ReflectionUtils.getField(field, restaraunteOrigem);
			//System.out.println(nomePropridade + " = " + valorPropriedade + " = " + novoValor);
			ReflectionUtils.setField(field, restauranteDestino, novoValor);

		});
	}
}












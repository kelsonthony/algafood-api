package com.kelsonthony.algafood.api.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kelsonthony.algafood.api.assembler.FotoProdutoModelAssembler;
import com.kelsonthony.algafood.api.model.FotoProdutoModel;
import com.kelsonthony.algafood.api.model.input.FotoProdutoInput;
import com.kelsonthony.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.kelsonthony.algafood.domain.model.FotoProduto;
import com.kelsonthony.algafood.domain.model.Produto;
import com.kelsonthony.algafood.domain.service.CadastroProdutoService;
import com.kelsonthony.algafood.domain.service.CatalogoFotoProdutoService;
import com.kelsonthony.algafood.domain.service.FotoStorageService;
import com.kelsonthony.algafood.domain.service.FotoStorageService.FotoRecuperada;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos/{produtoId}/foto")
public class RestauranteProdutoFotoController {
	
	@Autowired
	private CatalogoFotoProdutoService catalogoFotoProdutoService;
	
	@Autowired
	private CadastroProdutoService cadastroProdutoService;
	
	@Autowired
	private FotoProdutoModelAssembler fotoProdutoModelAssembler;
	
	@Autowired
	private FotoStorageService fotoStorageService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public FotoProdutoModel buscar(@PathVariable Long restauranteId, 
			@PathVariable Long produtoId) {
		
		FotoProduto fotoProduto = catalogoFotoProdutoService.buscarOuFalhar(restauranteId, produtoId);
		
		return fotoProdutoModelAssembler.toModel(fotoProduto);
	}
	
	@GetMapping
	public ResponseEntity<?> servirFoto(@PathVariable Long restauranteId, 
			@PathVariable Long produtoId, @RequestHeader(name = "accept") String acceptHeader) 
					throws HttpMediaTypeNotAcceptableException {
		try {
		FotoProduto fotoProduto = catalogoFotoProdutoService.buscarOuFalhar(restauranteId, produtoId);
		
		MediaType mediaTypeFoto = MediaType.parseMediaType(fotoProduto.getContentType());
		List<MediaType> mediaTypesAceitas = MediaType.parseMediaTypes(acceptHeader);
		
		verificarCompatibilidadeMediaType(mediaTypeFoto, mediaTypesAceitas);
		
		FotoRecuperada fotoRecuperada = fotoStorageService.recuperar(fotoProduto.getNomeArquivo());
		
		if(fotoRecuperada.temUrl()) {
			return ResponseEntity
					.status(HttpStatus.FOUND)
					.header(HttpHeaders.LOCATION, fotoRecuperada.getUrl())
					.build();
		} else {
			return ResponseEntity.ok()
					.contentType(mediaTypeFoto)
					.body(new InputStreamResource(fotoRecuperada.getInputStream()));
		   }
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		}
	}
	

	@PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public FotoProdutoModel atualizarFoto(@PathVariable Long restauranteId,
			@PathVariable Long produtoId, @RequestParam MultipartFile arquivo,
			@Valid FotoProdutoInput fotoProdutoInput) throws IOException {
		
		Produto produto = cadastroProdutoService.buscarOuFalhar(restauranteId, produtoId);
		
		MultipartFile arquivoSave = fotoProdutoInput.getArquivo();
		
		FotoProduto foto = new FotoProduto();
		foto.setProduto(produto);
		foto.setDescricao(fotoProdutoInput.getDescricao());
		foto.setContentType(arquivoSave.getContentType());
		foto.setTamanho(arquivo.getSize());
		foto.setNomeArquivo(arquivoSave.getOriginalFilename());
		
		
		FotoProduto fotoSalva = catalogoFotoProdutoService.salvar(foto, arquivoSave.getInputStream());
		
		return fotoProdutoModelAssembler.toModel(fotoSalva);
		
	}
	
	private void verificarCompatibilidadeMediaType(MediaType mediaTypeFoto,
			List<MediaType> mediaTypesAceitas) throws HttpMediaTypeNotAcceptableException {
		boolean compattivel = mediaTypesAceitas.stream()
				.anyMatch(mediaTypeAceita -> mediaTypeAceita.isCompatibleWith(mediaTypeFoto));
		
		if(!compattivel) {
			throw new HttpMediaTypeNotAcceptableException(mediaTypesAceitas);
		}
	}
	
	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long restauranteId, 
			@PathVariable Long produtoId) {
		catalogoFotoProdutoService.excluir(restauranteId, produtoId);
	}
}

package com.kelsonthony.algafood.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kelsonthony.algafood.api.assembler.FotoProdutoModelAssembler;
import com.kelsonthony.algafood.api.model.FotoProdutoModel;
import com.kelsonthony.algafood.api.model.input.FotoProdutoInput;
import com.kelsonthony.algafood.domain.model.FotoProduto;
import com.kelsonthony.algafood.domain.model.Produto;
import com.kelsonthony.algafood.domain.service.CadastroProdutoService;
import com.kelsonthony.algafood.domain.service.CatalogoFotoProdutoService;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos/{produtoId}/foto")
public class RestauranteProdutoFotoController {
	
	@Autowired
	private CatalogoFotoProdutoService catalogoFotoProdutoService;
	
	@Autowired
	private CadastroProdutoService cadastroProdutoService;
	
	@Autowired
	private FotoProdutoModelAssembler fotoProdutoModelAssembler;

	/*
	 * @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE) public void
	 * atualizarFoto(@PathVariable Long restauranteId,
	 * 
	 * @PathVariable Long produtoId, @RequestParam MultipartFile arquivo,
	 * 
	 * @Valid FotoProdutoInput fotoProdutoInput) {
	 * 
	 * 
	 * var nomeArquivo = UUID.randomUUID().toString() + "_" +
	 * arquivo.getOriginalFilename();
	 * 
	 * var nomeArquivo = UUID.randomUUID().toString() + "_" +
	 * fotoProdutoInput.getArquivo().getOriginalFilename();
	 * 
	 * var arquivoFoto = Path.of("C:/Users/kanthony.CORP/catalogo", nomeArquivo);
	 * 
	 * System.out.println(nomeArquivo);
	 * System.out.println(fotoProdutoInput.getDescricao());
	 * System.out.println(arquivo.getContentType());
	 * 
	 * 
	 * try { fotoProdutoInput.getArquivo().transferTo(arquivoFoto); } catch
	 * (Exception e) { // TODO Auto-generated catch block throw new
	 * RuntimeException(e); } }
	 */
	@PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public FotoProdutoModel atualizarFoto(@PathVariable Long restauranteId,
			@PathVariable Long produtoId, @RequestParam MultipartFile arquivo,
			@Valid FotoProdutoInput fotoProdutoInput) {
		
		Produto produto = cadastroProdutoService.buscarOuFalhar(restauranteId, produtoId);
		
		MultipartFile arquivo1 = fotoProdutoInput.getArquivo();
		
		FotoProduto foto = new FotoProduto();
		foto.setProduto(produto);
		foto.setDescricao(fotoProdutoInput.getDescricao());
		foto.setContentType(arquivo1.getContentType());
		foto.setTamanho(arquivo.getSize());
		foto.setNomeArquivo(arquivo1.getOriginalFilename());
		
		
		FotoProduto fotoSalva = catalogoFotoProdutoService.salvar(foto);
		
		return fotoProdutoModelAssembler.toModel(fotoSalva);
		
	}
}

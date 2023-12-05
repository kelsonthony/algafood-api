package com.kelsonthony.algafood.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.List;

//@ApiModel("Problema")
@JsonInclude(Include.NON_NULL)
@Getter
@Builder
public class Problem {
	
	//@ApiModelProperty(example = "400", position = 1)
	private Integer status;
	
	//@ApiModelProperty(example = "2022-06-06T14:27:04.7958653Z", position = 5)
	private OffsetDateTime timestamp;
	
	//@ApiModelProperty(example = "https://kelsonthony.com.br/mensagem-incompreensivel", position = 10)
	private String type;
	
	//@ApiModelProperty(example = "mensagem incompreensível", position = 15)
	private String title;
	
	//@ApiModelProperty(example = "O corpo da requisição eśta inválido. Verifique o erro de sintaxe", position = 20)
	private String detail;
	
	//@ApiModelProperty(example = "O corpo da requisição eśta inválido. Verifique o erro de sintaxe", position = 25)
	private String userMessage;
	
	//@ApiModelProperty(value = "Lista de objetos ou campos que geraram o erro (opcional)", position = 30)
	private List<Object> objects;
	
	//@ApiModel("ObjetoProblema")
	@Getter
	@Builder
	public static class Object {
		
		//@ApiModelProperty(value = "preço")
		private String name;
		
		//@ApiModelProperty(value = "O preço é obrigatório")
		private String userMessage;
		
	}
	

	/*
	 * private LocalDateTime dataHora; private String mensagem;
	 */
}

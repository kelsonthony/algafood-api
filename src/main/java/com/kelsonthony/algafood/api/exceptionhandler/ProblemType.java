package com.kelsonthony.algafood.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

	MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensivel", "mensagem incompreensível"),
	RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado", "Recurso não encontrado"),
	ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
	ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio"),
	PARAMETRO_INVALIDO("/parametro-invalido", "Parâmetro Inválido"),
	ERRO_DE_SISTEMA("/erro-de-sistema", "Erro de Sistema"),
	DADOS_INVALIDOS("/dados-invalidos", "Dados inválidos"),
	ACESSO_NEGADO("/acesso-negado", "Acesso Negado");
	
	
	private String title;
	private String uri;
	
	ProblemType(String path, String title){
		this.uri = "https://kelsonthony.com.br" + path;
		this.title = title;
	}
}

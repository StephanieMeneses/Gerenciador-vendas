package com.gerenciador.api.services.exceptions;

public class ViolacaoRegraDeNegocioException extends RuntimeException {
	
	private static final long serialVersionUID= 1L;
	
	public ViolacaoRegraDeNegocioException(String mensagem) {
		super(mensagem);
	}
	
	
}

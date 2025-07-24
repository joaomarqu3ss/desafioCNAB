package br.com.cotiinformatica.dtos;

import java.util.UUID;

public record CnabResponse(
		UUID id, 
		String tipo, 
		String fluxo, 
		String data, 
		String hora, 
		Double valor, 
		String cartao, 
		String cpf, 
		String proprietario, 
		String propriedade, 
		Double saldo
		) {

}

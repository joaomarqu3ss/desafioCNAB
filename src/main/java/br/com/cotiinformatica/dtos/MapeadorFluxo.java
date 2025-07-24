package br.com.cotiinformatica.dtos;

import java.util.HashMap;
import java.util.Map;

import br.com.cotiinformatica.enums.Fluxo;
import br.com.cotiinformatica.enums.Tipo;

public class MapeadorFluxo {

	// map determinando os fluxos de entrada e saída conforme os tipos de movimentacao
	private static final Map<Tipo, Fluxo> map = new HashMap<>(); 
	
	static {
	map.put(Tipo.DEBITO, Fluxo.ENTRADA);
	map.put(Tipo.BOLETO, Fluxo.SAIDA);
	map.put(Tipo.FINANCIAMENTO, Fluxo.SAIDA);
	map.put(Tipo.CREDITO, Fluxo.ENTRADA);
	map.put(Tipo.RECEBIMENTOEMPRESTIMO, Fluxo.ENTRADA);
	map.put(Tipo.VENDAS, Fluxo.ENTRADA);
	map.put(Tipo.RECEBIMENTOTED, Fluxo.ENTRADA);
	map.put(Tipo.RECEBIMENTODOC, Fluxo.ENTRADA);
	map.put(Tipo.ALUGUEL, Fluxo.SAIDA);
	}
	public static Fluxo getFluxo(Tipo tipo) {
		return map.get(tipo);
	}
}

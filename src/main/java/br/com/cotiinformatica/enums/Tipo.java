package br.com.cotiinformatica.enums;

public enum Tipo {
	
	DEBITO(1),
	BOLETO(2),
	FINANCIAMENTO(3),
	CREDITO(4),
	RECEBIMENTOEMPRESTIMO(5),
	VENDAS(6),
	RECEBIMENTOTED(7),
	RECEBIMENTODOC(8),
	ALUGUEL(9);
	
	private int valor;
	private Tipo(int valor) {
		this.valor = valor;
	}
	
	public int getValor() {
		return valor;
	}
	 public static Tipo valueOf(int valor) {
	        for (Tipo tipo : Tipo.values()) {
	            if (tipo.getValor() == valor) {
	                return tipo;
	            }
	        }
	        throw new IllegalArgumentException("Tipo inválido: " + valor);
	 }
}

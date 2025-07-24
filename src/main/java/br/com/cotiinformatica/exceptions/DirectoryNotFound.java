package br.com.cotiinformatica.exceptions;

@SuppressWarnings("serial")
public class DirectoryNotFound extends RuntimeException {

	@Override
	public String getMessage() {

		return "Não foi possível criar o diretório de upload.";
	}
}

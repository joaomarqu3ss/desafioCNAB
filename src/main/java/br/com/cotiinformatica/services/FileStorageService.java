package br.com.cotiinformatica.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import br.com.cotiinformatica.configurations.FileStorageConfig;
import br.com.cotiinformatica.dtos.CnabRequest;
import br.com.cotiinformatica.dtos.CnabResponse;
import br.com.cotiinformatica.entities.Cnab;
import br.com.cotiinformatica.enums.Fluxo;
import br.com.cotiinformatica.enums.Tipo;
import br.com.cotiinformatica.exceptions.DirectoryNotFound;
import br.com.cotiinformatica.repositories.CnabRepository;

@Service
public class FileStorageService {
	
	private final CnabRepository repository;
	private final Path fileStorageLocation;

	public FileStorageService(FileStorageConfig fileStorageConfig, CnabRepository repository) {
		
		this.fileStorageLocation = Paths.get(fileStorageConfig.getUploadDir())
				.toAbsolutePath().normalize();
		this.repository = repository;
		
		try {
			Files.createDirectories(this.fileStorageLocation);
		} catch (IOException ex) {
			throw new DirectoryNotFound();
		}
	}

	public String uploadFile(MultipartFile file) {
		
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		
		try {
			Path targetLocation = fileStorageLocation.resolve(fileName);
			file.transferTo(targetLocation);
			parse(); // pegando o arquivo -> parseando -> salvando no banco.
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return "OK";

	}
	
	public List<CnabResponse> list(CnabRequest request) { // listar por nome das lojas
			List<Cnab> moves = repository.findByPropriedadeContainingIgnoreCase(request.propriedade());
			return moves.stream()
					.map(this::copyToResponse)
					.toList();
	}

	public void parse() {

		
		
		// Metodo para parsear o arquivo que foi enviado na requisicao post.
		
		try {
		String nomedoArquivo = "CNAB.txt";
		
		Path filePath = this.fileStorageLocation.resolve(nomedoArquivo).normalize();
		List<String> movements = Files.readAllLines(filePath);
		for(var movement : movements) {
			var cnab = new Cnab();
			int tipo =  Integer.parseInt(movement.substring(0, 1));
			Double valor = Double.parseDouble(movement.substring(9, 19)) / 100;
			cnab.setTipo(Tipo.valueOf(tipo));
			cnab.setData(movement.substring(1, 9));
			cnab.setValor(valor);
			cnab.setCpf(movement.substring(19, 30));
			cnab.setCartao(movement.substring(30, 42));
			cnab.setHora(movement.substring(42, 48));
			cnab.setProprietario(movement.substring(48, 62).trim()); // trim para tirar os espacos
			cnab.setPropriedade(movement.substring(62, 80).trim());
			
			// SALDO HIPOTETICO PARA NAO RETORNAR NULL.
			if(Fluxo.SAIDA != null) {
				cnab.setSaldo(1000.00 - cnab.getValor());
			} 
			if(Fluxo.ENTRADA != null) {
				cnab.setSaldo(Double.sum(1000.00, cnab.getValor()));
			}			
		
			repository.save(cnab);	
		}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	private CnabResponse copyToResponse(Cnab cnab) {
		return new CnabResponse(
				cnab.getId(), 
				cnab.getTipo().toString(), 
				cnab.getFluxo().toString(), 
				cnab.getData(),
				cnab.getHora(), 
				cnab.getValor(), 
				cnab.getCartao(), 
				cnab.getCpf(), 
				cnab.getProprietario(),
				cnab.getPropriedade(), 
				cnab.getSaldo());
	}
}

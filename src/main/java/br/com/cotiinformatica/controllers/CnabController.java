package br.com.cotiinformatica.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.cotiinformatica.dtos.CnabRequest;
import br.com.cotiinformatica.dtos.CnabResponse;
import br.com.cotiinformatica.services.FileStorageService;

@RestController
@RequestMapping("/api/v1/cnab")
public class CnabController {

	private final FileStorageService fileService;

	public CnabController(FileStorageService fileService) {
		this.fileService = fileService;
	}

	@PostMapping("upload")
	public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) {	
			
		return ResponseEntity.ok(fileService.uploadFile(file));
		
	}
	
	@GetMapping("listar")
	public ResponseEntity<List<CnabResponse>> listar(CnabRequest request) {
		var response = fileService.list(request);
		return ResponseEntity.ok(response);
	}
	
	
}

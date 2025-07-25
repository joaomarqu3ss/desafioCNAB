package br.com.cotiinformatica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class DesafioCnabApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioCnabApplication.class, args);
	}

}

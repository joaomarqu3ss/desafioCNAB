package br.com.cotiinformatica.entities;

import java.util.UUID;

import br.com.cotiinformatica.dtos.MapeadorFluxo;
import br.com.cotiinformatica.enums.Fluxo;
import br.com.cotiinformatica.enums.Tipo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "CNAB")
@Data
public class Cnab {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "id")
	private UUID id;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo", nullable = false)
	private Tipo tipo;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "fluxo", nullable = false)
	private Fluxo fluxo;
	
	@Column(name = "data", nullable = false)
	private String data;
	
	@Column(name = "hora", nullable = false)
	private String hora;
	
	@Column(name = "valor", nullable = false)
	private Double valor;
	
	@Column(name = "cartao", nullable = false)
	private String cartao;
	
	@Column(name = "cpf", nullable = false)
	private String cpf;
	
	@Column(name = "proprietario", nullable = false)
	private String proprietario;
	
	@Column(name = "propriedade", nullable = false)
	private String propriedade;
	
	@Column(name = "saldo")
	private Double saldo;
	
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
		this.fluxo = MapeadorFluxo.getFluxo(tipo);
	}
}

package br.com.cotiinformatica.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.cotiinformatica.entities.Cnab;

@Repository
public interface CnabRepository extends JpaRepository<Cnab, UUID> {
	
	@Query("""
			SELECT c FROM Cnab c 
			WHERE c.propriedade = :propriedade
			""")
	List<Cnab> findByPropriedade(@Param("propriedade") String propriedade);
}

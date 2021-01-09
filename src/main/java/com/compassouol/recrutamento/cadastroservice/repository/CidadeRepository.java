package com.compassouol.recrutamento.cadastroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.compassouol.recrutamento.cadastroservice.model.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {

	@Query(value = "SELECT COUNT (c.*) c FROM Cidade c WHERE UPPER(c.nome) = UPPER(?1) and UPPER(c.estado) = UPPER(?2)", nativeQuery = true)
	public Long countCidadesByNomeAndEstado(String nome, String estado);

}

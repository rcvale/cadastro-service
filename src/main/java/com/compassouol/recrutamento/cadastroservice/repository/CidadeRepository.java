package com.compassouol.recrutamento.cadastroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.compassouol.recrutamento.cadastroservice.model.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long>, JpaSpecificationExecutor<Cidade>  {	

	
	
}

package com.compassouol.recrutamento.cadastroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.compassouol.recrutamento.cadastroservice.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>, JpaSpecificationExecutor<Cliente>  {	
	
	
	
}

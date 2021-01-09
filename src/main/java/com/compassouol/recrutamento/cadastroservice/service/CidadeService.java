package com.compassouol.recrutamento.cadastroservice.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compassouol.recrutamento.cadastroservice.exception.BadRequestException;
import com.compassouol.recrutamento.cadastroservice.exception.InternalServerErrorException;
import com.compassouol.recrutamento.cadastroservice.model.Cidade;
import com.compassouol.recrutamento.cadastroservice.repository.CidadeRepository;


@Service
public class CidadeService {
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	public Cidade incluir(Cidade cidade) {
		if(cidade==null)
			throw new BadRequestException("Objeto cidade não pode ser nulo");
		if(StringUtils.isBlank(cidade.getNome()))
			throw new BadRequestException("O nome da cidade não pode ser nulo ou vazio");
		if(cidade.getNome().length() > 100)
			throw new BadRequestException("O nome da cidade não pode ter mais que 100 caracteres");
		if(cidade.getEstado()==null)		
			throw new BadRequestException("O estado da cidade não pode ser nulo");
		
		long qtdCidades = cidadeRepository.countCidadesByNomeAndEstado(cidade.getNome(), cidade.getEstado().toString());
		if(qtdCidades > 0)
			throw new InternalServerErrorException("Já existe uma cidade cadastrada com o nome informado para o estado");
		
		cidade = cidadeRepository.save(cidade);		
		return cidade;
	}
	
	

}

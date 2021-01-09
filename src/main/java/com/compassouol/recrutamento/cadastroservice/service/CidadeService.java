package com.compassouol.recrutamento.cadastroservice.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.compassouol.recrutamento.cadastroservice.exception.BadRequestException;
import com.compassouol.recrutamento.cadastroservice.exception.InternalServerErrorException;
import com.compassouol.recrutamento.cadastroservice.model.Cidade;
import com.compassouol.recrutamento.cadastroservice.repository.CidadeRepository;


@Service
public class CidadeService {
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	public Cidade insert(Cidade cidade) {
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
		
		cidade.setNome(cidade.getNome().toUpperCase());
		cidade = cidadeRepository.save(cidade);		
		return cidade;
	}
	
	public List<Cidade> find(Cidade search) {		
		List<Cidade> list = null;
		
		if(search==null)
			throw new BadRequestException("Objeto de busca não pode ser nulo");
		
		if(StringUtils.isNotBlank(search.getNome()) && search.getEstado()!=null)
			list = cidadeRepository.findByNomeContainingIgnoreCaseAndEstado(search.getNome(), search.getEstado(), getOrderBy());
		else if(StringUtils.isNotBlank(search.getNome()))
			list = cidadeRepository.findByNomeContainingIgnoreCase(search.getNome(), getOrderBy());
		else if (search.getEstado()!=null)
			list = cidadeRepository.findByEstado(search.getEstado(), getOrderBy());
		else 
			list = cidadeRepository.findAll(getOrderBy());
		
		return list;
	}	
	
	private Sort getOrderBy() {
		Sort sort = Sort.by(Sort.Order.asc("nome"), Sort.Order.asc("estado"));
		return sort;
	}

}

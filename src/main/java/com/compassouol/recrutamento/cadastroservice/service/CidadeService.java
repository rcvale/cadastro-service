package com.compassouol.recrutamento.cadastroservice.service;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
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
			throw new BadRequestException("O nome da cidade não pode ter mais de 100 caracteres");
		if(cidade.getEstado()==null)		
			throw new BadRequestException("O estado da cidade não pode ser nulo");
		
		Cidade search = new Cidade();
		search.setNome(cidade.getNome());
		search.setEstado(cidade.getEstado());
		
		long qtdCidades = count(search);
		if(qtdCidades > 0)
			throw new InternalServerErrorException("Já existe uma cidade cadastrada com esse nome para o estado informado");
		
		cidade.setNome(cidade.getNome().toUpperCase());
		cidade = cidadeRepository.save(cidade);		
		
		return cidade;
	}
	
	public List<Cidade> find(Cidade search) {		
		List<Cidade> list = cidadeRepository.findAll(getSpecification(search), getOrderBy());
		return list;
	}
	
	
	private long count(Cidade search) {		
		return cidadeRepository.count(getSpecification(search));
	}

	
	private Specification<Cidade> getSpecification(Cidade search) {
		return new Specification<Cidade>() {			
			
			private static final long serialVersionUID = -8865848533459557168L;

			@Override
			public Predicate toPredicate(Root<Cidade> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				Predicate p=null;
				
				if(search!=null) {
					if(search.getId()!=null) {
						Predicate p1 =cb.equal(root.get("id"), search.getId());
						p = p == null ? p1 : cb.and(p, p1);
					}
					else {
						if(StringUtils.isNotBlank(search.getNome())){
							Predicate p1 = cb.like(cb.upper(root.get("nome")), "%"+search.getNome().toUpperCase()+"%");
							p = p == null ? p1 : cb.and(p, p1);
						}
						if(search.getEstado()!=null) {
							Predicate p1 =cb.equal(root.get("estado"), search.getEstado());
							p = p == null ? p1 : cb.and(p, p1);
						}
					}	
				}
				
				return p;
			}
		};
	}	
	
	private Sort getOrderBy() {
		Sort sort = Sort.by(Sort.Order.asc("nome"), Sort.Order.asc("estado"));
		return sort;
	}
	
}

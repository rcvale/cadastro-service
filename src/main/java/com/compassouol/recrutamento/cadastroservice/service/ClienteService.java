package com.compassouol.recrutamento.cadastroservice.service;

import java.util.List;
import java.util.Optional;

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
import com.compassouol.recrutamento.cadastroservice.model.Cidade;
import com.compassouol.recrutamento.cadastroservice.model.Cliente;
import com.compassouol.recrutamento.cadastroservice.repository.CidadeRepository;
import com.compassouol.recrutamento.cadastroservice.repository.ClienteRepository;


@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	
	
	public Cliente insert(Cliente cliente) {
		return gravar(cliente);
	}
	
	public Cliente update(Cliente cliente) {
		if(cliente!=null && cliente.getId()==null)
			throw new BadRequestException("Id do cliente não pode ser nulo");
		
		Optional<Cliente> optCliente = clienteRepository.findById(cliente.getId());
		if(!optCliente.isPresent())
			throw new BadRequestException("Não foi encontrado um cliente com o id informado");
		
		return gravar(cliente);
	}
	
	private Cliente gravar(Cliente cliente) {		
		if(cliente==null)
			throw new BadRequestException("Objeto cliente não pode ser nulo");
		if(StringUtils.isBlank(cliente.getNome()))
			throw new BadRequestException("O nome do cliente não pode ser nulo ou vazio");
		if(cliente.getNome().length() > 100)
			throw new BadRequestException("O nome do cliente não pode ter mais de 100 caracteres");
				
		if(cliente.getCidade()!=null && cliente.getCidade().getId()!=null) {
			Optional<Cidade> optCidade = cidadeRepository.findById(cliente.getCidade().getId());
			if(!optCidade.isPresent())
				throw new BadRequestException("Não existe cidade com o id informado");
			cliente.setCidade(optCidade.get());
		}else {
			cliente.setCidade(null);
		}
		
		cliente.setNome(cliente.getNome().toUpperCase());
		cliente = clienteRepository.save(cliente);
		return cliente;
	}
	
	public List<Cliente> find(Cliente search) {		
		List<Cliente> list = clienteRepository.findAll(getSpecification(search), getOrderBy());
		return list;
	}
	
	public String delete(Long idCliente) {
		Optional<Cliente> optCliente = clienteRepository.findById(idCliente);
		if(!optCliente.isPresent())
			throw new BadRequestException("Não foi encontrado um cliente com o id informado");
		
		clienteRepository.deleteById(idCliente);		
		return "Cliente excluído com sucesso";
	}
	
	
	private Specification<Cliente> getSpecification(Cliente search) {
		return new Specification<Cliente>() {			

			private static final long serialVersionUID = 8207384065339613983L;

			@Override
			public Predicate toPredicate(Root<Cliente> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				
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
					}	
				}
				
				return p;
			}
		};
	}	
	
	private Sort getOrderBy() {
		Sort sort = Sort.by(Sort.Order.asc("nome"));
		return sort;
	}

}

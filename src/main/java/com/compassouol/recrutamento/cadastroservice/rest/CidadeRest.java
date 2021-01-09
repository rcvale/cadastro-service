package com.compassouol.recrutamento.cadastroservice.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.compassouol.recrutamento.cadastroservice.model.Cidade;
import com.compassouol.recrutamento.cadastroservice.model.Estado;
import com.compassouol.recrutamento.cadastroservice.rest.model.Cidade_R;
import com.compassouol.recrutamento.cadastroservice.rest.model.PesquisaRetorno_R;
import com.compassouol.recrutamento.cadastroservice.service.CidadeService;
import com.compassouol.recrutamento.cadastroservice.util.ConversorCidadeRest;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/rest/cidade")
public class CidadeRest {

	@Autowired
	private CidadeService cidadeService;
	
	@Autowired
	private ConversorCidadeRest conversor;
	
	@PostMapping()
	public Cidade_R insertCidade(@RequestBody Cidade_R cidade_R) {		
		Cidade cidade = conversor.toEntity(cidade_R);		
		cidade = cidadeService.insert(cidade);
		return conversor.toRest(cidade);
	}
	
	@GetMapping()
	public PesquisaRetorno_R<Cidade_R> pesquisarCidades(@RequestParam(name = "nome", required = false) String nome, @RequestParam(name = "estado", required = false) Estado estado) {
		Cidade search = conversor.toEntity(new Cidade_R().nome(nome).estado(estado));
		List<Cidade> list = cidadeService.find(search);	
		return conversor.toListRest(list);
	}	
	
}

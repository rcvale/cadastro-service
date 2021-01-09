package com.compassouol.recrutamento.cadastroservice.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.compassouol.recrutamento.cadastroservice.model.Cidade;
import com.compassouol.recrutamento.cadastroservice.rest.model.Cidade_R;
import com.compassouol.recrutamento.cadastroservice.service.CidadeService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/rest/cidade")
public class CidadeRest {

	@Autowired
	private CidadeService cidadeService;
	
	@PostMapping()
	public Cidade_R insertCidade(@RequestBody Cidade_R cidade_R) {

		Cidade cidade = new Cidade();
		cidade.setNome(cidade_R.getNome());
		cidade.setEstado(cidade_R.getEstado());
		
		Cidade cidadeGravada = cidadeService.incluir(cidade);
		
		cidade_R.setId(cidadeGravada.getId());
		
		return cidade_R;
	}
	
	@GetMapping()
	public Cidade_R pesquisarCidades() {

//		Cidade cidade = new Cidade();
//		cidade.setNome(cidade_R.getNome());
//		cidade.setEstado(cidade_R.getEstado());
//		
//		Cidade cidadeGravada = cidadeService.gravar(cidade);
//		
//		cidade_R.setId(cidadeGravada.getId());
		
		return null;
	}
	
	
	
//	Consultar cidade pelo nome
//	Consultar cidade pelo estado

}

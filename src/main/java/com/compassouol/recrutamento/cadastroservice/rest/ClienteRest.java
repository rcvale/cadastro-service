package com.compassouol.recrutamento.cadastroservice.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.compassouol.recrutamento.cadastroservice.model.Cliente;
import com.compassouol.recrutamento.cadastroservice.rest.model.Cliente_R;
import com.compassouol.recrutamento.cadastroservice.rest.model.PesquisaRetorno_R;
import com.compassouol.recrutamento.cadastroservice.rest.model.Sucesso_R;
import com.compassouol.recrutamento.cadastroservice.service.ClienteService;
import com.compassouol.recrutamento.cadastroservice.util.ConversorClienteRest;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/rest/cliente")
public class ClienteRest {

	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ConversorClienteRest conversor;
	
	@PostMapping()
	public Cliente_R insertCliente(@RequestBody Cliente_R Cliente_R) {		
		Cliente Cliente = conversor.toEntity(Cliente_R);	
		Cliente.setId(null);
		Cliente = clienteService.insert(Cliente);
		return conversor.toRest(Cliente);
	}
	
	@GetMapping()
	public PesquisaRetorno_R<Cliente_R> findClientes(@RequestParam(name = "id", required = false) Long id, @RequestParam(name = "nome", required = false) String nome) {
		Cliente search = conversor.toEntity(new Cliente_R().id(id).nome(nome));
		List<Cliente> list = clienteService.find(search);	
		return conversor.toListRest(list);
	}	

	@PutMapping(path = "/{idCliente}")
	public Cliente_R updateCliente(@PathVariable(name = "idCliente") Long idCliente, @RequestBody Cliente_R Cliente_R) {		
		Cliente Cliente = conversor.toEntity(Cliente_R);
		Cliente.setId(idCliente);
		Cliente = clienteService.update(Cliente);
		return conversor.toRest(Cliente);
	}	

	@DeleteMapping(path = "/{idCliente}")
	public Sucesso_R updateCliente(@PathVariable(name = "idCliente") Long idCliente) {		
		String msg = clienteService.delete(idCliente);
		return new Sucesso_R(msg);
	}
	
}

package com.compassouol.recrutamento.cadastroservice.rest.model;

import java.io.Serializable;

import com.compassouol.recrutamento.cadastroservice.model.Estado;

public class Cidade_R implements Serializable{
	
	private Long id;
	private String nome;
	private Estado estado;
	
	public Cidade_R id(Long id) {
		setId(id);
		return this;
	}
	
	public Cidade_R nome(String nome) {
		setNome(nome);
		return this;
	}
	
	public Cidade_R estado(Estado estado) {
		setEstado(estado);
		return this;
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}

}

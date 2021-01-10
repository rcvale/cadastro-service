package com.compassouol.recrutamento.cadastroservice.rest.model;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.compassouol.recrutamento.cadastroservice.model.Sexo;
import com.fasterxml.jackson.annotation.JsonFormat;

public class Cliente_R {

	private Long id;
	private String nome;
	private Sexo sexo;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "pt-BR", timezone = "Brazil/East")
	private Date dataNascimento;
	private Integer idade;
	private Cidade_R cidade;
	
	public Cliente_R id(Long id) {
		setId(id);
		return this;
	}
	
	public Cliente_R nome(String nome) {
		setNome(nome);
		return this;
	}
	
	public Cliente_R sexo(Sexo sexo) {
		setSexo(sexo);
		return this;
	}
	
	public Cliente_R dataNascimento(Date dataNascimento) {
		setDataNascimento(dataNascimento);
		return this;
	}
	
	public Cliente_R idade(Integer idade) {
		setIdade(idade);
		return this;
	}
	
	public Cliente_R cidade(Cidade_R cidade) {
		setCidade(cidade);
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
	public Sexo getSexo() {
		return sexo;
	}
	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public Integer getIdade() {
		return idade;
	}
	public void setIdade(Integer idade) {
		this.idade = idade;
	}
	public Cidade_R getCidade() {
		return cidade;
	}
	public void setCidade(Cidade_R cidade) {
		this.cidade = cidade;
	}
	
}

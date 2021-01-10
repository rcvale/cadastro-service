package com.compassouol.recrutamento.cadastroservice.rest.model;

import java.io.Serializable;

public class Sucesso_R implements Serializable{
	
	private String mensagem;
	
	public Sucesso_R(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}

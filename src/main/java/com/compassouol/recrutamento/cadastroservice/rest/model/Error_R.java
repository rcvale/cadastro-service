package com.compassouol.recrutamento.cadastroservice.rest.model;

import java.io.Serializable;

public class Error_R implements Serializable{
	
	private int codErro;
	private String messagem;
	
	public Error_R(int codErro, String messagem) {
		this.codErro = codErro;
		this.messagem = messagem;
	}

	public int getCodErro() {
		return codErro;
	}

	public void setCodErro(int codErro) {
		this.codErro = codErro;
	}

	public String getMessagem() {
		return messagem;
	}

	public void setMessagem(String messagem) {
		this.messagem = messagem;
	}

}

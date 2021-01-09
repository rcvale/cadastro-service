package com.compassouol.recrutamento.cadastroservice.rest.model;

import java.io.Serializable;

public class Error_R implements Serializable{
	
	private int codErro;
	private String message;
	
	public Error_R(int codErro, String message) {
		this.codErro = codErro;
		this.message = message;
	}

	public int getCodErro() {
		return codErro;
	}

	public void setCodErro(int codErro) {
		this.codErro = codErro;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}

package com.compassouol.recrutamento.cadastroservice.rest.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PesquisaRetorno_R<T> implements Serializable{
	
	private List<T> dados = new ArrayList<T>();

	public List<T> getDados() {
		return dados;
	}

	public void setDados(List<T> dados) {
		this.dados = dados;
	}

}

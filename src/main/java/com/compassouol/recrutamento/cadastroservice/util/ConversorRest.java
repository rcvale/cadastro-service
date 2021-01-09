package com.compassouol.recrutamento.cadastroservice.util;

import java.util.List;

import com.compassouol.recrutamento.cadastroservice.rest.model.PesquisaRetorno_R;

public abstract class ConversorRest<T, E> {
	
	public abstract T toRest(E e);
	
	public abstract E toEntity(T t);
	
	public PesquisaRetorno_R<T> toListRest(List<E> list){
		PesquisaRetorno_R<T> pesquisaRetorno = new PesquisaRetorno_R<T>();		
		if(!list.isEmpty()) {
			for (E e : list) {
				pesquisaRetorno.getDados().add(toRest(e));
			}
		}		
		return pesquisaRetorno;
	}

}

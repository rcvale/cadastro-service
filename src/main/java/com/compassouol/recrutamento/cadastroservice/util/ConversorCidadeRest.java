package com.compassouol.recrutamento.cadastroservice.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.compassouol.recrutamento.cadastroservice.model.Cidade;
import com.compassouol.recrutamento.cadastroservice.rest.model.Cidade_R;

@Component
public class ConversorCidadeRest extends ConversorRest<Cidade_R, Cidade>{

	@Override
	public Cidade_R toRest(Cidade entity) {
		if(entity!=null) {
			Cidade_R cidade_R = new Cidade_R()
					.id(entity.getId())
					.nome(entity.getNome())
					.estado(entity.getEstado());
			
//			if(entity.getId()!=null)
//				cidade_R.setId(entity.getId());
			return cidade_R;
		}
		return null;
	}

	@Override
	public Cidade toEntity(Cidade_R rest) {
		Cidade cidade = new Cidade();
		if(rest!=null) {
			if(rest.getId()!=null)
				cidade.setId(rest.getId());
			else if(StringUtils.isNotBlank(rest.getNome()))
				cidade.setNome(rest.getNome());
			if(rest.getEstado()!=null)
				cidade.setEstado(rest.getEstado());
		}
		return cidade;
	}

	

}

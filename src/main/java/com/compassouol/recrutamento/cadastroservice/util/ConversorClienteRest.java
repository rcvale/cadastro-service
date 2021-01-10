package com.compassouol.recrutamento.cadastroservice.util;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.compassouol.recrutamento.cadastroservice.model.Cliente;
import com.compassouol.recrutamento.cadastroservice.rest.model.Cidade_R;
import com.compassouol.recrutamento.cadastroservice.rest.model.Cliente_R;

@Component
public class ConversorClienteRest extends ConversorRest<Cliente_R, Cliente>{

	@Autowired
	private ConversorCidadeRest conversorCidade;
	
	@Override
	public Cliente_R toRest(Cliente entity) {
		if(entity!=null) {
			Cliente_R Cliente_R = new Cliente_R()
					.id(entity.getId())
					.nome(entity.getNome())
					.sexo(entity.getSexo())
					.dataNascimento(entity.getDataNascimento())
					.idade(entity.getIdade())
					.cidade(conversorCidade.toRest(entity.getCidade()));
			return Cliente_R;
		}
		return null;
	}

	@Override
	public Cliente toEntity(Cliente_R rest) {
		Cliente Cliente = new Cliente();		
		if(rest!=null) {
			if(rest.getId()!=null)
				Cliente.setId(rest.getId());
			else if(StringUtils.isNotBlank(rest.getNome()))
				Cliente.setNome(rest.getNome());
			if(rest.getSexo()!=null)
				Cliente.setSexo(rest.getSexo());		
			if(rest.getDataNascimento()!=null)
				Cliente.setDataNascimento(rest.getDataNascimento());
			if(rest.getIdade()!=null)
				Cliente.setIdade(rest.getIdade());
			if(rest.getCidade()!=null)
				Cliente.setCidade(conversorCidade.toEntity(rest.getCidade()));
		}
		return Cliente;
	}

	

}

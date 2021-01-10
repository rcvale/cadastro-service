package com.compassouol.recrutamento.cadastroservice;

import org.apache.commons.lang3.RandomStringUtils;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.util.ReflectionTestUtils;

import com.compassouol.recrutamento.cadastroservice.exception.BadRequestException;
import com.compassouol.recrutamento.cadastroservice.model.Cidade;
import com.compassouol.recrutamento.cadastroservice.repository.CidadeRepository;
import com.compassouol.recrutamento.cadastroservice.service.CidadeService;

@SpringBootTest(classes = CidadeTest.class)
//@SpringBootTest
//@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class CidadeTest {

	@Spy
	private CidadeService cidadeService;
	
	@MockBean
	private CidadeRepository cidadeRepository;
		
	@BeforeEach 
    public void init() {
		cidadeRepository = Mockito.mock(CidadeRepository.class);
		ReflectionTestUtils.setField(cidadeService, "cidadeRepository", cidadeRepository);
		//cidadeRepository.deleteAll();
    }

	@Test
	public void incluir_cidade_null() {
		Throwable exceptionThatWasThrown = Assertions.assertThrows(BadRequestException.class, () -> {
			Cidade cidade = null;
			cidadeService.insert(cidade);
		});		
		MatcherAssert.assertThat(exceptionThatWasThrown.getMessage(), IsEqual.equalTo("Objeto cidade não pode ser nulo"));
	}
	
	@Test
	public void incluir_cidade_nome_null() {
		Throwable exceptionThatWasThrown = Assertions.assertThrows(BadRequestException.class, () -> {
			Cidade cidade = new Cidade();
			cidadeService.insert(cidade);
		});		
		MatcherAssert.assertThat(exceptionThatWasThrown.getMessage(), IsEqual.equalTo("O nome da cidade não pode ser nulo ou vazio"));
	}
	
	@Test
	public void incluir_cidade_nome_maior_100() {
		Throwable exceptionThatWasThrown = Assertions.assertThrows(BadRequestException.class, () -> {
			Cidade cidade = new Cidade();
			cidade.setNome(RandomStringUtils.randomAlphabetic(101));
			cidadeService.insert(cidade);
		});		
		MatcherAssert.assertThat(exceptionThatWasThrown.getMessage(), IsEqual.equalTo("O nome da cidade não pode ter mais de 100 caracteres"));
	}
	
	@Test
	public void incluir_cidade_estado_null() {
		Throwable exceptionThatWasThrown = Assertions.assertThrows(BadRequestException.class, () -> {
			Cidade cidade = new Cidade();
			cidade.setNome(RandomStringUtils.randomAlphabetic(100));
			cidadeService.insert(cidade);
		});		
		MatcherAssert.assertThat(exceptionThatWasThrown.getMessage(), IsEqual.equalTo("O estado da cidade não pode ser nulo"));
	}
	
}

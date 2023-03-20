package com.empresa.colaboradores.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.empresa.colaboradores.dto.ColaboradorRequest;
import com.empresa.colaboradores.entity.Colaborador;
import com.empresa.colaboradores.repository.ColaboradorRepository;

@SpringBootTest
public class ColaboradorServiceTest {
	
	@Autowired
	private ColaboradorService colaboradorService;
	
	@MockBean
	private ColaboradorRepository colaboradorRepository;
	
	private static Optional<Colaborador> colaborador;
	
	private static ColaboradorRequest colaboradorRequest;

	@BeforeAll
	public static void carregarDados() {
		colaborador = Optional
				.of(Colaborador.builder().nome("Jose")
				.senha("8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92")
				.score(1)
				.build());
		colaboradorRequest = ColaboradorRequest.builder().nome("Jose")
				.senha("123456").build();
	}
	
	@Test
	public void deveConsultarColaborador() {
		List<Colaborador> colaboradores = new ArrayList<>();
		colaboradores.add(colaborador.get());
		when(colaboradorService.consultar()).thenReturn(colaboradores);
		assertEquals(this.colaboradorService.consultar(), colaboradores);
	}
	
	@Test
	public void deveLancarExcecaoAtualizarColaborador() {
		when(colaboradorRepository.findById(1)).thenReturn(Optional.empty());
		assertThrows(EntityNotFoundException.class, () -> this.colaboradorService.atualizar(colaboradorRequest , 1));
	}
	
	@Test
	public void deveAtualizarColaborador() {
		when(colaboradorRepository.findById(1)).thenReturn(colaborador);
		when(colaboradorRepository.save(colaborador.get())).thenReturn(colaborador.get());
		String nomeAtualizado = "Paulo";
		colaboradorRequest.setNome(nomeAtualizado);
		Colaborador colcaboradorAtualizado = colaboradorService.atualizar(colaboradorRequest, 1);
		assertEquals(nomeAtualizado, colcaboradorAtualizado.getNome());
	}
	
	@Test
	public void deveCadastrarColaboradorSemLider() {
		when(colaboradorRepository.save(colaborador.get())).thenReturn(colaborador.get());
		assertEquals(colaboradorService.cadastrar(colaboradorRequest), colaborador.get());
	}
	
	@Test
	public void deveCadastrarColaboradorComLider() {
		when(colaboradorRepository.save(colaborador.get())).thenReturn(colaborador.get());
		colaboradorRequest.setIdLider(2);
		
		Optional<Colaborador> colaboradorLider = Optional
				.of(Colaborador.builder().nome("Jose")
				.senha("8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92")
				.score(1)
				.build());
		when(colaboradorRepository.findById(2)).thenReturn(colaboradorLider);
		
		assertEquals(colaboradorService.cadastrar(colaboradorRequest), colaborador.get());
	}

}

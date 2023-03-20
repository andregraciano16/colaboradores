package com.empresa.colaboradores.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import com.empresa.colaboradores.dto.ColaboradorRequest;
import com.empresa.colaboradores.entity.Colaborador;
import com.empresa.colaboradores.service.ColaboradorService;

@AutoConfigureJsonTesters
@WebMvcTest(ColaboradorController.class)
public class ColaboradorControllerTest {

	private static final String URL_SERVICO = "/colaboradores";
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private JacksonTester<ColaboradorRequest> jsonColaboradrRequest;
	
	@Autowired
	private JacksonTester<Colaborador> jsonColaborador;
	
	@Autowired
	private JacksonTester<List<Colaborador>> jsonListColaborador;
	
	@MockBean
	private ColaboradorService colaboradorService;
	
	@Autowired
    private MessageSource messageSource;
	
	private static ColaboradorRequest colaboradorRequest;
	private static Colaborador colaborador;
	
	@BeforeAll
	public static void carregarDados() {
		colaboradorRequest = ColaboradorRequest.builder().nome("Maria da Silva").senha("123456").build();
		colaborador = Colaborador.builder().id(1).nome("Maria da Silva").senha("123456").score(null).subordinados(null).build();
	}
	
	@Test 
	public void deveCadastrarColaborador() throws Exception {
		given(colaboradorService.cadastrar(colaboradorRequest)).willReturn(colaborador);

		MockHttpServletResponse response = mvc.perform(post(URL_SERVICO).accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(jsonColaboradrRequest.write(colaboradorRequest).getJson()))
				.andReturn().getResponse();
		
		assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
		assertThat(response.getContentAsString()).isEqualTo(jsonColaborador.write(colaborador).getJson());
	} 
	
	@Test 
	public void deveAtualizarColaborador() throws Exception {
		given(colaboradorService.atualizar(colaboradorRequest, 1)).willReturn(colaborador);

		MockHttpServletResponse response = mvc.perform(put(URL_SERVICO + "/{id}", 1).accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(jsonColaboradrRequest.write(colaboradorRequest).getJson()))
				.andReturn().getResponse();
		
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(response.getContentAsString()).isEqualTo(jsonColaborador.write(colaborador).getJson());
	} 
	
	@Test 
	public void deveConsultarColaborador() throws Exception {
		List<Colaborador> colaboradores = new ArrayList<>();
		colaboradores.add(colaborador);
		
		given(colaboradorService.consultar()).willReturn(colaboradores);

		MockHttpServletResponse response = mvc.perform(get(URL_SERVICO).accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON))
				.andReturn().getResponse();
		
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(response.getContentAsString()).isEqualTo(jsonListColaborador.write(colaboradores).getJson());
	} 	
	
	@Test 
	public void deveDeletarColaborador() throws Exception {

		MockHttpServletResponse response = mvc.perform(delete(URL_SERVICO + "/{id}", 1).accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON))
				.andReturn().getResponse();
		
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(response.getContentAsString()).isEqualTo(messageSource.getMessage("removido.com.sucesso", null, null));
	} 
	
}

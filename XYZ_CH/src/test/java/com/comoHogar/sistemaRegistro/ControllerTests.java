package com.comoHogar.sistemaRegistro;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.comoHogar.sistemaRegistro.domain.Client;
import com.comoHogar.sistemaRegistro.domain.ClientId;
import com.comoHogar.sistemaRegistro.infraetructure.outputAdapter.ClientService;
import com.comoHogar.sistemaRegistro.infraetructure.outputPort.ClientRepository;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(RestDocumentationExtension.class)
class ControllerTests {

	
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ClientService clientService;
	
	@BeforeEach
	void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation) {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				.apply(documentationConfiguration(restDocumentation)) 
				.build();
	}
	
	@Test
	public void debeCrear() throws Exception {
		
		Client c1 = Client.builder().nombre("Pedro").correo("aaa").grupo("th").build();
		 c1 = clientService.guardar(c1);
		List<Client> list = clientService.listar();
		Client c2 = Client.builder().nombre("Pedro").correo("aaa").grupo("th").build();
		 c2 = clientService.guardar(c2);

		this.mockMvc.perform(get("/cliente/crear")).andDo(print())
				.andDo(document("cliente-crear")); 
	}
	
	
	
		
	@Test
	public void debeListar() throws Exception {
		
		Client c1 = Client.builder().nombre("Pedro").correo("aaa").grupo("th").build();
		 c1 = clientService.guardar(c1);
		Client c2 = Client.builder().nombre("Pedro").correo("aaa").grupo("th").build();
		 c2 = clientService.guardar(c2);
		this.mockMvc.perform(get("/cliente/listar")).andDo(print())
				.andDo(document("cliente-listar")); 
	}
	
	
	
}

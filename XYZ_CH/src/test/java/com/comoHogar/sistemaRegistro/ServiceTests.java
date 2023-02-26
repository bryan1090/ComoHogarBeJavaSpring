package com.comoHogar.sistemaRegistro;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.comoHogar.sistemaRegistro.domain.Client;
import com.comoHogar.sistemaRegistro.domain.ClientId;
import com.comoHogar.sistemaRegistro.infraetructure.outputAdapter.ClientService;
import com.comoHogar.sistemaRegistro.infraetructure.outputPort.ClientRepository;

@SpringBootTest
class ServiceTests {

	@Autowired
	private ClientService clientService;
	
	@Test
	public void guardarCliente() {
		Client c = Client.builder().nombre("Pedro").correo("asd").grupo("th").build();
		 c = clientService.guardar(c);
		assertThat(c).isNotNull();
		assertThat(c.getNombre().equals("Pedro"));
	}
	
	
	@Test
	public void guardarMismoClienteDiferenteGrupo() {
		//prueba de clave compuesta (nombre, grupo)
		Client c1 = Client.builder().nombre("Pedro").correo("aaa").grupo("th").build();
		 c1 = clientService.guardar(c1);
		List<Client> list = clientService.listar();
		Client c2 = Client.builder().nombre("Pedro").correo("aaa").grupo("th").build();
		 c2 = clientService.guardar(c2);
		list = clientService.listar();
		assertThat(list).isNotNull();
		assertThat(list.size()).isEqualTo(2);
	
	}

	@Test
	public void listarClientes() {
		
		List<Client> c = clientService.listar();
		assertThat(c).isNotNull();
		assertThat(c.size()).isGreaterThan(0);
	}
	
	@Test
	public void asd() {
		
		clientService.leerJSON();
		
	}

}

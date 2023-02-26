package com.comoHogar.sistemaRegistro.infraetructure.inputAdapter;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.comoHogar.sistemaRegistro.domain.Client;
import com.comoHogar.sistemaRegistro.infraetructure.inputPort.ClientInputPort;
import com.comoHogar.sistemaRegistro.infraetructure.outputAdapter.ClientService;
import com.comoHogar.sistemaRegistro.infraetructure.outputPort.ClientRepository;


@RestController
@RequestMapping(value="/cliente")
public class ClientController {
	
	@Autowired
	ClientService clienteService;
	
	
	@GetMapping(value="/listar",produces = MediaType.APPLICATION_JSON_VALUE)
	public  List<Client> listar() {
		
		return clienteService.listar();
	
	}
	
	@PostMapping(value="/crear",produces = MediaType.APPLICATION_JSON_VALUE)
	public  Client nuevo(@RequestBody Client client) {
		
		return clienteService.guardar(client);
	
	}
	
}

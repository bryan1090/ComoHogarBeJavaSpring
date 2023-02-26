package com.comoHogar.sistemaRegistro.infraetructure.outputPort;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.comoHogar.sistemaRegistro.domain.Client;
import com.comoHogar.sistemaRegistro.domain.ClientId;
@Repository
public interface ClientRepository extends CrudRepository<Client, ClientId>{
	List<Client> findAll();

	
}

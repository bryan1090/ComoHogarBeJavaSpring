package com.comoHogar.sistemaRegistro.infraetructure.inputPort;

import java.util.List;
import java.util.Optional;

import com.comoHogar.sistemaRegistro.domain.Client;
import com.comoHogar.sistemaRegistro.domain.ClientId;

public interface ClientInputPort {

	List<Client> listar();

    Client guardar(Client client);
}

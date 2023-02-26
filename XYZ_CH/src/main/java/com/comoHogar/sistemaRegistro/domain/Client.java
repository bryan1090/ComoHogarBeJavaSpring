package com.comoHogar.sistemaRegistro.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@IdClass(ClientId.class)
@Entity
public class Client {
	@Id
	private String nombre;

	@Id
	private String grupo;
	private String correo;
	private String telefono;
	private String beneficio;
}

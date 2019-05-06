/**
 * 
 */
package com.salesianostriana.dam.gestionhermandad.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Esta clase modela las solicitudes de puesto destacado en la cofradía que
 * pueden hacer los hermanos.
 * 
 * @author José Antonio Llamas Álvarez
 *
 */
@Data
@NoArgsConstructor
@Entity
public class SolicitudPuesto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private LocalDate fechaSolicitud;

	@ManyToOne
	private Puesto puesto;

	@ManyToOne
	private Hermano hermano;

	public SolicitudPuesto(LocalDate fechaSolicitud, Puesto puesto, Hermano hermano) {
		super();
		this.fechaSolicitud = fechaSolicitud;
		this.puesto = puesto;
		this.hermano = hermano;
	}

}

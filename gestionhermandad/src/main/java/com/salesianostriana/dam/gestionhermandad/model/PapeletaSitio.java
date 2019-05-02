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
 * Esta clase modela las Papeletas de sitio para salir en la Cofradía.
 * 
 * @author José Antonio Llamas Álvarez
 *
 */
@Data
@NoArgsConstructor
@Entity
public class PapeletaSitio {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private LocalDate fecha;

	@ManyToOne
	private Cofradia anyo;

	/**
	 * @param fecha Fecha de expedición de la papeleta.
	 * @param anyo  Cofradía del año a la que pertenece la papeleta.
	 */
	public PapeletaSitio(LocalDate fecha, Cofradia anyo) {
		super();
		this.fecha = fecha;
	}

}

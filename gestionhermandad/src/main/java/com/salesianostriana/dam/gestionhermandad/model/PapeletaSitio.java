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

import org.springframework.format.annotation.DateTimeFormat;

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

	@DateTimeFormat(pattern = "dd-mm-yyyy")
	private LocalDate fecha;

	private int anyo;

	@ManyToOne
	private Puesto puesto;

	@ManyToOne
	private Hermano hermano;

	/**
	 * 
	 * @param fecha   Fecha de expedición de la papeleta.
	 * @param anyo    Año de la procesión.
	 * @param puesto  Puesto que se ocupará en la cofradía.
	 * @param hermano Hermano al que se le expide la papeleta de sitio
	 */
	public PapeletaSitio(LocalDate fecha, int anyo, Puesto puesto, Hermano hermano) {
		super();
		this.fecha = fecha;
		this.anyo = anyo;
		this.puesto = puesto;
		this.hermano = hermano;
	}

//	public PapeletaSitio(Puesto puesto, Hermano hermano) {
//		super();
//		this.puesto = puesto;
//		this.hermano = hermano;
//	}
}

/**
 * 
 */
package com.salesianostriana.dam.gestionhermandad.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Esta clase modela los puestos existentes en la cofradía.
 * 
 * @author José Antonio Llamas Álvarez
 *
 */
@Data
@NoArgsConstructor
@Entity
public class Puesto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(unique = true)
	private String nombre;
	private int numero;
	private double precio;
	private int paso;
	private boolean esDestacado;

	/**
	 * 
	 * @param nombre      Nombre del puesto.
	 * @param numero      Número de puestos de este tipo existente en la procesión
	 * @param precio      Precio de la papeleta de sitio de este puesto.
	 * @param paso        Paso al que corresponde el puesto.
	 * @param esDestacado Si se considera puesto destacado o no.
	 */
	public Puesto(String nombre, int numero, double precio, int paso, boolean esDestacado) {
		this.nombre = nombre;
		this.numero = numero;
		this.precio = precio;
		this.paso = paso;
		this.esDestacado = esDestacado;
	}

}

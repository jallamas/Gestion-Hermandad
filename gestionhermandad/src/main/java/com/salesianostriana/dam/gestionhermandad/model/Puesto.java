/**
 * 
 */
package com.salesianostriana.dam.gestionhermandad.model;

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

	private String nombre;
	private int numero;
	private int tramo;
	private double precio;
	private int paso;
	private boolean esDestacado;
	private String descripcion;
}

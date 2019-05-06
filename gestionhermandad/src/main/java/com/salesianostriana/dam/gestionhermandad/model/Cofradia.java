/**
 * 
 */
package com.salesianostriana.dam.gestionhermandad.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Esta clase modela la procesión del día de salida (cofradía).
 * 
 * @author José Antonio Llamas Álvarez
 *
 */
@Data
@NoArgsConstructor
@Entity
public class Cofradia {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@DateTimeFormat(pattern="yyyy")
	private LocalDate anyo;

	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(mappedBy = "cofradia", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private List<PapeletaSitio> listaPapeletas;

	/**
	 * Constructor con los parámetros menos el id (que se autogenera)
	 * 
	 * @param anyo           Año de salida.
	 * @param listaPapeletas Lista de papeletas expedidas.
	 */
	public Cofradia(LocalDate anyo, List<PapeletaSitio> listaPapeletas) {
		super();
		this.anyo = anyo;
		this.listaPapeletas = listaPapeletas;
	}

}

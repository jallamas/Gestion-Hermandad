/**
 * 
 */
package com.salesianostriana.dam.gestionhermandad.model;

import java.time.LocalDate;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Esta clase modela los hermanos que causaron baja y pasan al histórico.
 * 
 * @author José Antonio Llamas Álvarez
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@DiscriminatorValue("HermanoHistorico")
@Entity
public class HermanoHistorico extends Hermano {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private LocalDate fechaBaja;

	/**
	 * @param nombre          Nombre del hermano
	 * @param apellidos       Apellidos del hermano.
	 * @param telefono        Teléfono fijo.
	 * @param movil           Teléfono móvil.
	 * @param direccion       Dirección postal.
	 * @param provincia       Provincia de residencia.
	 * @param localidad       Localidad de residencia.
	 * @param codigoPostal    Código Postal/ZIP code.
	 * @param pais            Pais de residencia.
	 * @param fechaNacimiento Fecha de nacimiento.
	 * @param email           Correo electrónico.
	 * @param usuario         Nombre de usuario para acceder al área de gestión de
	 *                        la web.
	 * @param password        Contraseña para el área de gestión.
	 * @param fechaAlta       Fecha de confirmación del alta en la Hermandad.
	 * @param numHermano      Número de hermano que indica la antigüedad.
	 * @param isAdmin         Booleano que indica si es administrador de la
	 *                        aplicación.
	 * @param fechaBaja       Fecha en la que causó baja en la Hermandad.
	 */
	public HermanoHistorico(long numExpediente, String nombre, String apellidos, String telefono, String movil,
			String direccion, String provincia, String localidad, String codigoPostal, String pais,
			LocalDate fechaNacimiento, String email, String usuario, String password, LocalDate fechaAlta,
			int numHermano, LocalDate fechaBaja) {
		super(numExpediente, nombre, apellidos, telefono, movil, direccion, provincia, localidad, codigoPostal, pais,
				fechaNacimiento, email, usuario, password, fechaAlta, numHermano);
		this.fechaBaja = fechaBaja;
	}
	
	public HermanoHistorico(long numExpediente, String nombre, String apellidos, String telefono, String movil,
			String direccion, String provincia, String localidad, String codigoPostal, String pais,
			LocalDate fechaNacimiento, String email, String usuario, String password, LocalDate fechaAlta,
			 LocalDate fechaBaja) {
		super(numExpediente, nombre, apellidos, telefono, movil, direccion, provincia, localidad, codigoPostal, pais,
				fechaNacimiento, email, usuario, password, fechaAlta);
		this.fechaBaja = fechaBaja;
	}

}

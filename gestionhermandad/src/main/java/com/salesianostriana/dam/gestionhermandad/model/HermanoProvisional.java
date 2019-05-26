package com.salesianostriana.dam.gestionhermandad.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Esta clase modela los hermanos que solicitan el alta en el sistema.
 * 
 * @author José Antonio Llamas Álvarez
 *
 */
@Data
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("HermanoProvisional")
@Entity
@Table(name = "Hermanos")
public class HermanoProvisional {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private long numExpediente;

	private String nombre;
	private String apellidos;
	private String telefono;
	private String movil;
	private String direccion;
	private String provincia;
	private String localidad;
	private String codigoPostal;
	private String pais;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaNacimiento;
	private String email;
	@Column(unique = true)
	private String usuario;
	private String password;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaAlta;

	/**
	 * Constructor con todos los parámetros excepto el número de expediente (id) que
	 * es generado de forma automática.
	 * 
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
	 */

	public HermanoProvisional(long numExpediente, String nombre, String apellidos, String telefono, String movil,
			String direccion, String provincia, String localidad, String codigoPostal, String pais,
			LocalDate fechaNacimiento, String email, String usuario, String password, LocalDate fechaAlta) {
		super();
		this.numExpediente = numExpediente;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.telefono = telefono;
		this.movil = movil;
		this.direccion = direccion;
		this.provincia = provincia;
		this.localidad = localidad;
		this.codigoPostal = codigoPostal;
		this.pais = pais;
		this.fechaNacimiento = fechaNacimiento;
		this.email = email;
		this.usuario = usuario;
		this.password = password;
		this.fechaAlta = fechaAlta;
	}

}

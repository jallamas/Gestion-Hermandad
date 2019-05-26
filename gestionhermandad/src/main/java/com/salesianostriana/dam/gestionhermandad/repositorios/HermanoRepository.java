/**
 * 
 */
package com.salesianostriana.dam.gestionhermandad.repositorios;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.salesianostriana.dam.gestionhermandad.model.Hermano;

/**
 * @author José Antonio Llamas Álvarez
 *
 */
public interface HermanoRepository extends JpaRepository<Hermano, Long> {

	/**
	 * Consulta que devuelve una lista de todos los hermanos del tipo Hermano.
	 */
	@Query("select h from Hermano h where TYPE(h)=Hermano order by h.apellidos")
	List<Hermano> findAll();

	/**
	 * Consulta que devuelve una lista de todos los hermanos que hayan solicitado la
	 * baja en la Hermandad.
	 */
	List<Hermano> findBySolicitaBajaTrue();

	/**
	 * Consulta que devuelve una lista de todos los hermanos del tipo Hermano
	 * ordenados por fecha de alta en la Hermandad.
	 */
	@Query("select h from Hermano h where TYPE(h)=Hermano order by h.fechaAlta")
	List<Hermano> findByFechaAlta();

	/**
	 * Consulta que devuelve el hermano que tiene un nombre de usuario concreto.
	 * 
	 * @param usuario El nombre de usuario a buscar.
	 */
	Hermano findFirstByUsuario(String usuario);

	/**
	 * Método que pasándole una fecha, nos obtiene todos los hermanos con fecha
	 * igual o anterior.
	 * 
	 * @param fechaReferencia Fecha
	 */
//	@Query("select h from Hermano h where TYPE(h)=Hermano and h.fechaNacimiento <= :fechaReferencia order by h.apellidos")
	List<Hermano> findByFechaNacimientoBefore(@Param("fechaReferencia") LocalDate fechaReferencia);

	/**
	 * Consulta que obtiene el valor máximo del número de hermano de entre los
	 * activos
	 * 
	 * @return Un entero equivalente a número de hermano más alto de entre todos los
	 *         hermanos en activo.
	 */
	@Query("select max(h.numHermano) from Hermano h where TYPE(h)=Hermano")
	int contarNumeroHermanos();

	/**
	 * Consulta que devuelve una lista de los hermanos que tienen sacada la papeleta
	 * de sitio de este año (Atributo papeletaSacada = true).
	 * 
	 * @return
	 */
	List<Hermano> findByPapeletaSacadaTrue();
}

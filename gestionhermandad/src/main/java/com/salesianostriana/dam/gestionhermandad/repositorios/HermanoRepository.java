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

	@Query("select h from Hermano h where TYPE(h)=Hermano order by h.apellidos")
	List<Hermano> findAll();

	List<Hermano> findBySolicitaBajaTrue();

	@Query("select h from Hermano h where TYPE(h)=Hermano order by h.fechaAlta")
	List<Hermano> findByFechaAlta();

	Hermano findFirstByUsuario(String usuario);

	/**
	 * Método que pasándole una fecha, nos obtiene todos los hermanos con fecha igual o anterior. 
	 * @param fechaReferencia Fecha 
	 * @return
	 */
	@Query("select h from Hermano h where TYPE(h)=Hermano and h.fechaNacimiento <= fechaReferencia order by h.apellidos")
	List<Hermano> findByFechaNacimientoBefore(@Param("fechaReferencia") LocalDate fechaReferencia);

	/**
	 * Consulta que obtiene el número de hermanos activos
	 * 
	 * @return
	 */
	@Query("select max(h.numHermano) from Hermano h where TYPE(h)=Hermano")
	int obtenerNuevoNumHermano();
}

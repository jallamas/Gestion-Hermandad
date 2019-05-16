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

	// @Query(value = "SELECT * FROM HERMANOS WHERE DTYPE='Hermano' ORDER BY
	// APELLIDOS", nativeQuery = true)
	@Query("select h from Hermano h where TYPE(h)=Hermano order by h.apellidos")
	List<Hermano> findAll();

	// @Query(value = "SELECT * FROM HERMANOS WHERE DTYPE='Hermano' AND
	// SOLICITUDBAJA='true'", nativeQuery = true)
	// @Query("select h from Hermano h where TYPE(h)=Hermano and
	// h.solicitaBaja=true")
	List<Hermano> findBySolicitaBajaTrue();

	Hermano findFirstByUsuario(String usuario);

	@Query("select h from Hermano h where TYPE(h)=Hermano and h.fechaNacimiento <= fechaReferencia order by h.apellidos")
	List<Hermano> findByFechaNacimientoBefore(@Param("fechaReferencia") LocalDate fechaReferencia);
}

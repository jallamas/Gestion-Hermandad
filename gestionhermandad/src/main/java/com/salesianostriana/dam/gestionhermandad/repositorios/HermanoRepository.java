/**
 * 
 */
package com.salesianostriana.dam.gestionhermandad.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesianostriana.dam.gestionhermandad.model.Hermano;

/**
 * @author José Antonio Llamas Álvarez
 *
 */
public interface HermanoRepository extends JpaRepository<Hermano, Long> {

	@Query(value = "SELECT * FROM HERMANOS WHERE DTYPE='Hermano' ORDER BY APELLIDOS", nativeQuery = true)
	List<Hermano> findAll();

	@Query(value = "SELECT * FROM HERMANOS WHERE DTYPE='Hermano' AND SOLICITUDBAJA='true'", nativeQuery = true)
	List<Hermano> listarSolicitudesBaja();
}

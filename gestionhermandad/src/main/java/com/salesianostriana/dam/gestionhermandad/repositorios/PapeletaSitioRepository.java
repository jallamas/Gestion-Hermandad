/**
 * 
 */
package com.salesianostriana.dam.gestionhermandad.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.salesianostriana.dam.gestionhermandad.model.PapeletaSitio;

/**
 * @author José Antonio Llamas Álvarez
 *
 */
public interface PapeletaSitioRepository extends JpaRepository<PapeletaSitio, Long> {

	@Query(value="select p from PapeletaSitio p where p.anyo=anyoActual and p.Hermano=hermanoActual")
	List<PapeletaSitio> comprobarPapeletaHermanoAnyo(@Param("anyoActual") int anyoActual, @Param("hermanoActual") Long hermanoActual);
}

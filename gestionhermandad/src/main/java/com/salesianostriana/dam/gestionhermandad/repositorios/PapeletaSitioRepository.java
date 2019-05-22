/**
 * 
 */
package com.salesianostriana.dam.gestionhermandad.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.salesianostriana.dam.gestionhermandad.model.PapeletaSitio;

/**
 * @author José Antonio Llamas Álvarez
 *
 */
public interface PapeletaSitioRepository extends JpaRepository<PapeletaSitio, Long> {

	@Query("select p from PapeletaSitio p where p.anyo=anyoActual and p.Hermano=hermanoActual")
	PapeletaSitio comprobarPapeletaHermanoAnyo(@Param("anyoActual") int anyoActual, @Param("hermanoActual") Long hermanoActual);
}

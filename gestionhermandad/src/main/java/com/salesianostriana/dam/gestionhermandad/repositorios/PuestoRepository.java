/**
 * 
 */
package com.salesianostriana.dam.gestionhermandad.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salesianostriana.dam.gestionhermandad.model.Puesto;

/**
 * @author José Antonio Llamas Álvarez
 *
 */
public interface PuestoRepository extends JpaRepository<Puesto, Long> {

	List<Puesto> findAllByEsDestacadoFalse();
}

/**
 * 
 */
package com.salesianostriana.dam.gestionhermandad.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesianostriana.dam.gestionhermandad.model.HermanoProvisional;

/**
 * @author José Antonio Llamas Álvarez
 *
 */
public interface HermanoProvisionalRepository extends JpaRepository<HermanoProvisional, Long> {

	@Query(value = "SELECT * FROM HERMANOS WHERE DTYPE='HermanoProvisional'", nativeQuery = true)
	List<HermanoProvisional> findAll();
}

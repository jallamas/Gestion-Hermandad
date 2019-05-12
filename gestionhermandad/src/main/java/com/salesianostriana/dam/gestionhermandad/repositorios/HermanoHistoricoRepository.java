/**
 * 
 */
package com.salesianostriana.dam.gestionhermandad.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesianostriana.dam.gestionhermandad.model.HermanoHistorico;

/**
 * @author José Antonio Llamas Álvarez
 *
 */
public interface HermanoHistoricoRepository extends JpaRepository<HermanoHistorico, Long> {

	@Query(value = "SELECT * FROM HERMANOS WHERE DTYPE='HermanoHistorico'", nativeQuery = true)
	List<HermanoHistorico> findAll();
}

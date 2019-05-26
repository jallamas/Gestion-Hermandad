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

	/**
	 * Consulta que devuelve una lista de los hermanos históricos ordenados por
	 * apellidos.
	 */
	@Query("select hh from HermanoHistorico hh where TYPE(hh)=HermanoHistorico order by hh.apellidos")
	List<HermanoHistorico> findAll();
}

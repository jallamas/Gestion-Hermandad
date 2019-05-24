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

	@Query("select hp from HermanoProvisional hp where TYPE(hp)=HermanoProvisional order by hp.apellidos")
	List<HermanoProvisional> findAll();
	
	List<HermanoProvisional> findByUsuario(String usuario);
}

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

	/**
	 * Consulta que devuelve una lista de todos los hermanos provisionales ordenados
	 * por apellidos.
	 */
	@Query("select hp from HermanoProvisional hp where TYPE(hp)=HermanoProvisional order by hp.apellidos")
	List<HermanoProvisional> findAll();

	/**
	 * Método que devuelve la lista con los hermanos con un nombre de usuario
	 * concreto (habrá 0 ó 1). Al no usar una consulta con DTYPE, esta lista busca
	 * en los hermanos provisionales, definitivos e históricos debido a la herencia
	 * de clases entre ellos.
	 * 
	 * @param usuario Nombre de usuario a buscar
	 * @return Lista de hermanos vacía o con un hermano.
	 */
	List<HermanoProvisional> findByUsuario(String usuario);
}

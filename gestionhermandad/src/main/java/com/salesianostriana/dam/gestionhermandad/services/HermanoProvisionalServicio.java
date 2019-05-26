/**
 * 
 */
package com.salesianostriana.dam.gestionhermandad.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.gestionhermandad.model.Hermano;
import com.salesianostriana.dam.gestionhermandad.model.HermanoProvisional;
import com.salesianostriana.dam.gestionhermandad.repositorios.HermanoProvisionalRepository;
import com.salesianostriana.dam.gestionhermandad.services.base.ServicioBase;

/**
 * Clase que contiene los métodos propios que manejan el repositorio de los
 * hermanos provisionales.
 * 
 * @author José Antonio Llamas Álvarez
 *
 */
@Service
public class HermanoProvisionalServicio extends ServicioBase<HermanoProvisional, Long, HermanoProvisionalRepository> {

	@Autowired
	private HermanoServicio hermanoServicio;
	@Autowired
	private HermanoProvisionalServicio hermanoProvisionalServicio;
	protected HermanoProvisionalRepository hermanoProvisionalRepositorio;

	/**
	 * Método que nos permite validar un hermano provisional pasándolo al estado de
	 * Hermano. Usamos el constructor de la clase Hermano y le seteamos los datos
	 * obtenidos del objeto de tipo HermanoProvisional correspondiente. Asignamos el
	 * número de hermano con el método que tenemos para ello en la clase
	 * HermanoServicio.
	 * 
	 * @param hermanoProv EL objeto HermanoProvisional a validar.
	 * @return El objeto Hermano resultante.
	 */
	public Hermano validarHermanoProvisional(HermanoProvisional hermanoProv) {
		Hermano hermano = new Hermano(hermanoProv.getNumExpediente(), hermanoProv.getNombre(),
				hermanoProv.getApellidos(), hermanoProv.getTelefono(), hermanoProv.getMovil(),
				hermanoProv.getDireccion(), hermanoProv.getProvincia(), hermanoProv.getLocalidad(),
				hermanoProv.getCodigoPostal(), hermanoProv.getPais(), hermanoProv.getFechaNacimiento(),
				hermanoProv.getEmail(), hermanoProv.getUsuario(), hermanoProv.getPassword(), hermanoProv.getFechaAlta(),
				hermanoServicio.obtenerNuevoNumHermano());
		hermanoProvisionalServicio.deleteById(hermanoProv.getId());
		hermanoServicio.save(hermano);
		return hermano;
	}

	/**
	 * Método que devuelve la lista con los hermanos con un nombre de usuario
	 * concreto (habrá 0 ó 1). Al no usar una consulta con DTYPE, esta lista busca
	 * en los hermanos provisionales, definitivos e históricos debido a la herencia
	 * de clases entre ellos.
	 * 
	 * @param usuario Nombre de usuario a buscar
	 * @return Lista de hermanos vacía o con un hermano.
	 */
	public List<HermanoProvisional> findByUsuario(String usuario) {
		return repositorio.findByUsuario(usuario);
	}
}

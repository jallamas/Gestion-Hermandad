/**
 * 
 */
package com.salesianostriana.dam.gestionhermandad.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.gestionhermandad.model.Hermano;
import com.salesianostriana.dam.gestionhermandad.repositorios.HermanoRepository;
import com.salesianostriana.dam.gestionhermandad.services.base.ServicioBase;

/**
 * @author José Antonio Llamas Álvarez
 *
 */
@Service
public class HermanoServicio extends ServicioBase<Hermano, Long, HermanoRepository> {

	protected HermanoRepository hermanoRepositorio;

	public List<Hermano> listarSolicitudesBaja() {
		return repositorio.listarSolicitudesBaja();
	}
}

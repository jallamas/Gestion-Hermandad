package com.salesianostriana.dam.gestionhermandad.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.gestionhermandad.model.Puesto;
import com.salesianostriana.dam.gestionhermandad.repositorios.PuestoRepository;
import com.salesianostriana.dam.gestionhermandad.services.base.ServicioBase;

/**
 * Clase que contiene los métodos que operan con los puestos en la base de
 * datos.
 * 
 * @author José Antonio Llamas Álvarez
 *
 */
@Service
public class PuestoServicio extends ServicioBase<Puesto, Long, PuestoRepository> {

	@Autowired
	PuestoRepository puestoRepository;

	/**
	 * Método que devuelve una lista de los puestos en la cofradía que NO son
	 * considerados puestos destacados.
	 */
	public List<Puesto> listarPuestosNormales() {
		return puestoRepository.findAllByEsDestacadoFalse();
	}
}

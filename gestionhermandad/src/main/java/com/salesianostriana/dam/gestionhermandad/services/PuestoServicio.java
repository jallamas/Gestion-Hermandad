/**
 * 
 */
package com.salesianostriana.dam.gestionhermandad.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.gestionhermandad.model.Puesto;
import com.salesianostriana.dam.gestionhermandad.repositorios.PuestoRepository;
import com.salesianostriana.dam.gestionhermandad.services.base.ServicioBase;

/**
 * @author José Antonio Llamas Álvarez
 *
 */
@Service
public class PuestoServicio extends ServicioBase<Puesto, Long, PuestoRepository> {

	@Autowired
	PuestoRepository puestoRepository;
	
	public List<Puesto> listarPuestosNormales(){
		return puestoRepository.findAllByEsDestacadoFalse();
	}
}

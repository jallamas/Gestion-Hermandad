/**
 * 
 */
package com.salesianostriana.dam.gestionhermandad.services;

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
}

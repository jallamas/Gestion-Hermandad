/**
 * 
 */
package com.salesianostriana.dam.gestionhermandad.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.gestionhermandad.model.Hermano;
import com.salesianostriana.dam.gestionhermandad.model.PapeletaSitio;
import com.salesianostriana.dam.gestionhermandad.repositorios.PapeletaSitioRepository;
import com.salesianostriana.dam.gestionhermandad.services.base.ServicioBase;

/**
 * @author José Antonio Llamas Álvarez
 *
 */
@Service
public class PapeletaSitioServicio extends ServicioBase<PapeletaSitio, Long, PapeletaSitioRepository> {

	
	protected PapeletaSitioRepository papeletaSitioRepository;

	public List<PapeletaSitio> comprobarPapeletaHermanoAnyo(Hermano hermano) {
		return papeletaSitioRepository.comprobarPapeletaHermanoAnyo(LocalDate.now().getYear(), hermano.getId());
	}
}

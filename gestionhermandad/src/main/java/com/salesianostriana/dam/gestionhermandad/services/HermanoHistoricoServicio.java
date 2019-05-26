package com.salesianostriana.dam.gestionhermandad.services;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.gestionhermandad.model.Hermano;
import com.salesianostriana.dam.gestionhermandad.model.HermanoHistorico;
import com.salesianostriana.dam.gestionhermandad.repositorios.HermanoHistoricoRepository;
import com.salesianostriana.dam.gestionhermandad.services.base.ServicioBase;

/**
 * Clase que contiene los métodos propios que manejan el repositorio de los
 * hermanos históricos.
 * 
 * @author José Antonio Llamas Álvarez
 *
 */
@Service
public class HermanoHistoricoServicio extends ServicioBase<HermanoHistorico, Long, HermanoHistoricoRepository> {
	@Autowired
	private HermanoHistoricoServicio hermanoHistoricoServicio;
	@Autowired
	private HermanoServicio hermanoServicio;

	/**
	 * Método que permite pasar un hermano que está en el histórico al estado de
	 * hermano activo de nuevo (asignándole una nueva fecha de alta -la actual- y un
	 * nuevo número de hermano). Usamos el constructor de la clase Hermano y le
	 * seteamos los datos obtenidos del objeto de tipo HermanoHistorico
	 * correspondiente.
	 * 
	 * @param hermanoHistorico El HermanoHistorico a pasar a Hermano
	 * @return El objeto del tipo Hermano resultante.
	 */
	public Hermano reactivarHermanoHistorico(HermanoHistorico hermanoHistorico) {
		Hermano hermano = new Hermano(hermanoHistorico.getNumExpediente(), hermanoHistorico.getNombre(),
				hermanoHistorico.getApellidos(), hermanoHistorico.getTelefono(), hermanoHistorico.getMovil(),
				hermanoHistorico.getDireccion(), hermanoHistorico.getProvincia(), hermanoHistorico.getLocalidad(),
				hermanoHistorico.getCodigoPostal(), hermanoHistorico.getPais(), hermanoHistorico.getFechaNacimiento(),
				hermanoHistorico.getEmail(), hermanoHistorico.getUsuario(), hermanoHistorico.getPassword(),
				hermanoHistorico.getFechaAlta(), 1);
		hermano.setFechaAlta(LocalDate.now());
		hermano.setNumHermano(hermanoServicio.obtenerNuevoNumHermano());
		hermanoHistoricoServicio.deleteById(hermanoHistorico.getId());
		hermanoServicio.save(hermano);
		return hermano;
	}
}

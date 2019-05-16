/**
 * 
 */
package com.salesianostriana.dam.gestionhermandad.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.gestionhermandad.model.Hermano;
import com.salesianostriana.dam.gestionhermandad.model.HermanoHistorico;
import com.salesianostriana.dam.gestionhermandad.repositorios.HermanoHistoricoRepository;
import com.salesianostriana.dam.gestionhermandad.services.base.ServicioBase;

/**
 * @author José Antonio Llamas Álvarez
 *
 */
@Service
public class HermanoHistoricoServicio extends ServicioBase<HermanoHistorico, Long, HermanoHistoricoRepository> {
	@Autowired
	private HermanoHistoricoServicio hermanoHistoricoServicio;
	@Autowired
	private HermanoServicio hermanoServicio;

	public Hermano reactivarHermanoHistorico(HermanoHistorico hermanoHistorico) {
		Hermano hermano = new Hermano(hermanoHistorico.getNumExpediente(), hermanoHistorico.getNombre(),
				hermanoHistorico.getApellidos(), hermanoHistorico.getTelefono(), hermanoHistorico.getMovil(),
				hermanoHistorico.getDireccion(), hermanoHistorico.getProvincia(), hermanoHistorico.getLocalidad(),
				hermanoHistorico.getCodigoPostal(), hermanoHistorico.getPais(), hermanoHistorico.getFechaNacimiento(),
				hermanoHistorico.getEmail(), hermanoHistorico.getUsuario(), hermanoHistorico.getPassword(),
				hermanoHistorico.getFechaAlta(), 1);
		hermanoHistoricoServicio.deleteById(hermanoHistorico.getId());
		hermanoServicio.save(hermano);
		return hermano;
	}
}

/**
 * 
 */
package com.salesianostriana.dam.gestionhermandad.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.gestionhermandad.model.Hermano;
import com.salesianostriana.dam.gestionhermandad.model.HermanoProvisional;
import com.salesianostriana.dam.gestionhermandad.repositorios.HermanoProvisionalRepository;
import com.salesianostriana.dam.gestionhermandad.services.base.ServicioBase;

/**
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

	public Hermano validarHermanoProvisional(HermanoProvisional hermanoProv) {
		Hermano hermano = new Hermano(hermanoProv.getNombre(), hermanoProv.getApellidos(), hermanoProv.getTelefono(),
				hermanoProv.getMovil(), hermanoProv.getDireccion(), hermanoProv.getProvincia(),
				hermanoProv.getLocalidad(), hermanoProv.getCodigoPostal(), hermanoProv.getPais(),
				hermanoProv.getFechaNacimiento(), hermanoProv.getEmail(), hermanoProv.getUsuario(),
				hermanoProv.getPassword(), hermanoProv.getFechaAlta(), 1);
		hermanoProvisionalServicio.deleteById(hermanoProv.getId());
		hermanoServicio.save(hermano);
		return hermano;
	}

}

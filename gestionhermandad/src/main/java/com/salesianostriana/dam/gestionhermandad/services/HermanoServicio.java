/**
 * 
 */
package com.salesianostriana.dam.gestionhermandad.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.gestionhermandad.model.Hermano;
import com.salesianostriana.dam.gestionhermandad.model.HermanoHistorico;
import com.salesianostriana.dam.gestionhermandad.repositorios.HermanoRepository;
import com.salesianostriana.dam.gestionhermandad.services.base.ServicioBase;

/**
 * @author José Antonio Llamas Álvarez
 *
 */
@Service
public class HermanoServicio extends ServicioBase<Hermano, Long, HermanoRepository> {

	@Autowired
	private HermanoServicio hermanoServicio;
	@Autowired
	private HermanoHistoricoServicio hermanoHistoricoServicio;
	protected HermanoRepository hermanoRepositorio;

	public HermanoHistorico pasarHermanoHistorico(Hermano hermano) {
		HermanoHistorico hermanoHistorico = new HermanoHistorico(hermano.getNumExpediente(), hermano.getNombre(),
				hermano.getApellidos(), hermano.getTelefono(), hermano.getMovil(), hermano.getDireccion(),
				hermano.getProvincia(), hermano.getLocalidad(), hermano.getCodigoPostal(), hermano.getPais(),
				hermano.getFechaNacimiento(), hermano.getEmail(), hermano.getUsuario(), hermano.getPassword(),
				hermano.getFechaAlta(), LocalDate.now());
		hermanoServicio.deleteById(hermano.getId());
		hermanoHistoricoServicio.save(hermanoHistorico);
		return hermanoHistorico;
	}

	public List<Hermano> listarSolicitudesBaja() {
		return repositorio.findBySolicitaBajaTrue();
	}

	public List<Hermano> hermanosPorFechaAlta() {
		return repositorio.findByFechaAlta();
	}

	public void renumerarHermanos() {
		for (Hermano h : hermanosPorFechaAlta()) {
			h.setNumHermano(hermanosPorFechaAlta().indexOf(h) + 1);
			hermanoServicio.save(h);
		}
	}

	public List<Hermano> mostrarCenso() {
		LocalDate fechaReferencia = LocalDate.now().minusYears(18);
		return hermanoRepositorio.findByFechaNacimientoBefore(fechaReferencia);
	}

	public Hermano buscarPorUsuario(String usuario) {
		return repositorio.findFirstByUsuario(usuario);
	}
}

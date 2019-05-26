/**
 * 
 */
package com.salesianostriana.dam.gestionhermandad.services;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.gestionhermandad.model.Hermano;
import com.salesianostriana.dam.gestionhermandad.model.HermanoHistorico;
import com.salesianostriana.dam.gestionhermandad.repositorios.HermanoRepository;
import com.salesianostriana.dam.gestionhermandad.services.base.ServicioBase;

/**
 * Métodos para operar sobre los hermanos activos.
 * 
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

	/**
	 * Método para pasar a histórico un hermano. Añade la fecha de baja actual.
	 * 
	 * @param hermano El hermano que se quiere pasar a histórico.
	 * @return Devuelve el objeto hermano histórico correspondiente.
	 */
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

	/**
	 * Método que permite a un hermano solicitar la baja en la hermandad.
	 * 
	 * @param id El id del hermano.
	 */
	public void solicitarBaja(Long id) {
		Hermano h;
		h = this.findById(id);
		h.setSolicitaBaja(true);
		hermanoServicio.edit(h);
	}

	/**
	 * Método que anula la solicitud de baja en la Hermandad de un hermano
	 * 
	 * @param id El id del Hermano
	 */
	public void anularBaja(Long id) {
		Hermano h;
		h = this.findById(id);
		h.setSolicitaBaja(false);
		hermanoServicio.edit(h);
	}

	/**
	 * Método que nos busca una lista de los hermanos que han solicitado la baja en
	 * la hermandad.
	 * 
	 * @return La lista de los hermano correspondientes.
	 */
	public List<Hermano> listarSolicitudesBaja() {
		return repositorio.findBySolicitaBajaTrue();
	}

	/**
	 * Método para obtener un List con los hermanos ordenados por fecha de alta en
	 * la hermandad de manera ascendente.
	 * 
	 * @return La lista de hermanos ordenados
	 */
	public List<Hermano> hermanosPorFechaAlta() {
		return repositorio.findByFechaAlta();
	}

	/**
	 * Método que renumera el atributo numHermano de los hermanos activos. Se
	 * realiza obteniendo un List de los hermanos activos ordenados por fecha de
	 * alta y seteando a cada uno la posición en la lista más uno.
	 */
	public void renumerarHermanos() {
		for (Hermano h : hermanosPorFechaAlta()) {
			h.setNumHermano(hermanosPorFechaAlta().indexOf(h) + 1);
			hermanoServicio.save(h);
		}
	}

	/**
	 * Método que busca los hermanos mayores de 18 años
	 * 
	 * @return La lista de hermanos mayores de edad.
	 */
	public List<Hermano> mostrarCenso() {
		LocalDate fechaReferencia = LocalDate.now().minusYears(18);
		return hermanoRepositorio.findByFechaNacimientoBefore(fechaReferencia);
	}

	/**
	 * Método que obtiene el número que le corresponde a un hermano nuevo,
	 * añadiéndole 1 al resultado de contar los hermanos existentes en la base de
	 * datos
	 * 
	 * @return El número a setear al hermano nuevo.
	 */
	public int obtenerNuevoNumHermano() {
		return repositorio.contarNumeroHermanos() + 1;
	}

	/**
	 * Busca el hermano con el nombre de usuario dado
	 * 
	 * @param usuario Nombre de usuario buescado
	 * @return objeto Hermano que cumple la condicioón
	 */
	public Hermano buscarPorUsuario(String usuario) {
		return repositorio.findFirstByUsuario(usuario);
	}

	/**
	 * Método que busca todos los hermanos que tienen la papeleta de sitio de este
	 * año ya sacada.
	 * 
	 * @return Devuelve la lista de hermanos que cumplen esa condición.
	 */
	public List<Hermano> buscarPapeletaSacada() {
		return repositorio.findByPapeletaSacadaTrue();
	}

	/**
	 * Este método setea a false el atributo papeletaSacada de todos los hermanos
	 */
	public void resetearPapeletaSacada() {
		List<Hermano> listaHermanos = hermanoServicio.findAll();
		for (Hermano h : listaHermanos) {
			h.setPapeletaSacada(false);
			hermanoServicio.edit(h);
		}
	}

	/**
	 * Busca el hermano que está logueado
	 * 
	 * @param p objeto referencia de java security
	 * @return El objeto Hermano buscado o null si nop hay ninguno logueado en ese
	 *         momento.
	 */
	public Hermano buscarHermanoLogeado(Principal p) {
		Hermano hermano;
		if (p != null) {
			hermano = this.buscarPorUsuario(p.getName());
			return hermano;
		} else {
			return null;
		}
	}
}

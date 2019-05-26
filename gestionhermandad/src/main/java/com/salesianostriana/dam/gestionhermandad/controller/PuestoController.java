/**
 * 
 */
package com.salesianostriana.dam.gestionhermandad.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianostriana.dam.gestionhermandad.model.Puesto;
import com.salesianostriana.dam.gestionhermandad.services.PuestoServicio;

/**
 * Clase que contiene los métodos que controlan las plantillas que permiten
 * manejar al administrador los tipos de puesto que se pueden ocupar en la
 * cofradía.
 * 
 * @author José Antonio Llamas Álvarez
 *
 */
@Controller
public class PuestoController {

	private PuestoServicio puestoServicio;

	/**
	 * Constructor con el servicio.
	 * 
	 * @param puestoServicio
	 */
	public PuestoController(PuestoServicio puestoServicio) {
		super();
		this.puestoServicio = puestoServicio;
	}

	/**
	 * Método que muestra el formulario para crear un nuevo puesto.
	 * 
	 * @param model Modelo para pasar los datos al formulario
	 * @return Plantilla que muestra el formulario.
	 */
	@GetMapping("/admin/nuevopuesto")
	public String mostrarNuevoPuestoForm(Model model) {
		model.addAttribute("puesto", new Puesto());
		return "admin/puesto_form";
	}

	/**
	 * Método que procesa los datos del formulario de crear un nuevo puesto y guarda
	 * el registro en la base de datos.
	 * 
	 * @param puesto El objeto de tipo Puesto creado.
	 * @return Plantilla que muestra la lista de puestos existente.
	 */
	@PostMapping("/admin/nuevopuesto/submit")
	public String procesarNuevoPuesto(@ModelAttribute("puesto") Puesto puesto) {
		puestoServicio.save(puesto);
		return "redirect:/admin/puestos";
	}

	/**
	 * Método que muestra la lista de puestos existentes.
	 * 
	 * @param model Modelo para pasar la lista a la plantilla
	 * @return Plentilla que muestra la lista en pantalla
	 */
	@GetMapping("/admin/puestos")
	public String listarPuestos(Model model) {
		model.addAttribute("listaPuestos", puestoServicio.findAll());
		return "admin/puestos";
	}

	/**
	 * Método que muestra un formulario con los datos de un puesto para editarlos.
	 * 
	 * @param id    El id del puesto a editar
	 * @param model Model que le pasa los datos al formulario
	 * @return Plantilla que muestra el formulario con los datos.
	 */
	@GetMapping("/admin/editarpuesto/{id}")
	public String mostrarFormularioEdicion(@PathVariable("id") long id, Model model) {
		Puesto puestoEditar = puestoServicio.findById(id);
		model.addAttribute("puesto", puestoEditar);
		return "admin/editarpuesto_form";
	}

	/**
	 * Método que procesa la respuesta del formulario al editar.
	 */
	@PostMapping("/admin/editarpuesto/submit")
	public String procesarFormularioEdicion(@ModelAttribute("puesto") Puesto puesto) {
		puestoServicio.edit(puesto);
		return "redirect:/admin/puestos";
	}

	/**
	 * Método que permite al administrador eliminar un tipo de puesto.
	 * 
	 * @param id el id del puesto a eliminar (tomado de la lista)
	 * @return Plantilla que pinta la lista de puestos actualizada.
	 */
	@GetMapping("/admin/borrarpuesto/{id}")
	public String borrarpuesto(@PathVariable("id") long id) {
		puestoServicio.deleteById(id);
		return "redirect:/admin/puestos";
	}
}

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
 * @author usuarioç
 *
 */
@Controller
public class PuestoController {

	private PuestoServicio puestoServicio;

	/**
	 * @param puestoServicio
	 */
	public PuestoController(PuestoServicio puestoServicio) {
		super();
		this.puestoServicio = puestoServicio;
	}

	@GetMapping("/admin/nuevopuesto")
	public String mostrarNuevoPuestoForm(Model model) {
		model.addAttribute("puesto", new Puesto());
		return "admin/puesto_form";
	}

	@PostMapping("/admin/nuevopuesto/submit")
	public String procesarNuevoPuesto(@ModelAttribute("puesto") Puesto puesto) {
		puestoServicio.save(puesto);
		return "redirect:/puestos";
	}

	@GetMapping("/admin/puestos")
	public String listarPuestos(Model model) {
		model.addAttribute("listaPuestos", puestoServicio.findAll());
		return "admin/puestos";
	}

	@GetMapping("/admin/editarpuesto/{id}")
	public String mostrarFormularioEdicion(@PathVariable("id") long id, Model model) {
		Puesto puestoEditar = puestoServicio.findById(id);
		model.addAttribute("puesto", puestoEditar);
		return "admin/editarpuesto_form";
	}

	/**
	 * Método que procesa la respuesta del formulario al editar
	 */
	@PostMapping("/admin/editarpuesto/submit")
	public String procesarFormularioEdicion(@ModelAttribute("puesto") Puesto puesto) {
		puestoServicio.edit(puesto);
		return "redirect:/puestos";
	}

	@GetMapping("/admin/borrarpuesto/{id}")
	public String borrarpuesto(@PathVariable("id") long id) {
		puestoServicio.deleteById(id);
		return "redirect:/puestos";
	}
}

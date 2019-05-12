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

import com.salesianostriana.dam.gestionhermandad.model.Hermano;
import com.salesianostriana.dam.gestionhermandad.services.HermanoServicio;

/**
 * @author usuarioç
 *
 */
@Controller
public class HermanoController {

	private HermanoServicio hermanoServicio;

	public HermanoController(HermanoServicio hermanoServicio) {
		this.hermanoServicio = hermanoServicio;
	}

	@GetMapping("/login")
	public String mostrarlogin(Model model) {
		model.addAttribute("hermano", new Hermano());
		return "login";
	}

	@PostMapping("/login/submit")
	public String procesarlogin(Model model) {
		return "login";
	}

	@GetMapping({ "/listarTodos" })
	public String listarTodos(Model model) {
		model.addAttribute("listaHerm", hermanoServicio.findAll());
		return "listaHermanos";
	}

	@GetMapping("/nuevoHno")
	public String mostrarFormulario(Model model) {
		model.addAttribute("hermano", new Hermano());
		return "hermano_form";
	}

	@GetMapping("/editarHno/{id}")
	public String mostrarFormularioEdicion(@PathVariable("id") long id, Model model) {

		// Buscamos al alumno por id y recordemos que el método findById del servicio,
		// devuelve el objeto buscado o null si no se encuentra.

		Hermano hnoEditar = hermanoServicio.findById(id);

		if (hnoEditar != null) {
			model.addAttribute("hermano", hnoEditar);
			return "hermano_form";
		} else {
			// No existe ningún alumno con el Id proporcionado.
			// Redirigimos hacia el listado.
			return "redirect:/listarTodos";
		}

	}

	/**
	 * Método que procesa la respuesta del formulario al editar
	 */
	@PostMapping("/editarHno/submit")
	public String procesarFormularioEdicion(@ModelAttribute("hermano") Hermano hno) {
		hermanoServicio.edit(hno);
		return "redirect:/listarTodos";// Volvemos a redirigir la listado a través del controller para pintar la lista
		// actualizada con la modificación hecha
	}

	@GetMapping("/borrarHno/{id}")
	public String borrarhno(@PathVariable("id") long id) {
		hermanoServicio.deleteById(id);
		return "redirect:/listarTodos";
	}
}

/**
 * 
 */
package com.salesianostriana.dam.gestionhermandad.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianostriana.dam.gestionhermandad.model.Hermano;
import com.salesianostriana.dam.gestionhermandad.model.PapeletaSitio;
import com.salesianostriana.dam.gestionhermandad.services.HermanoServicio;
import com.salesianostriana.dam.gestionhermandad.services.PapeletaSitioServicio;
import com.salesianostriana.dam.gestionhermandad.services.PuestoServicio;

/**
 * Clase que tiene los métodos que controlan las plantillas que permiten manejar
 * al administrador las papeletas de sitio.
 * 
 * @author José Antonio Llamas Álvarez
 *
 */
@Controller
public class PapeletaSitioAdminController {

	@Autowired
	PapeletaSitioServicio papeletaSitioServicio;
	@Autowired
	PuestoServicio puestoServicio;
	@Autowired
	HermanoServicio hermanoServicio;

	public PapeletaSitioAdminController(PapeletaSitioServicio papeletaSitioServicio) {
		super();
		this.papeletaSitioServicio = papeletaSitioServicio;
	}

	/**
	 * Método que permite mostrar una lista de todas las papeletas de sitio del
	 * sistema
	 * 
	 * @param model Modelo para pasar la lista a la plantilla.
	 * @return Plantilla html que pinta la lista.
	 */
	@GetMapping("/admin/listarTodasPapeletas")
	public String listarTodasPapeletas(Model model) {
		model.addAttribute("listaPapeletas", papeletaSitioServicio.findAll());
		return "admin/listaPapeletas";
	}

	/**
	 * Método que permite al administrador asignar una papeleta de sitio a un
	 * hermano. En el caso de que ya tuviera una (controlado por el atributo
	 * booleano papeletaSacada del objeto Hermano), se mostrará una pantalla de
	 * error.
	 * 
	 * @param id    el id del hermano seleccionado de la lista
	 * @param model Modelo para pasar a la plantilla los datos de la papeleta y la
	 *              lista de puestos.
	 * @return Plantilla con el formulario que permite escoger el puesto que ocupará
	 *         el hermano en la cofradía.
	 */
	@GetMapping("/admin/nuevaPapeleta/{id}")
	public String mostrarFormulario(@PathVariable("id") long id, Model model) {
		Hermano hno = hermanoServicio.findById(id);
		if (hermanoServicio.buscarPapeletaSacada().contains(hno)) {
			return "user/papeletaExistente";
		} else {
			hno.setPapeletaSacada(true);
			PapeletaSitio papeletaSitio = new PapeletaSitio();
			papeletaSitio.setHermano(hno);
			model.addAttribute("papeletaSitio", papeletaSitio);
			model.addAttribute("puestos", puestoServicio.findAll());
			return "admin/nuevaPapeleta";
		}
	}

	/**
	 * Método que procesa el alta de la papeleta de sitio y la almacena en la bbdd.
	 * 
	 * @param papeletaSitio El objeto de la papeleta de sitio con los datos
	 *                      elegidos.
	 * @return Plantilla que muestra la lista de papeletas de sitio existentes.
	 */
	@PostMapping("/admin/nuevaPapeleta/submit")
	public String procesarPapeleta(@ModelAttribute("papeletaSitio") PapeletaSitio papeletaSitio) {
		papeletaSitio.setFecha(LocalDate.now());
		papeletaSitio.setAnyo(LocalDate.now().getYear());
		papeletaSitioServicio.save(papeletaSitio);
		return "redirect:/admin/listarTodasPapeletas";
	}

	/**
	 * Método que muestra un formulario con los datos de una papeleta para editarla.
	 * 
	 * @param id    El id de la papeleta a editar
	 * @param model Model que le pasa los datos al formulario
	 * @return Plantilla que muestra el formulario con los datos.
	 */
	@GetMapping("/admin/editarPapeletaSitio/{id}")
	public String mostrarFormularioEdicion(@PathVariable("id") long id, Model model) {
		PapeletaSitio papeletaEditar = papeletaSitioServicio.findById(id);
		model.addAttribute("papeletaSitio", papeletaEditar);
		model.addAttribute("puestos", puestoServicio.findAll());
		return "admin/editarpapeleta_form";
	}

	/**
	 * Método que procesa la respuesta del formulario al editar.
	 */
	@PostMapping("/admin/editarPapeletaSitio/submit")
	public String procesarFormularioEdicion(@ModelAttribute("papeletaSitio") PapeletaSitio papeleta) {
		papeletaSitioServicio.edit(papeleta);
		return "redirect:/admin/listarTodasPapeletas";
	}

	/**
	 * Método que permite al administrador borrar una papeleta de sitio de la lista.
	 * 
	 * @param id El id de la papeleta a borrar
	 * @return Plantilla que pinta la lista de papeletas actualizada.
	 */
	@GetMapping("/admin/borrarPapeletaSitio/{id}")
	public String borrar(@PathVariable("id") long id) {
		papeletaSitioServicio.deleteById(id);
		return "redirect:/admin/listarTodasPapeletas";
	}
}

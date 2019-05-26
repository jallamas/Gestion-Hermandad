/**
 * 
 */
package com.salesianostriana.dam.gestionhermandad.controller;

import java.time.LocalDate;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianostriana.dam.gestionhermandad.model.HermanoProvisional;
import com.salesianostriana.dam.gestionhermandad.services.HermanoProvisionalServicio;

/**
 * Clase contiene los métodos que controlan las plantillas de los hermanos dados
 * de alta en el sistema y que están a expensas de la validación por pàrte del
 * administrador.
 * 
 * @author José Antonio Llamas Álvarez
 *
 */
@Controller
public class HermanoProvisionalController {

	private HermanoProvisionalServicio hermanoProvisionalServicio;

	public HermanoProvisionalController(HermanoProvisionalServicio hermanoprovisionalservicio) {
		this.hermanoProvisionalServicio = hermanoprovisionalservicio;
	}

	/**
	 * Método que muestra el formulario para introducir los datos y solicitar el
	 * alta en el sistema.
	 * 
	 * @param model Modelo para pasar los datos al formulario
	 * @return Plantilla que pinta el formulario
	 */
	@GetMapping("/registro")
	public String mostrarRegistroForm(Model model) {
		model.addAttribute("hermanoProvisional", new HermanoProvisional());
		return "registro_form";
	}

	/**
	 * Plantilla que procesa los datos del formulario de alta, Asigna la fecha
	 * actual como fecha de alta y encripta la contraseña del hermano y lo guarda en
	 * la bbdd. Si el nombre de usuario elegido ya está en uso, conduce a una
	 * plantilla que muestra el error.
	 * 
	 * @param hermanoProvisional El objeto de esa clase para construir el hermano
	 * @return Plantilla que muestra los datos que se han almacenado.
	 */
	@PostMapping("/registro/submit")
	public String procesarAltaProvisional(@ModelAttribute("hermanoProvisional") HermanoProvisional hermanoProvisional) {
		if (hermanoProvisionalServicio.findByUsuario(hermanoProvisional.getUsuario()).isEmpty()) {
			hermanoProvisional.setFechaAlta(LocalDate.now());
			PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			hermanoProvisional.setPassword(passwordEncoder.encode(hermanoProvisional.getPassword()));
			hermanoProvisionalServicio.save(hermanoProvisional);
			return "/vistaHermanoProvisional";
		} else {
			return "/usuarioExistente";
		}
	}

	/**
	 * Método que muestra al administrador una lista de todas las altas pendientes
	 * de validar que haya.
	 * 
	 * @param model Modelo que usamos para pasar los datos a la plantilla
	 * @return Plantilla html que pinta la lista
	 */
	@GetMapping({ "/admin/listarTodosProv" })
	public String listarTodos(Model model) {
		model.addAttribute("listaProv", hermanoProvisionalServicio.findAll());
		return "admin/listaHermanosProv";
	}

	/**
	 * Método que valida un alta en el sistema pasando el hermano provisional a
	 * hermano.
	 * 
	 * @param id El id del hermano Provisional
	 * @return Plantilla que repinta la lista de altas pendientes de validar
	 *         actualizada.
	 */
	@GetMapping("/admin/validar/{id}")
	public String validar(@PathVariable("id") long id) {
		hermanoProvisionalServicio.validarHermanoProvisional(hermanoProvisionalServicio.findById(id));
		return "redirect:/admin/listarTodosProv";
	}

	/**
	 * Método que permite al administrador borrar los datos de un hermano
	 * provisional en el caso de que no corresponda validar el alta (los datos se
	 * borran definitivamente)
	 * 
	 * @param id el id del hermano provisional
	 * @return Plantilla que repinta la lista de altas pendientes de validar
	 *         actualizada.
	 */
	@GetMapping("/admin/borrar/{id}")
	public String borrar(@PathVariable("id") long id) {
		hermanoProvisionalServicio.deleteById(id);
		return "redirect:/admin/listarTodosProv";
	}

}
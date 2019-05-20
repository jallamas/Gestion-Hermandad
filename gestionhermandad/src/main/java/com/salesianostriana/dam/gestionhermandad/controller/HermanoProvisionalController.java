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
 * @author José Antonio Llamas Álvarez
 *
 */
@Controller
public class HermanoProvisionalController {

	private HermanoProvisionalServicio hermanoProvisionalServicio;
	
	public HermanoProvisionalController(HermanoProvisionalServicio hermanoprovisionalservicio) {
		this.hermanoProvisionalServicio = hermanoprovisionalservicio;
	}

	@GetMapping("/registro")
	public String mostrarRegistroForm(Model model) {
		model.addAttribute("hermanoProvisional", new HermanoProvisional());
		return "registro_form";
	}

	@PostMapping("/registro/submit")
	public String procesarAltaProvisional(@ModelAttribute("hermanoProvisional") HermanoProvisional hermanoProvisional) {
		hermanoProvisional.setFechaAlta(LocalDate.now());
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		hermanoProvisional.setPassword(passwordEncoder.encode(hermanoProvisional.getPassword()));
		hermanoProvisionalServicio.save(hermanoProvisional);
		return "/vistaHermanoProvisional";
	}

	@GetMapping({ "/admin/listarTodosProv" })
	public String listarTodos(Model model) {
		model.addAttribute("listaProv", hermanoProvisionalServicio.findAll());
		return "admin/listaHermanosProv";
	}

	@GetMapping("/admin/validar/{id}")
	public String validar(@PathVariable("id") long id) {
		hermanoProvisionalServicio.validarHermanoProvisional(hermanoProvisionalServicio.findById(id));
		return "redirect:/listarTodosProv";
	}

	@GetMapping("/admin/borrar/{id}")
	public String borrar(@PathVariable("id") long id) {
		hermanoProvisionalServicio.deleteById(id);
		return "redirect:/listarTodosProv";
	}

}

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
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianostriana.dam.gestionhermandad.model.PapeletaSitio;
import com.salesianostriana.dam.gestionhermandad.services.PapeletaSitioServicio;

/**
 * @author jallamas
 *
 */
@Controller
public class PapeletaSitioAdminController {

	@Autowired
	PapeletaSitioServicio papeletaSitioServicio;

	public PapeletaSitioAdminController(PapeletaSitioServicio papeletaSitioServicio) {
		super();
		this.papeletaSitioServicio = papeletaSitioServicio;
	}

	@GetMapping("/admin/listarTodasPapeletas")
	public String listarTodasPapeletas(Model model) {
		model.addAttribute("listaPapeletas", papeletaSitioServicio.findAll());
		return "admin/listaPapeletas";
	}

	@GetMapping("/admin/nuevaPapeleta")
	public String mostrarFormulario(Model model) {
		model.addAttribute("papeletaSitio", new PapeletaSitio());
		return "admin/papeletaSitio_form_admin";
	}

	@PostMapping("/admin/nuevaPapeleta/submit")
	public String procesarAlta(@ModelAttribute("papeletaSitio") PapeletaSitio papeletaSitio) {
		papeletaSitio.setFecha(LocalDate.now());
		papeletaSitio.setAnyo(LocalDate.now().getYear());
		papeletaSitioServicio.save(papeletaSitio);
		return "redirect:/admin/listarTodasPapeletas";
	}
}

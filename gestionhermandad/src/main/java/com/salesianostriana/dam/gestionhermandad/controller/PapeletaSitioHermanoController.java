/**
 * 
 */
package com.salesianostriana.dam.gestionhermandad.controller;

import java.security.Principal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianostriana.dam.gestionhermandad.model.Hermano;
import com.salesianostriana.dam.gestionhermandad.model.PapeletaSitio;
import com.salesianostriana.dam.gestionhermandad.services.HermanoServicio;
import com.salesianostriana.dam.gestionhermandad.services.PapeletaSitioServicio;
import com.salesianostriana.dam.gestionhermandad.services.PuestoServicio;

/**
 * @author jallamas
 *
 */
@Controller
public class PapeletaSitioHermanoController {

	@Autowired
	PapeletaSitioServicio papeletaSitioServicio;
	@Autowired
	PuestoServicio puestoServicio;
	@Autowired
	HermanoServicio hermanoServicio;

	public PapeletaSitioHermanoController(PapeletaSitioServicio papeletaSitioServicio) {
		super();
		this.papeletaSitioServicio = papeletaSitioServicio;
	}

	@GetMapping("/user/nuevaPapeleta")
	public String mostrarFormulario(Model model, Principal p) {
		Hermano hno = hermanoServicio.buscarHermanoLogeado(p);
		PapeletaSitio papeletaSitio = new PapeletaSitio();
		papeletaSitio.setHermano(hno);
		model.addAttribute("papeletaSitio", papeletaSitio);
		model.addAttribute("puestos", puestoServicio.listarPuestosNormales());
		//model.addAttribute("puestos", puestoServicio.findAll());
		return "user/nuevaPapeleta";
	}

	@PostMapping("/user/nuevaPapeleta/submit")
	public String procesarPapeleta(@ModelAttribute("papeletaSitio") PapeletaSitio papeletaSitio) {
		papeletaSitio.setFecha(LocalDate.now());
		papeletaSitio.setAnyo(LocalDate.now().getYear());
		papeletaSitioServicio.save(papeletaSitio);
		return "redirect:/";
	}
}

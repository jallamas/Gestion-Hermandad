package com.salesianostriana.dam.gestionhermandad.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.salesianostriana.dam.gestionhermandad.services.HermanoServicio;

@Controller
public class WebController {

	@Autowired
	HermanoServicio hermanoServicio;
	
	@GetMapping("/quienes_somos")
	public String listarTodos(Model model) {
		return "quienes_somos";
	}
	
	@GetMapping("/")
	public String mostrarIndex(Model model,Principal p) {
		model.addAttribute("hermano", hermanoServicio.buscarHermanoLogeado(p));
		return "index";
	}
}

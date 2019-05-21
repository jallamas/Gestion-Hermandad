package com.salesianostriana.dam.gestionhermandad.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

	@GetMapping("/quienes_somos")
	public String listarTodos(Model model) {
		return "quienes_somos";
	}
	
}

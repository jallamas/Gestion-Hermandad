package com.salesianostriana.dam.gestionhermandad.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.salesianostriana.dam.gestionhermandad.services.HermanoServicio;

/**
 * Esta clase contiene los métodos para mostrar las plantillas de la parte
 * estática de la aplicación web.
 * 
 * @author José Antonio Llamas Álvarez
 *
 */
@Controller
public class WebController {

	@Autowired
	HermanoServicio hermanoServicio;

	/**
	 * Muestra la plantilla de "quienes somos"
	 */
	@GetMapping("/quienes_somos")
	public String listarTodos(Model model) {
		return "quienes_somos";
	}

	/**
	 * Muestra la plantilla de "inicio"
	 * 
	 * model Le pasamos los datos del hermano logueado para poder usar los métodos
	 * del menú de Hermano.
	 */
	@GetMapping("/")
	public String mostrarIndex(Model model, Principal p) {
		model.addAttribute("hermano", hermanoServicio.buscarHermanoLogeado(p));
		return "index";
	}
}

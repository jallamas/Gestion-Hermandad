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
 * Clase que tiene los métodos que controlan las plantillas que permiten manejar
 * al hermano las papeletas de sitio.
 * 
 * @author José Antonio Llamas Álvarez
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

	/**
	 * Método que permite al hermano logueado solicitar su papeleta de sitio. En el
	 * caso de que ya tuviera una (controlado por el atributo booleano
	 * papeletaSacada del objeto Hermano), se mostrará una pantalla de error.
	 * 
	 * @param model Modelo para pasar a la plantilla los datos de la papeleta y la
	 *              lista de puestos.
	 * @param p     Objeto de la clase Principal que nos permite obtener la
	 *              información del hermano logueado
	 * @return Plantilla que muestra el formulario con los puestos a elegir para
	 *         obtener la papeleta.
	 */
	@GetMapping("/user/nuevaPapeleta")
	public String mostrarFormulario(Model model, Principal p) {
		Hermano hno = hermanoServicio.buscarHermanoLogeado(p);
		if (hermanoServicio.buscarPapeletaSacada().contains(hno)) {
			return "user/papeletaExistente";
		} else {
			hno.setPapeletaSacada(true);
			hermanoServicio.edit(hno);
			PapeletaSitio papeletaSitio = new PapeletaSitio();
			papeletaSitio.setHermano(hno);
			model.addAttribute("papeletaSitio", papeletaSitio);
			model.addAttribute("puestos", puestoServicio.listarPuestosNormales());
			// model.addAttribute("puestos", puestoServicio.findAll());
			return "user/nuevaPapeleta";
		}
	}

	/**
	 * Método que procesa el alta de la papeleta de sitio y la almacena en la bbdd.
	 * 
	 * @param papeletaSitio El objeto de la papeleta de sitio con los datos
	 *                      elegidos.
	 * @return Plantilla que muestra el inicio de la aplicación
	 */
	@PostMapping("/user/nuevaPapeleta/submit")
	public String procesarPapeleta(@ModelAttribute("papeletaSitio") PapeletaSitio papeletaSitio) {
		papeletaSitio.setFecha(LocalDate.now());
		papeletaSitio.setAnyo(LocalDate.now().getYear());
		papeletaSitioServicio.save(papeletaSitio);
		return "redirect:/";
	}
}

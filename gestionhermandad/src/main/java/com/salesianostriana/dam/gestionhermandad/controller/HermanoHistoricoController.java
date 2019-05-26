package com.salesianostriana.dam.gestionhermandad.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.salesianostriana.dam.gestionhermandad.services.HermanoHistoricoServicio;

/**
 * Esta clase contiene los métodos que controlan las plantillas de los hermanos dados de baja y que están en el histórico.  
 * @author José Antonio Llamas Álvarez
 *
 */
@Controller
public class HermanoHistoricoController {

	private HermanoHistoricoServicio hermanoHistoricoServicio;

	/**
	 * @param hermanoHistoricoServicio
	 */
	public HermanoHistoricoController(HermanoHistoricoServicio hermanoHistoricoServicio) {
		super();
		this.hermanoHistoricoServicio = hermanoHistoricoServicio;
	}
	
	/**
	 * Método que muestra una lista de todos los hermanos que están en el histórico 
	 * @param model Objeto de la clase Modelo para pasar la lista a la plantilla
	 * @return Plantilla html que muestra la lista
	 */
	@GetMapping({ "/admin/listarHistoricos" })
	public String listarTodos(Model model) {
		model.addAttribute("listaHermanoHist", hermanoHistoricoServicio.findAll());
		return "admin/listaHermanosHist";
	}
	
	/**
	 * Método que vuelve a activar a un hermano histórico llamando al correspondiente servicio
	 * @param id El id del hermano
	 * @return Plantilla que muestra la lista de hermanos históricos actualizada.
	 */
	@GetMapping("admin/reactivarHnoHist/{id}")
	public String pasarHnoHist(@PathVariable("id") long id) {
		hermanoHistoricoServicio.reactivarHermanoHistorico(hermanoHistoricoServicio.findById(id));
		return "redirect:/admin/listaHermanosHist";
	}
}

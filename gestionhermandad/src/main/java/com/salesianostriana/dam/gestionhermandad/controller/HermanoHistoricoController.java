/**
 * 
 */
package com.salesianostriana.dam.gestionhermandad.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.salesianostriana.dam.gestionhermandad.services.HermanoHistoricoServicio;

/**
 * @author jallamas
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
	
	@GetMapping({ "/listarHistoricos" })
	public String listarTodos(Model model) {
		model.addAttribute("listaHermanoHist", hermanoHistoricoServicio.findAll());
		return "admin/listaHermanosHist";
	}
}

/**
 * 
 */
package com.salesianostriana.dam.gestionhermandad.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
	
	@GetMapping({ "/admin/listarHistoricos" })
	public String listarTodos(Model model) {
		model.addAttribute("listaHermanoHist", hermanoHistoricoServicio.findAll());
		return "admin/listaHermanosHist";
	}
	
	@GetMapping("admin/reactivarHnoHist/{id}")
	public String pasarHnoHist(@PathVariable("id") long id) {
		hermanoHistoricoServicio.reactivarHermanoHistorico(hermanoHistoricoServicio.findById(id));
		return "redirect:/listarHistoricos";
	}
}

/**
 * 
 */
package com.salesianostriana.dam.gestionhermandad.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianostriana.dam.gestionhermandad.model.HermanoProvisional;

/**
 * @author jallamas
 *
 */
@Controller
public class HermanoProvisionalController {

	@GetMapping("/registro")
	public String showForm(Model model) {

		HermanoProvisional hermanoProvisional = new HermanoProvisional();
		model.addAttribute("hermanoProvisional", hermanoProvisional);

		return "registro_form";
	}

	@PostMapping("/addHermanoProvisional")
	public String submit(@ModelAttribute("hermanoProvisionalForm") HermanoProvisional hermanoProvisional, Model model) {
		model.addAttribute("hermanoProvisional", hermanoProvisional);
		return "view";
	}
}

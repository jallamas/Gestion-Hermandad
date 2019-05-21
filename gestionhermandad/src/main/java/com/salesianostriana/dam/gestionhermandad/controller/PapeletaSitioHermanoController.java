/**
 * 
 */
package com.salesianostriana.dam.gestionhermandad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.salesianostriana.dam.gestionhermandad.services.PapeletaSitioServicio;

/**
 * @author jallamas
 *
 */
@Controller
public class PapeletaSitioHermanoController {

	@Autowired
	PapeletaSitioServicio papeletaSitioServicio;

	public PapeletaSitioHermanoController(PapeletaSitioServicio papeletaSitioServicio) {
		super();
		this.papeletaSitioServicio = papeletaSitioServicio;
	}
}

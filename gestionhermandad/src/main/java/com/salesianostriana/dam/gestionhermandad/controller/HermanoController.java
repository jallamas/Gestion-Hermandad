/**
 * 
 */
package com.salesianostriana.dam.gestionhermandad.controller;

import java.security.Principal;
import java.time.LocalDate;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianostriana.dam.gestionhermandad.model.Hermano;
import com.salesianostriana.dam.gestionhermandad.services.HermanoServicio;

/**
 * @author José Antonio Llamas Álvarez
 *
 */
@Controller
public class HermanoController {

	private HermanoServicio hermanoServicio;

	public HermanoController(HermanoServicio hermanoServicio) {
		this.hermanoServicio = hermanoServicio;
	}

	@GetMapping("/admin/listarTodos")
	public String listarTodos(Model model) {
		model.addAttribute("listaHerm", hermanoServicio.findAll());
		return "admin/listaHermanos";
	}

	@GetMapping("/admin/listarBajas")
	public String ListarSolicitudesBaja(Model model) {
		model.addAttribute("listaHerm", hermanoServicio.listarSolicitudesBaja());
		return "admin/listaSolicitudesBaja";
	}

	@GetMapping("/admin/nuevoHno")
	public String mostrarFormulario(Model model) {
		model.addAttribute("hermano", new Hermano());
		return "admin/hermano_form_admin";
	}

	@PostMapping("/admin/nuevoHno/submit")
	public String procesarAlta(@ModelAttribute("hermano") Hermano hermano) {
		hermano.setFechaAlta(LocalDate.now());
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		hermano.setPassword(passwordEncoder.encode(hermano.getPassword()));
		hermanoServicio.save(hermano);
		return "redirect:/admin/listarTodos";
	}

	@GetMapping("/admin/editarHno/{id}")
	public String mostrarFormularioEdicion(@PathVariable("id") long id, Model model) {

		Hermano hnoEditar = hermanoServicio.findById(id);
		model.addAttribute("hermano", hnoEditar);
		return "hermano_form";
	}

	/**
	 * Método que procesa la respuesta del formulario al editar
	 */
	@PostMapping("/admin/editarHno/submit")
	public String procesarFormularioEdicion(@ModelAttribute("hermano") Hermano hno) {
		hermanoServicio.edit(hno);
		return "redirect:/admin/listarTodos";
	}

	@GetMapping("/user/editarHno")
	public String mostrarFormularioEdicionUser(Model model, Principal p) {
		model.addAttribute("hermano", hermanoServicio.buscarHermanoLogeado(p));
		return "user/hermano_form";
	}

	/**
	 * Método que procesa la respuesta del formulario al editar
	 */
	@PostMapping("user/editarHno/submit")
	public String procesarFormularioEdicionUser(@ModelAttribute("hermano") Hermano hermano) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		hermano.setPassword(passwordEncoder.encode(hermano.getPassword()));
		hermanoServicio.edit(hermano);
		return "redirect:/user/vistaHermanoEditado";
	}

	@GetMapping("/user/vistaHermanoEditado")
	public String mostrarDatosEditados(Model model, Principal p) {
		model.addAttribute("hermano", hermanoServicio.buscarHermanoLogeado(p));
		return "user/vistaHermanoEditado";
	}

	@GetMapping("/admin/pasarHnoHist/{id}")
	public String pasarHnoHist(@PathVariable("id") long id) {
		hermanoServicio.pasarHermanoHistorico(hermanoServicio.findById(id));
		return "redirect:/admin/listarTodos";
	}
	
	@GetMapping("/admin/confirmarBajaHno/{id}")
	public String confirmarBajaHermano(@PathVariable("id") long id) {
		hermanoServicio.pasarHermanoHistorico(hermanoServicio.findById(id));
		return "redirect:/admin/listarBajas";
	}

	@GetMapping("/buscar")
	public String listarFiltrado(Model model) {
		model.addAttribute("listaHerm", hermanoServicio.findAll());
		return "admin/buscar";
	}

	@GetMapping("/user/solicitarBaja/{id}")
	public String solicitarBaja(@PathVariable("id") long id) {
		hermanoServicio.solicitarBaja(id);
		return "redirect:/";
	}

	@GetMapping("/admin/renumerarHermanos")
	public String renumerarHermano() {
		hermanoServicio.renumerarHermanos();
		return "redirect:/admin/listarTodos";
	}

}
/**
 * 
 */
package com.salesianostriana.dam.gestionhermandad.controller;

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
 * @author usuarioç
 *
 */
@Controller
public class HermanoController {

	private HermanoServicio hermanoServicio;

	public HermanoController(HermanoServicio hermanoServicio) {
		this.hermanoServicio = hermanoServicio;
	}

//	@GetMapping("/login")
//	public String mostrarlogin(Model model) {
//		model.addAttribute("hermano", new Hermano());
//		return "login";
//	}
//
//	@PostMapping("/login/submit")
//	public String procesarlogin(Model model) {
//		return "login";
//	}

	@GetMapping({ "/listarTodos" })
	public String listarTodos(Model model) {
		model.addAttribute("listaHerm", hermanoServicio.findAll());
		return "admin/listaHermanos";
	}

	@GetMapping("/nuevoHno")
	public String mostrarFormulario(Model model) {
		model.addAttribute("hermano", new Hermano());
		return "admin/hermano_form_admin";
	}
	@PostMapping("/nuevoHno/submit")
	public String procesarAlta(@ModelAttribute("hermano") Hermano hermano) {
		hermano.setFechaAlta(LocalDate.now());
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		hermano.setPassword(passwordEncoder.encode(hermano.getPassword()));
		hermanoServicio.save(hermano);
		return "redirect:/listarTodos";
	}

	@GetMapping("/editarHno/{id}")
	public String mostrarFormularioEdicion(@PathVariable("id") long id, Model model) {

		Hermano hnoEditar = hermanoServicio.findById(id);
		model.addAttribute("hermano", hnoEditar);
		return "hermano_form";
	}

	/**
	 * Método que procesa la respuesta del formulario al editar
	 */
	@PostMapping("/editarHno/submit")
	public String procesarFormularioEdicion(@ModelAttribute("hermano") Hermano hno) {
//		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//		hno.setPassword(passwordEncoder.encode(hno.getPassword()));
		hermanoServicio.edit(hno);
		return "redirect:/listarTodos";
	}

	@GetMapping("/pasarHnoHist/{id}")
	public String pasarHnoHist(@PathVariable("id") long id) {
		hermanoServicio.pasarHermanoHistorico(hermanoServicio.findById(id));
		return "redirect:/listarTodos";
	}
	
	@GetMapping("/buscar")
	public String listarFiltrado(Model model) {
		model.addAttribute("listaHerm", hermanoServicio.findAll());
		return "admin/buscar";
	}

//	@GetMapping("/hermanos/buscar")
//	public String getInicio(Model model) {
//		BuscarHermanoNombre hnonom=new BuscarHermanoNombre();
//		model.addAttribute("buscarHermano",hnonom);
//		return "admin/buscar";
//	}
//	
//	@PostMapping("hermanos/buscar")
//	public String listarClientes(@ModelAttribute("buscarCliente") BuscarHermanoNombre hnonom, Hermano hno, Model model) {
//		System.out.println(nom);
//		List<Hermano> clientesEncontrados=cs.buscarClienteByNomEmpresaContainingIgnoreCase(nom.getNomEmpresa());
//		model.addAttribute("clientesEncontrados",clientesEncontrados);
//		for(Hermano i:clientesEncontrados) {
//			System.out.println(i);
//		}
//		return "admin/adminClientesListar";
//	}
	
}
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
import com.salesianostriana.dam.gestionhermandad.services.HermanoProvisionalServicio;
import com.salesianostriana.dam.gestionhermandad.services.HermanoServicio;

/**
 * Esta clase contiene los métodos que controlan las plantillas que manejan a
 * los hermanos.
 * 
 * @author José Antonio Llamas Álvarez
 *
 */
@Controller
public class HermanoController {

	private HermanoServicio hermanoServicio;
	private HermanoProvisionalServicio hermanoProvisionalServicio;

	/**
	 * Constructor
	 * 
	 * @param hermanoServicio            Servicio con métodos que manejan los
	 *                                   hermanos del repositorio
	 * @param hermanoProvisionalServicio Servicio con métodos que manejan los
	 *                                   hermanos del repositorio (sin consulta,
	 *                                   debido a la herencia, también se pueden
	 *                                   aplicar a los hermanos).
	 */
	public HermanoController(HermanoServicio hermanoServicio, HermanoProvisionalServicio hermanoProvisionalServicio) {
		this.hermanoServicio = hermanoServicio;
		this.hermanoProvisionalServicio = hermanoProvisionalServicio;
	}

	/**
	 * Método que muestra una lista de todos los hermanos activos
	 * 
	 * @param model Modelo para pasar los datos a la plantilla
	 * @return La plantilla que pinta la lista
	 */
	@GetMapping("/admin/listarTodos")
	public String listarTodos(Model model) {
		model.addAttribute("listaHerm", hermanoServicio.findAll());
		return "admin/listaHermanos";
	}

	/**
	 * Método que muestra una lista de todos los hermanos activos que hayan
	 * solicitado la baja en la Hermandad
	 * 
	 * @param model Modelo para pasar los datos a la plantilla
	 * @return La plantilla que pinta la lista
	 */
	@GetMapping("/admin/listarBajas")
	public String ListarSolicitudesBaja(Model model) {
		model.addAttribute("listaHerm", hermanoServicio.listarSolicitudesBaja());
		return "admin/listaSolicitudesBaja";
	}

	/**
	 * Método que abre el formulario para que el administrador pueda registrar un
	 * hermano nuevo en el sistema.
	 * 
	 * @param model Modelo para pasar los datos a la plantilla
	 * @return Plantilla que pinta el formulario
	 */
	@GetMapping("/admin/nuevoHno")
	public String mostrarFormulario(Model model) {
		model.addAttribute("hermano", new Hermano());
		return "admin/hermano_form_admin";
	}

	/**
	 * Método que recoge los datos de formulario de registro de nuevo hermano por
	 * parte del administrador, le añade la fecha actual como fecha de alta,
	 * encripta su contraseña y guarda el registro en la base de datos.
	 * 
	 * Si el nombre de usuario que se haya introducido en el formulario ya existe en
	 * la base de datos, lleva a una pantalla de error indicándolo.
	 * 
	 * @param hermano El objeto Hermano con todos los datos recogidos del formulario
	 * @return Plantilla que muestra la lista de todos los hermanos.
	 */
	@PostMapping("/admin/nuevoHno/submit")
	public String procesarAlta(@ModelAttribute("hermano") Hermano hermano) {
		if (hermanoProvisionalServicio.findByUsuario(hermano.getUsuario()).isEmpty()) {
			hermano.setFechaAlta(LocalDate.now());
			PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			hermano.setPassword(passwordEncoder.encode(hermano.getPassword()));
			hermanoServicio.save(hermano);
			return "redirect:/admin/listarTodos";
		} else {
			return "/usuarioExistente";
		}
	}

	/**
	 * Método para editar los datos de un hermano por parte del administrador (salvo
	 * la contraseña y los datos estáticos como número de hermano, fecha de alta...)
	 * 
	 * @param id    el id del hermano a editar
	 * @param model Modelo para pasar los datos a la plantilla
	 * @return Plantilla del formulario con los datos a editar
	 */
	@GetMapping("/admin/editarHno/{id}")
	public String mostrarFormularioEdicion(@PathVariable("id") long id, Model model) {
		Hermano hnoEditar = hermanoServicio.findById(id);
		model.addAttribute("hermano", hnoEditar);
		return "admin/hermano_form_admin_edit";
	}

	/**
	 * Procesa la edición de datos de un hermano por parte del administrador y lo
	 * guarda en la base de datos. Si se hubiera editado el nombre de usuario y el
	 * nuevo ya existiera en otro hermano, se mostrará una pantalla de error.
	 * 
	 * @param hno El objeto Hermano con los datos recogidos del formulario
	 * @return Plantilla que muestra la lista de todos los hermanos.
	 */
	@PostMapping("/admin/editarHno/submit")
	public String procesarFormularioEdicion(@ModelAttribute("hermano") Hermano hno) {
		if (hermanoProvisionalServicio.findByUsuario(hno.getUsuario()).isEmpty()) {
			hermanoServicio.edit(hno);
			return "redirect:/admin/listarTodos";
		} else {
			return "/usuarioExistente";
		}
	}

	/**
	 * Método que muestra un formulario con los datos del hermano que está logueado
	 * para que edite sus datos (salvo el nombre de usuario, fecha de alta, número
	 * de hermano...)
	 * 
	 */
	@GetMapping("/user/editarHno")
	public String mostrarFormularioEdicionUser(Model model, Principal p) {
		model.addAttribute("hermano", hermanoServicio.buscarHermanoLogeado(p));
		return "user/hermano_form";
	}

	/**
	 * Método que procesa los datos editados del hermano logueado y los guarda,
	 * encriptando previamente la contraseña.
	 * 
	 * @param hermano El objeto Hermano con los datos recogidos del formulario
	 * @return Plantilla que muestra los datos guardados después de la edición.
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

	@GetMapping("/admin/anularBajaHno/{id}")
	public String anularBajaHermano(@PathVariable("id") long id) {
		hermanoServicio.anularBaja(id);
		return "redirect:/admin/listarBajas";
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

	@GetMapping("/admin/resetearPapeletasPedidasHermanos")
	public String resetearPapeletasSitio() {
		hermanoServicio.resetearPapeletaSacada();
		return "redirect:/admin/listarTodos";
	}
}
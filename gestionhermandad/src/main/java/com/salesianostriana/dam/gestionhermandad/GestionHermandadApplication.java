package com.salesianostriana.dam.gestionhermandad;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.salesianostriana.dam.gestionhermandad.model.Hermano;
import com.salesianostriana.dam.gestionhermandad.services.HermanoServicio;

/**
 * Clase que lanza la aplicación
 * 
 * @author José Antonio Llamas
 *
 */
@SpringBootApplication
public class GestionHermandadApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionHermandadApplication.class, args);
	}

	/**
	 * Método que encripta las contraseñas de todos los hermanos introducidos en el
	 * data.sql y renumera los hermanos por antigüedad para comenzar con una base de
	 * datos coherente.
	 * 
	 * @param hermanoServicio Servicio que usamos
	 * @param passwordEncoder el objeto de la clase BCryptPasswordEncoder que usamos
	 *                        para encriptar
	 */
	@Bean
	public CommandLineRunner init(HermanoServicio hermanoServicio, BCryptPasswordEncoder passwordEncoder) {
		return args -> {

			List<Hermano> listaHermanos = hermanoServicio.findAll();

			for (Hermano h : listaHermanos) {
				h.setPassword(passwordEncoder.encode(h.getPassword()));
				hermanoServicio.edit(h);
			}

			hermanoServicio.renumerarHermanos();
		};
	}
}

package com.salesianostriana.dam.gestionhermandad;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.salesianostriana.dam.gestionhermandad.model.Hermano;
import com.salesianostriana.dam.gestionhermandad.services.HermanoServicio;

@SpringBootApplication
public class GestionHermandadApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionHermandadApplication.class, args);
	}

	@Bean
	public CommandLineRunner init(HermanoServicio hermanoServicio, BCryptPasswordEncoder passwordEncoder) {
		return args -> {

			List<Hermano> listaHermanos = hermanoServicio.findAll();

			for (Hermano h : listaHermanos) {
				h.setPassword(passwordEncoder.encode(h.getPassword()));
				hermanoServicio.edit(h);
			}

//			Hermano hno = new Hermano();
//			hno.setAdmin(true);
//			hno.setEmail("admin@admin.com");
//			hno.setNombre("José Antonio");
//			hno.setApellidos("Llamas Álvarez");
//			hno.setUsuario("admin");
//			hno.setSolicitaBaja(false);
//			hno.setPassword(passwordEncoder.encode("admin"));
//
//			servicio.save(hno);

//			Hermano hno1 = new Hermano();
//			hno1.setAdmin(false);
//			hno1.setEmail("user@user.com");
//			hno1.setNombre("Esperanza");
//			hno1.setApellidos("Escacena");
//			hno1.setUsuario("user");
//			hno1.setSolicitaBaja(false);
//			hno1.setPassword(passwordEncoder.encode("1234"));
//
//			servicio.save(hno1);

		};
	}
}

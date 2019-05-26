package com.salesianostriana.dam.gestionhermandad;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.salesianostriana.dam.gestionhermandad.model.Hermano;
import com.salesianostriana.dam.gestionhermandad.model.HermanoProvisional;
import com.salesianostriana.dam.gestionhermandad.services.HermanoProvisionalServicio;
import com.salesianostriana.dam.gestionhermandad.services.HermanoServicio;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HermanoProvisionalServicioTest {

	@Autowired
	HermanoProvisionalServicio hermanoProvisionalServicio;
	@Autowired
	HermanoServicio hermanoServicio;

	@Test
	public void testValidarHermanoProvisional() {
		HermanoProvisional actual = new HermanoProvisional(1000, "Pepe", "Pérez", "654654654", "987654321", "calle",
				"sevilla", "sev", "123456", "españa", LocalDate.of(1999, 05, 03), "ad@ad.es", "pruebas1", "1324",
				LocalDate.now());
		hermanoProvisionalServicio.save(actual);
		Hermano esperado1 = new Hermano(1000, "Pepe", "Pérez", "654654654", "987654321", "calle", "sevilla", "sev",
				"123456", "españa", LocalDate.of(1999, 05, 03), "ad@ad.es", "pruebas1", "1324", LocalDate.now());
		assertEquals(esperado1, hermanoProvisionalServicio.validarHermanoProvisional(actual));
	}

	@Test
	public void test_FindByUsuario() {
		HermanoProvisional esperado = new HermanoProvisional(1000, "Pepe", "Pérez", "654654654", "987654321", "calle",
				"sevilla", "sev", "123456", "españa", LocalDate.of(1999, 05, 03), "ad@ad.es", "pruebas", "1324",
				LocalDate.now());
		hermanoProvisionalServicio.save(esperado);

		assertEquals(true, hermanoProvisionalServicio.findByUsuario("pruebas").contains(esperado));
	}

}

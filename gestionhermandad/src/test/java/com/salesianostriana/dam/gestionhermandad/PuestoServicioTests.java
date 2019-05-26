package com.salesianostriana.dam.gestionhermandad;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.salesianostriana.dam.gestionhermandad.model.Puesto;
import com.salesianostriana.dam.gestionhermandad.services.PuestoServicio;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PuestoServicioTests {

	@Autowired
	PuestoServicio puestoServicio;

	@Test
	public void testListarPuestosNormalesTrue() {
		Puesto p = new Puesto("AcólitoSeñor", 6, 12.3, 1, false);
		puestoServicio.save(p);

		assertEquals(true, puestoServicio.listarPuestosNormales().contains(p));
	}

	@Test
	public void testListarPuestosNormalesFalse() {
		Puesto p1 = new Puesto("AcólitoVirgen", 6, 12.3, 1, true);
		puestoServicio.save(p1);

		assertEquals(false, puestoServicio.listarPuestosNormales().contains(p1));
	}
}

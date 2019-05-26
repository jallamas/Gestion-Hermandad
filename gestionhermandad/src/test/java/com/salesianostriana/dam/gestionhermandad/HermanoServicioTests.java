package com.salesianostriana.dam.gestionhermandad;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.salesianostriana.dam.gestionhermandad.model.Hermano;
import com.salesianostriana.dam.gestionhermandad.services.HermanoProvisionalServicio;
import com.salesianostriana.dam.gestionhermandad.services.HermanoServicio;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HermanoServicioTests {

	@Autowired
	HermanoServicio hermanoServicio;
	@Autowired
	HermanoProvisionalServicio hermanoProvisionalServicio;

	@Test
	public void testSolicitarBaja() {
		Hermano hno = new Hermano(1000, "Pepe", "Pérez", "654654654", "987654321", "calle", "sevilla", "sev", "123456",
				"españa", LocalDate.of(1999, 05, 03), "ad@ad.es", "pruebas1", "1324", LocalDate.now());
		hermanoServicio.save(hno);
		hermanoServicio.solicitarBaja(hno.getId());

		assertEquals(true, hermanoServicio.buscarPorUsuario("pruebas1").isSolicitaBaja());
//		assertEquals(true, hermanoServicio.findById((long) 200).isSolicitaBaja());
	}

	@Test
	public void testAnularBaja() {
		Hermano hno = new Hermano(1000, "Pepe", "Pérez", "654654654", "987654321", "calle", "sevilla", "sev", "123456",
				"españa", LocalDate.of(1999, 05, 03), "ad@ad.es", "pruebas2", "1324", LocalDate.now());
		hermanoServicio.save(hno);
		hermanoServicio.solicitarBaja(hno.getId());
		hermanoServicio.anularBaja(hno.getId());

		assertEquals(false, hermanoServicio.buscarPorUsuario("pruebas2").isSolicitaBaja());
	}

	@Test
	public void testListarSolicitudesBaja() {
		Hermano hno = new Hermano(1000, "Pepe", "Pérez", "654654654", "987654321", "calle", "sevilla", "sev", "123456",
				"españa", LocalDate.of(1999, 05, 03), "ad@ad.es", "pruebas3", "1324", LocalDate.now());
		hermanoServicio.save(hno);
		hermanoServicio.solicitarBaja(hermanoServicio.buscarPorUsuario("pruebas3").getId());

		assertEquals(true,
				hermanoServicio.listarSolicitudesBaja().contains(hermanoServicio.buscarPorUsuario("pruebas3")));
	}

	@Test
	public void testHermanosPorFechaAlta() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testRenumerarHermanos() {
		hermanoServicio.renumerarHermanos();
		assertEquals(1, hermanoServicio.hermanosPorFechaAlta().get(0).getNumHermano());
	}

	@Test
	public void testMostrarCenso() {
		Hermano hno = new Hermano(1000, "Pepe", "Pérez", "654654654", "987654321", "calle", "sevilla", "sev", "123456",
				"españa", LocalDate.of(1999, 05, 03), "ad@ad.es", "pruebas4", "1324", LocalDate.now());
		hermanoServicio.save(hno);
		assertEquals(true, hermanoServicio.mostrarCenso()
				.contains(hermanoServicio.findById(hermanoServicio.buscarPorUsuario("pruebas4").getId())));
	}

	@Test
	public void testObtenerNuevoNumHermano() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testBuscarPorUsuario() {
		Hermano hno = new Hermano(1000, "Pepe", "Pérez", "654654654", "987654321", "calle", "sevilla", "sev", "123456",
				"españa", LocalDate.of(1999, 05, 03), "ad@ad.es", "pruebas5", "1324", LocalDate.now());
		hermanoServicio.save(hno);

		assertEquals(hno, hermanoServicio.buscarPorUsuario("pruebas5"));
	}

	@Test
	public void testBuscarPapeletaSacada() {
		Hermano h1 = hermanoServicio.buscarPorUsuario("user");
		h1.setPapeletaSacada(true);
		hermanoServicio.edit(h1);

		assertEquals(true, hermanoServicio.buscarPapeletaSacada().contains(hermanoServicio.buscarPorUsuario("user")));
	}

	@Test
	public void testResetearPapeletaSacada() {
		Hermano h = hermanoServicio.buscarPorUsuario("admin");
		h.setPapeletaSacada(true);
		hermanoServicio.edit(h);
		hermanoServicio.resetearPapeletaSacada();
		assertEquals(false, hermanoServicio.buscarPorUsuario("admin").isPapeletaSacada());
	}

}

/**
 * 
 */
package com.salesianostriana.dam.gestionhermandad;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.salesianostriana.dam.gestionhermandad.model.HermanoHistorico;
import com.salesianostriana.dam.gestionhermandad.services.HermanoHistoricoServicio;

/**
 * @author José Antonio Llamas Álvarez
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class HermanoHistoricoServicioTests {

	@Autowired
	HermanoHistoricoServicio hermanoHistoricoServicio;

	@Test
	public void reactivarHermanoHistorico() {

		HermanoHistorico nuevo = new HermanoHistorico(1000, "Pepe", "Pérez", "654654654", "987654321", "calle",
				"sevilla", "sev", "123456", "españa", LocalDate.of(1999, 05, 03), "ad@ad.es", "pruebas10", "1324",
				LocalDate.now(), null);
		hermanoHistoricoServicio.save(nuevo);
				
		assertEquals(nuevo.getUsuario(),hermanoHistoricoServicio.reactivarHermanoHistorico(nuevo).getUsuario());
	}
}

/**
 * 
 */
package com.salesianostriana.dam.gestionhermandad.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Esta clase modela los pedidos.
 * 
 * @author José Antonio Llamas Álvarez
 *
 */
@Data
@NoArgsConstructor
@Entity
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Hermano hermano;
	private List<LineaPedido> listaLineas;
}

/**
 * 
 */
package com.salesianostriana.dam.gestionhermandad.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Esta clase modela las líneas de los pedidos.
 * 
 * @author José Antonio Llamas Álvarez
 *
 */
@Data
@NoArgsConstructor
@Entity
public class LineaPedido {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@OneToOne
	private PapeletaSitio papeleta;

	@ManyToOne
	private Pedido pedido;

	/**
	 * 
	 * @param papeleta
	 * @param pedido
	 */
	public LineaPedido(PapeletaSitio papeleta, Pedido pedido) {
		super();
		this.papeleta = papeleta;
		this.pedido = pedido;
	}

}

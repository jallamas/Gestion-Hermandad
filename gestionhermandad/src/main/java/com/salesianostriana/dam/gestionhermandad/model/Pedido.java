/**
 * 
 */
package com.salesianostriana.dam.gestionhermandad.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

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

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaPedido;

	@ManyToOne
	private Hermano hermano;

	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private Set<LineaPedido> listaLineas = new HashSet<>();

	/**
	 * 
	 * @param hermano
	 * @param listaLineas
	 */
	public Pedido(Hermano hermano) {
		super();
		this.hermano = hermano;
	}

	/**
	 * Método que nos permite añadir una línea de pedido a la lista de líneas de un
	 * pedido.
	 * 
	 * @param lineapedido Línea del pedido.
	 */
	public void addLineaPedido(LineaPedido lineapedido) {
		this.listaLineas.add(lineapedido);
		lineapedido.setPedido(this);
	}

	/**
	 * 
	 * @param lineapedido
	 */
	public void removeLineaPedido(LineaPedido lineapedido) {
		this.listaLineas.remove(lineapedido);
		lineapedido.setPedido(null);
	}

}
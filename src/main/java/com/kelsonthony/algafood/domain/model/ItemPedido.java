package com.kelsonthony.algafood.domain.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.Data;
import lombok.EqualsAndHashCode;

@JsonRootName("item_pedido")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class ItemPedido {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JoinColumn(name = "quantidade", nullable = false)
	private Integer quantidade;
	
	@JoinColumn(name = "preco_unitario", nullable = false)
	private BigDecimal precoUnitario;
	
	@JoinColumn(name = "preco_total", nullable = false)
	private BigDecimal precoTotal;
	
	@JoinColumn(name = "observacao")
	private String observacao;
	
	@ManyToOne
	@JoinColumn(name = "pedido", nullable = false)
	private Pedido pedido;
	
	@ManyToOne
	@JoinColumn(name = "produto", nullable = false)
	private Produto produto;
}

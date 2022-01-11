package com.kelsonthony.algafood.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.Data;
import lombok.EqualsAndHashCode;

@JsonRootName("pedido")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Pedido {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "subtotal", nullable = false)
	private BigDecimal subtotal;
	
	@Column(name = "taxa_frete", nullable = false)
	private BigDecimal taxafrete;
	
	@Column(name = "valor_total", nullable = false)
	private BigDecimal valorTotal;
	
	@CreationTimestamp
	@JoinColumn(name = "data_criacao", nullable = false, columnDefinition = "datetime")
	private OffsetDateTime dataCriacao;
	
	@CreationTimestamp
	@JoinColumn(name = "data_confirmacao", columnDefinition = "datetime")
	private OffsetDateTime dataConfirmacao;
	
	@CreationTimestamp
	@JoinColumn(name = "data_cancelamento", columnDefinition = "datetime")
	private OffsetDateTime dataCancelamento;
	
	@CreationTimestamp
	@JoinColumn(name = "data_entrega", columnDefinition = "datetime")
	private OffsetDateTime dataEntrega;
	
	/*
	 * @ManyToMany
	 * 
	 * @JoinTable(name = "pedido_forma_pagamento", joinColumns = @JoinColumn(name =
	 * "pedido_id"), inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id"))
	 * private List<FormaPagamento> formasPagamento = new ArrayList<>();
	 */
	@ManyToOne
	@JoinColumn(name = "forma_pagamento_id", nullable = false)
	private FormaPagamento formaPagamento;
	
	@ManyToOne
	@JoinColumn(name = "restaurante_id", nullable = false)
	private Restaurante restaurante;
	
	@ManyToOne
	@JoinColumn(name = "usuario_cliente_id", nullable = false)
	private Usuario usuario;
	
	@Embedded
	private Endereco endereco;
	
	private StatusPedido statusPedido;
	
}

package com.kelsonthony.algafood.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.kelsonthony.algafood.core.validation.Groups;

import lombok.Data;
import lombok.EqualsAndHashCode;

@JsonRootName("estado")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "estado")
public class Estado {

	@NotNull(groups = Groups.EstadoId.class)
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Column(name = "nome", nullable = false)
	private String nome;
}
